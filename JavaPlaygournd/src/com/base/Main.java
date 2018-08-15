package com.base;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Streams s = new Streams();
        List<String> test = new ArrayList<>();
        test.add("AAA");
        test.add("BBB");
        test.add("CCC");
        test.add("Axx");

        System.out.println(s.search(test,"A"));
        System.out.println(s.search(test,"B"));
    }
}
