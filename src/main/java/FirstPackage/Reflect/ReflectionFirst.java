package FirstPackage;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Gg{
    public static void main(String[] args) {
        Class clazz = FirstClass.class;
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