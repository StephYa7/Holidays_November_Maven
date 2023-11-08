package FirstPackage.Reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectionFirst {
    public static void main(String[] args) {
        Class clazz = ClassNeedForReflectTest.class;
        Field[] fields = clazz.getDeclaredFields();
        Method[] methods = clazz.getMethods();
        System.out.println(Arrays.toString(fields));
        for (Method field : methods) {
            System.out.println(field);

        }
    }

    public void shouldBackPositiveInteger(){

    }

}