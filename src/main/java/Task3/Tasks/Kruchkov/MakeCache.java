package Task3.Tasks.Kruchkov;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import java.util.*;

import static java.lang.Math.round;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

@ToString @EqualsAndHashCode
public class MakeCache implements InvocationHandler {
    long        cashVolume = 0;
    private     int cashTime;       // араметр Cashe - время жизни кэша ms
    private     Object casheObj;        // Кэшируемый объект
    Method      objMeth;         // Метод класса объекта
    static      long timeStart=System.nanoTime(); // Начало заполнения кэша
    long        lisOptSize = 0;

    Map<Field,Object> states = new HashMap<>(); // Состояние + результат

    ForkJoinPool forkJoinPool =   ForkJoinPool.commonPool();

    // Класс: результат выполнения метода + дата начала жини кэша
           @Getter @Setter
    private static class ResultTime {
        Object result;                  // Результат выполнения метода
        long time; // ата и время начала жизни кэша
        ResultTime(Object result, long time)    {
                  this.result = result;
                  this.time = time;
        }
    }
    
    // Класс состояние объекта  результат выпонения метода в зависимости от состояни
    private static class  StateResult{
        ConcurrentHashMap<Field,Object> statesF ;
        ResultTime resultTime  ;
        StateResult(ConcurrentHashMap<Field,Object> statesF, ResultTime resultTime )   {
            this.statesF = statesF;
            this.resultTime  = resultTime ;
        }
        StateResult()   {

        }
    }
    
    private ConcurrentHashMap<Method, List<StateResult>> results;     // = new HashMap<>();

    public MakeCache(Object obj) {
       this.casheObj = obj;
    }
    // Расчет оптмального размера кэша перед 1-м стартам потока
    private static  long       lisOptSizeCalc(int cashTime, long casheVolume)    {
        double speed =  10000000*casheVolume/  (System.nanoTime() - timeStart );
        long listSize = (long ) round((speed * cashTime));
        System.out.println("расситанный объем кэша "+listSize + " speed "+speed);
        return listSize;
    }
    private class ForCasheForkJoinPool extends RecursiveAction {
        @Override
        protected void compute() {
            System.out.println("Процесс : "+Thread.currentThread().getName());
            for (Map.Entry<Method, List<StateResult>> resCashe : results.entrySet())   {
                List<StateResult> stateResults = new ArrayList<>();
                stateResults = resCashe.getValue();
                for (int ii=0; ii <  stateResults.size(); ii++) {
                    if(( System.nanoTime() - stateResults.get(ii).resultTime.time)/1000000 > cashTime )    {
                        // 0,5 секунды форы , чтобы надежно проверять при чтении на удаленное
                        System.out.println(" Удален элемент с временем жизни "+(( System.nanoTime() - stateResults.get(ii).resultTime.time)/1000000) );
                        stateResults.remove(ii);
                    }
                }
            }
        }
    }
    private void threadStart()  {
        forkJoinPool.invoke(new ForCasheForkJoinPool());
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        boolean found=true;
        Object value;
        long time;
        Object result;
        ResultTime resultTime;
        List<StateResult> stateResultL;
        StateResult stateResult = new StateResult();
        objMeth = this.casheObj.getClass().getMethod(method.getName(),method.getParameterTypes());
        if (objMeth.isAnnotationPresent(Cashe.class))    {
            cashTime = objMeth.getAnnotation(Cashe.class).value();
            if (results == null)    {
                results = new ConcurrentHashMap<>();
            }
            if ( results.containsKey(objMeth) ) {
// Сформруем фактическое состояние объекта и поищем его в кэш
                  states.clear();        // очистка предыдущего
                for (Field  vField : this.casheObj.getClass().getDeclaredFields()) {
                    vField.setAccessible(true);
                    value = vField.get(this.casheObj);
//                    Запомним состояние объекта
                  states.put(vField,value);
                }
                stateResultL = results.get(objMeth);
               for(StateResult  stateResultE : stateResultL ) {
                    found = true;
                    stateResult= stateResultE;
                    for (Map.Entry<Field,Object> statesL : states.entrySet()) {
                        Object pValue =  stateResult.statesF.get(statesL.getKey());
                        if (pValue.equals(statesL.getValue())) {
                            found = true;
                        }
                        else    {
                            found = false;
                            break;
                        }
                    }
                    if (found)  {
                        break;
                    }
                }
                if ( found )    {
                    // Если время жизни кэша истекло. то читаем осторожно 
                    if(( System.nanoTime() - stateResult.resultTime.time)/1000000 > cashTime )    {
                        try {
                            //  Если состояние объекта уже
                            // было в кэш, то выводим результат метода с обновлением времени
                            resultTime = stateResult.resultTime;
                            resultTime.time = System.nanoTime();
                            return  resultTime.result;
                        }
                        catch (Exception e) { // Попали на удаленное значение : пересчитываем кэш
                            time = System.nanoTime();
                            result  =  method.invoke(this.casheObj, args);
                            resultTime = new ResultTime(result,  time);
                            return resultTime.result;
                        }
                    }
                    else {  // Читаем без опаски
                        //  Если состояние объекта уже
                        // было в кэш, то выводим результат метода с обновлением времени
                        resultTime = stateResult.resultTime;
                        resultTime.time = System.nanoTime();
                        return resultTime.result;
                    }
                }
                else {      // В кэше состояния не найдено , записываем состояние , результат. время
                    result = method.invoke(this.casheObj, args);
                    time = System.nanoTime();
                    resultTime = new ResultTime(result, time);
                    ConcurrentHashMap<Field,Object> statesF = new ConcurrentHashMap<>();
                    statesF.putAll(states);
                    stateResult = new StateResult(statesF, resultTime  );
                    stateResultL = results.get(objMeth);;
                    stateResultL.add(stateResult);
                     cashVolume++;
                    if ( lisOptSize == 0 && cashVolume >= 1000)   {
                        lisOptSize = lisOptSizeCalc(cashTime,  cashVolume);
                    }
                    if (  lisOptSize > 0) {
                        if (((double) cashVolume / (double) lisOptSize - cashVolume / lisOptSize ) == 0 && lisOptSize > 0) {
                            threadStart();
                        }
                    }
                    results.put(objMeth, stateResultL );

                    return result;
                }
            }
            else {  // Если еще метода в кэше нет
                time = System.nanoTime();
                result  =  method.invoke(this.casheObj, args);
                resultTime = new ResultTime(result,  time);

                // оостояние объекта
               ConcurrentHashMap<Field,Object> statesF = new ConcurrentHashMap<>();
                for (Field  vField : this.casheObj.getClass().getDeclaredFields()) {
                    vField.setAccessible(true);
                    value = vField.get(this.casheObj);
                    statesF.put(vField,value);
                }
// Результат выполнения метода в завсимости от состояния + время наала жизни кэша
                stateResult = new StateResult(statesF, resultTime  );
                results = new ConcurrentHashMap<>();
                stateResultL = new ArrayList<>();
                stateResultL.add(stateResult);
                cashVolume++;
                if (lisOptSize == 0 && cashVolume >= 10000)   {
                    lisOptSize = lisOptSizeCalc(cashTime,  cashVolume);
            }
                if (  lisOptSize > 0) {
                    if ( ((double) cashVolume / (double) lisOptSize - cashVolume / lisOptSize) == 0 && lisOptSize > 0 ) {
                        threadStart();
                    }
                }
                results.put(objMeth,  stateResultL );
                 return result;
            }
       }
        if (objMeth.isAnnotationPresent(Mutator.class)) {

        }
        return method.invoke(this.casheObj, args);
    }
}

