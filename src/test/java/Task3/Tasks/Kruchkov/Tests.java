package Task3.Tasks.Kruchkov;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.awt.*;
import java.util.stream.IntStream;


// Тестирование выполняется по 2 методам 2 классов Fraction and Circule
public class Tests {

    @Test
    // Тестирование количества обращений к методу высления знаения о 2-м методам 2-х классов
    public void cashCheck()  {
        try {


            Fraction fr = new Fraction(7, 8);
            Fractionable casheNum = UtilLib.cashe(fr);

            Point point = new Point(1,1);
            Point point2 = new Point(2,2);
            Circule cr = new Circule(point, 1);
            Circuleable casheCr = UtilLib.cashe(cr);

            fr.forTest.count = 0;
            fr.forTest.countR= 0;

            cr.count = 0;
            cr.countP = 0;
            casheNum.doubleValue();
            casheNum.doubleValue();
            casheNum.reverseValue();
            casheNum.reverseValue();

            casheCr.diametr();
            casheCr.diametr();
            casheCr.circusLen();
            casheCr.circusLen();

            casheNum.setNum(3);
            casheNum.doubleValue();
            casheNum.doubleValue();
            casheNum.reverseValue();
            casheNum.reverseValue();

            casheCr.setRadius(25);
            casheCr.diametr();
            casheCr.diametr();
            casheCr.circusLen();
            casheCr.circusLen();


            casheNum.setDenum(4);
            casheNum.doubleValue();
            casheNum.doubleValue();
            casheNum.reverseValue();
            casheNum.reverseValue();

            casheCr.setCentre(point2);
            casheCr.diametr();
            casheCr.diametr();
            casheCr.circusLen();
            casheCr.circusLen();

            Assertions.assertEquals( 3 , fr.forTest.count ,"Каш 1 класса 1 не работает " + fr.forTest.count);

            Assertions.assertEquals( 3 , fr.forTest.countR ,"Каш 2 класса 1  не работает " + fr.forTest.countR);

            Assertions.assertEquals( 3 , cr.count ,"Каш 1 класса 2 не работает " + cr.count);

            Assertions.assertEquals(3 , cr.countP ,"Каш 2 класса 2  не работает " + cr.countP);

        }
        catch (Exception e) {
            throw new  IllegalArgumentException(" Ошибка либо в классе либо в тесте , неудачная инициализация кэша " + e.getLocalizedMessage());

        }
    }
    @Test
    //
    public void valueCheck() {


        try {
            Fraction fr = new Fraction(16, 8);
            Fractionable casheNum = UtilLib.cashe(fr);



            Point point = new Point(10, 101);
            Point point2 = new Point(22, 212);
            Circule cr = new Circule(point, 15);
            Circuleable casheCr = UtilLib.cashe(cr);


            Assertions.assertEquals( fr.doubleValue(), casheNum.doubleValue() , "Неверное вычисление кэш значения 1 метода 1 класса 1 "+fr.doubleValue()+" "+ casheNum.doubleValue());
            Assertions.assertEquals( casheNum.reverseValue() , fr.reverseValue() , "Неверное вычисление кэш значения 1 метода 2 класса 1 "+casheNum.reverseValue() +" "+ fr.reverseValue());

            Assertions.assertEquals( cr.diametr(), casheCr.diametr() , "Неверное вычисление кэш значения 1 метода 1 класса 2 "+cr.diametr() +" "+casheCr.diametr());
            Assertions.assertEquals( cr.circusLen(), casheCr.circusLen() , "Неверное вычисление кэш значения 1 метода 2 класса 2 "+cr.circusLen() +" "+ casheCr.circusLen());


            casheNum.setNum(4);
            Assertions.assertEquals( fr.doubleValue(), casheNum.doubleValue() , "Неверное вычисление кэш значения 2 метода 1 класса 1 "+fr.doubleValue()+" "+ casheNum.doubleValue());
            Assertions.assertEquals( casheNum.reverseValue() , fr.reverseValue() , "Неверное вычисление кэш значения 2 метода 2 класса 1 "+casheNum.reverseValue() +" "+ fr.reverseValue());
            Assertions.assertEquals( fr.doubleValue(), casheNum.doubleValue() , "Неверное вычисление кэш значения 3 метода 1 класса 1 "+fr.doubleValue()+" "+ casheNum.doubleValue());
            Assertions.assertEquals( casheNum.reverseValue() , fr.reverseValue() , "Неверное вычисление кэш значения 3 метода 2 класса 1 "+casheNum.reverseValue() +" "+ fr.reverseValue());

            Assertions.assertEquals( cr.diametr(), casheCr.diametr() , "Неверное вычисление кэш значения 2 метода 1 класса 2 "+cr.diametr() +" "+casheCr.diametr());
            Assertions.assertEquals( cr.circusLen(), casheCr.circusLen() , "Неверное вычисление кэш значения 2 метода 2 класса 2 "+cr.circusLen() +" "+ casheCr.circusLen());
            Assertions.assertEquals( cr.diametr(), casheCr.diametr() , "Неверное вычисление кэш значения 3 метода 1 класса 2 "+cr.diametr() +" "+casheCr.diametr());
            Assertions.assertEquals( cr.circusLen(), casheCr.circusLen() , "Неверное вычисление кэш значения 3 метода 2 класса 2 "+cr.circusLen() +" "+ casheCr.circusLen());


            casheNum.setDenum(32);
            Assertions.assertEquals( fr.doubleValue(), casheNum.doubleValue() , "Неверное вычисление кэш значения 4 метода 1 класса 1 "+fr.doubleValue()+" "+ casheNum.doubleValue());
            Assertions.assertEquals( casheNum.reverseValue() , fr.reverseValue() , "Неверное вычисление кэш значения 2 метода 4 класса 1 "+casheNum.reverseValue() +" "+ fr.reverseValue());
            Assertions.assertEquals( fr.doubleValue(), casheNum.doubleValue() , "Неверное вычисление кэш значения 4 метода 1 класса 1 "+fr.doubleValue()+" "+ casheNum.doubleValue());
            Assertions.assertEquals( casheNum.reverseValue() , fr.reverseValue() , "Неверное вычисление кэш значения 4 метода 2 класса 1 "+casheNum.reverseValue() +" "+ fr.reverseValue());

            Assertions.assertEquals( cr.diametr(), casheCr.diametr() , "Неверное вычисление кэш значения 5 метода 1 класса 2 "+cr.diametr() +" "+casheCr.diametr());
            Assertions.assertEquals( cr.circusLen(), casheCr.circusLen() , "Неверное вычисление кэш значения 5 метода 2 класса 2 "+cr.circusLen() +" "+ casheCr.circusLen());
            Assertions.assertEquals( cr.diametr(), casheCr.diametr() , "Неверное вычисление кэш значения 5 метода 1 класса 2 "+cr.diametr() +" "+casheCr.diametr());
            Assertions.assertEquals( cr.circusLen(), casheCr.circusLen() , "Неверное вычисление кэш значения 5 метода 2 класса 2 "+cr.circusLen() +" "+ casheCr.circusLen());


        } catch (Exception e) {
            throw new IllegalArgumentException("Ошибка либо в классе либо в тесте , Неверное вычисление  значения кэша " + e.getLocalizedMessage());
        }
    }

