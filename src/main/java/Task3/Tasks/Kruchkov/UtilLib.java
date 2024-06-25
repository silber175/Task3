package Task3.Tasks.Kruchkov;

import java.lang.reflect.Proxy;

public class UtilLib {

    public static <T> T cashe(T objectIncome)  {
        return (T) Proxy.newProxyInstance(
                objectIncome.getClass().getClassLoader(),
                objectIncome.getClass().getInterfaces(),
                new MakeCache( objectIncome)
        );

    }

}
