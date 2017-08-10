package com.lopframework.lop.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

public class InvokeTest {
    
    @Test
    public void test() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Invoker in = new Invoker();
        
        Class clazz = Invoker.class;
        Method m = clazz.getDeclaredMethod("print");
        System.out.println(m.getParameterTypes().length);
        m.invoke(in, "asdf");
    }
    
    private class Invoker {
        
        public void print() {
            System.out.println("hello");
        }
    }
}