    @Test
    public void initCheck() {
        Fraction fr = null;
        Fractionable casheNum = null;
        Circule cr = null;
        Circuleable casheCr = null;
        Point point = new Point(1,1);

              try {
            fr = new Fraction(16, 8);
            casheNum = UtilLib.cashe(fr);
            cr = new Circule(point, 1);
            casheCr = UtilLib.cashe(cr);

            Assertions.assertEquals( fr.doubleValue() , casheNum.doubleValue() , "неудачная инициализация кэша класса 1 "+fr.doubleValue() + " "+  casheNum.doubleValue());
            Assertions.assertEquals( cr.diametr() , casheCr.diametr() , "неудачная инициализация кэша  класса 2 "+cr.diametr() +" "+ casheCr.diametr());

        }
        catch (Exception e) {
            throw new  IllegalArgumentException(" Ошибка либо в классе либо в тесте , неудачная инициализация кэша " + e.getLocalizedMessage());
        }

        try {
            Assertions.assertEquals( fr.doubleValue() , casheNum.doubleValue()  , "Неверное вычисление кэш значения метода 1класса 1 "+fr.doubleValue()+" "+ casheNum.doubleValue());
            Assertions.assertEquals( cr.diametr() , casheCr.diametr() , "Неверное вычисление кэш значения метода 1 класса 2 "+cr.diametr() +" "+casheCr.diametr());
        }
        catch (Exception e) {
            throw new  IllegalArgumentException(" Ошибка либо в классе либо в тесте , неудачная выборка 1 из  кэша " + e.getLocalizedMessage());
        }
        try {
            Assertions.assertEquals( casheNum.reverseValue() , fr.reverseValue() , "Неверное вычисление кэш значения метода 2 класса 1 "+casheNum.reverseValue() +" "+ fr.reverseValue());
            Assertions.assertEquals( cr.circusLen() , casheCr.circusLen() , "Неверное вычисление кэш 2-го метода значения метода 2 класса 2 "+cr.circusLen() +" "+ casheCr.circusLen());

        }
        catch (Exception e)    {
            throw new  IllegalArgumentException("Ошибка либо в классе либо в тесте , Неверное вычисление обратного значения 1 " + e.getLocalizedMessage());
        }

    }
    // Сборщк мусора трудно тестировать, проверяю, что содержание кэша меньше . чем было при очистки и удаление элементов вывожу в консоль
    @Test
    public void threadTest()    {
        long count =0;
        long logStep = 10000;
        Fraction fr = new Fraction(16, 8);
        Fractionable casheNum = UtilLib.cashe(fr);
    //    try {
        for(int ll=0;ll<100;ll++)   {
            long steps = 200;
            long subSteps = 60;
            for (int y=1; y <= steps; y++) {
                casheNum.setNum(y);
                for (int x=1;x<=subSteps; x++) {
                    casheNum.setDenum(x);
                    double res = casheNum.doubleValue();
                    count++;
                    if (ll== 0) {
                        if (((double) count / (double) logStep - count / logStep) == 0) {
                            System.out.println("Выполнено вычисление " + count + " значений");
                        }
                    }
                }
            }
            Assertions.assertNotEquals((subSteps * steps), fr.forTest.count ," кэш не очищается");
        }
     //   }
    //    catch (Exception e)    {
    //        throw new  IllegalArgumentException("Ошибка либо в классе либо в тесте , при проверке работы потоков " + e.getLocalizedMessage());
    //    }

    }
    @Test
    public void bigCasheTest()    {
        Fraction fr = new Fraction(16, 8);
        Fractionable casheNum = UtilLib.cashe(fr);
        long steps = 20;
        long subSteps = 20;
        try {
            for(int ll=0;ll<10;ll++) {
                for (int y = 1; y <= steps; y++) {
                    casheNum.setNum(y);
                    for (int x = 1; x <= subSteps; x++) {
                        casheNum.setDenum(x);
                        double res = casheNum.doubleValue();
                    }
                }
                if ((subSteps * steps) < fr.forTest.count) {
                    Assertions.assertEquals((subSteps * steps), fr.forTest.count, "В кэш записываются совпадающие значения");
                }
            }
        }
       catch (Exception e)    {
            throw new  IllegalArgumentException("Ошибка либо в классе либо в тесте , при проверке работы большого кэша " + e.getLocalizedMessage());
        }
    }
}
