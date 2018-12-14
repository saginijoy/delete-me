package com.cognizant.covr;


import org.springframework.stereotype.Component;


public class VisitorCounter {
    private static int counter;

    public static void setCounter(int count) {
        counter=count;

    }
    public static int incrementCounterAndGet() {
        counter+=1;
        return counter;

    }

}
