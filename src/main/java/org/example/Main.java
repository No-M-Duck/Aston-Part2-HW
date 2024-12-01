package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>(10000);
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }
        for (int i = 0; i < 10000; i++) {
            System.out.println(list.get(i));;
        }
        for (int i = 0; i < 10000; i++) {
            list.replace(i*2,i);
        }
        for (int i = 0; i < 10000; i++) {
            list.remove(0);
        }

    }
}