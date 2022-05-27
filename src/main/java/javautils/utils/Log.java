package javautils.utils;

public class Log {
    public static <T> void info(T... objects){
        for(T o: objects){
            out(o);
        }
    }

    protected static <T> void out(T val){
        System.out.println(val);
    }
}
