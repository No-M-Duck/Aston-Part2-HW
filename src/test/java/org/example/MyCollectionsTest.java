package org.example;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class MyCollectionsTest {


    @Test
    void sortWithComparator() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(5);
        list.add(2);
        list.add(8);
        list.add(1);

        MyCollections.sort(list, Comparator.naturalOrder());

        assertEquals(4, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(5, list.get(2));
        assertEquals(8, list.get(3));
    }

    @Test
    void sortWithCustomComparator() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("banana");
        list.add("apple");
        list.add("pear");

        MyCollections.sort(list, Comparator.comparingInt(String::length));

        assertEquals("pear", list.get(0));
        assertEquals("apple", list.get(1));
        assertEquals("banana", list.get(2));
    }

    @Test
    void sortThrowsClassCastException() {
        MyArrayList<Object> list = new MyArrayList<>();
        list.add(new Object());
        list.add(new Object());

        Exception exception = assertThrows(ClassCastException.class, () -> MyCollections.sort(list));
        assertTrue(exception.getMessage().contains("does not implement Comparable"));
    }

    @Test
    void sortEmptyList() {
        MyArrayList<Integer> list = new MyArrayList<>();
        MyCollections.sort(list);
        assertTrue(list.isEmpty());
    }

    @Test
    void sortSingleElementList() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(10);
        MyCollections.sort(list);
        assertEquals(1, list.size());
        assertEquals(10, list.get(0));
    }

    @Test
    void sortIdenticalElements() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(3);
        list.add(3);
        list.add(3);

        MyCollections.sort(list);

        assertEquals(3, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void sortAlreadySortedList() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        MyCollections.sort(list);

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void swapMethodTest() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        MyCollections.sort(list, Comparator.reverseOrder());
        assertEquals(3, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(1, list.get(2));
    }

}