package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyArrayListTest {

    @Test
    void toArray() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(2);
        Object[] array = list.toArray();

        assertArrayEquals(new Object[]{1, 2}, array, "ToArray FAIL");
    }

    @Test
    void toArrayEmptyList() {
        MyArrayList<Integer> list = new MyArrayList<>();
        Object[] array = list.toArray();

        assertArrayEquals(new Object[]{}, array, "toArrayEmptyList FAIL");
    }

    @Test
    void isEmptyList(){
        MyArrayList<Integer> list = new MyArrayList<>();
        boolean emptyY = list.isEmpty();
        list.add(1);
        boolean emptyN = list.isEmpty();

        assertTrue(emptyY);
        assertFalse(emptyN);
    }

    @Test
    void add() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("A");
        list.add("B");

        assertEquals(2, list.size(), "Add element FAIL. Incorrect list size value");
        assertEquals("A", list.get(0), "Add element FAIL. Invalid value of list item by index");
        assertEquals("B", list.get(1), "Add element FAIL. Invalid value of list item by index");
    }

    @Test
    void addNull() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add(null);

        assertEquals(1, list.size(), "Add null FAIL. Incorrect list size value");
        assertNull(list.get(0), "Add null FAIL. Invalid value of list item by index");
    }

    @Test
    void addByIndex() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("A");
        list.add("C");
        list.add("B", 1);

        assertEquals(3, list.size(), "addByIndex FAIL. Incorrect list size");
        assertEquals("B", list.get(1), "addByIndex FAIL. Incorrect value of element after add by index");
        assertEquals("C", list.get(2), "addByIndex FAIL. Array elements shift error after inserting an element by index");
    }

    @Test
    void get() {
        MyArrayList<Double> list = new MyArrayList<>();
        list.add(5.5);

        assertEquals(5.5, list.get(0), "Get element FAIL. Invalid value of list item by index");
    }

    @Test
    void getInvalidIndex() {
        MyArrayList<Double> list = new MyArrayList<>();
        list.add(5.5);

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1), "getInvalidIndex FAIL. No exception has been granted");
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1), "getInvalidIndex FAIL. No exception has been granted");

    }

    @Test
    void addByIndexInvalid() {
        MyArrayList<String> list = new MyArrayList<>();

        assertThrows(IndexOutOfBoundsException.class, () -> list.replace("B", 0), "addByIndexInvalid FAIL. No exception has been granted");
    }

    @Test
    void remove() {
        MyArrayList<Character> list = new MyArrayList<>();
        list.add('A');
        list.add('B');

        Character removed = list.remove(0);

        assertEquals('A', removed, "Remove index FAIL. The deleted element does not match the pattern");
        assertEquals(1, list.size(), "Remove index FAIL. Incorrect list size after deleting an item");
        assertEquals('B', list.get(0), "Remove index FAIL. Incorrect item value after deletion");
    }

    @Test
    void removeByIndexInvalid() {
        MyArrayList<Character> list = new MyArrayList<>();

        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0), "removeInvalidIndex FAIL. No exception has been granted");
    }


    @Test
    void removeObj() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("A");
        list.add("B");

        boolean result = list.remove("A");

        assertTrue(result, "Remove object FAIL. The method could not find an item to delete");
        assertEquals(1, list.size(), "Remove object FAIL. Incorrect list size after deleting an item");
        assertEquals("B", list.get(0), "Remove object FAIL. Incorrect item value after deletion");
    }

    @Test
    void removeNullObj() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("A");
        list.add(null);


        boolean result = list.remove(null);

        assertTrue(result, "Remove object FAIL. The method could not find an item to delete");
        assertEquals(1, list.size(), "Remove object FAIL. Incorrect list size after deleting an item");
        assertEquals("A", list.get(0), "Remove object FAIL. Incorrect item value after deletion");
    }

    @Test
    void removeObjNotFound() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("A");

        boolean result = list.remove("B");

        assertFalse(result, "removeObjNotFound FAIL. Deleted an item that does not exist");
        assertEquals(1, list.size(), "removeObjNotFound FAIL. Incorrect list size");
    }

    @Test
    void removeObjEmptyList() {
        MyArrayList<String> list = new MyArrayList<>();

        boolean result = list.remove("B");

        assertFalse(result, "removeObjNotFound FAIL. Deleted an item that does not exist");
    }

    @Test
    void clear() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("A");
        list.add("B");

        list.clear();

        assertEquals(0, list.size(), "Clear FAIL. Incorrect list size");
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0), "Clear FAIL. Incorrect item value after deletion");
    }

    @Test
    void replace() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("A");

        list.replace("B", 0);

        assertEquals("B", list.get(0), "Replace FAIL. Element replacement error");
    }

    @Test
    void replaceInvalidIndex() {
        MyArrayList<String> list = new MyArrayList<>();

        assertThrows(IndexOutOfBoundsException.class, () -> list.replace("B", 0), "replaceInvalidIndex FAIL. No exception has been granted");
    }

    @Test
    void size() {
        MyArrayList<String> list = new MyArrayList<>();
        assertEquals(0, list.size());

        list.add("A");
        assertEquals(1, list.size());

        list.remove(0);
        assertEquals(0, list.size());
    }

    @Test
    void checkCapacity(){
        MyArrayList<Integer> arrayList = new MyArrayList<>(1);
        assertThrows(IllegalArgumentException.class,()-> new MyArrayList<>(-4));
    }

    @Test
    void checkCRUDList(){
        MyArrayList<Integer> list = new MyArrayList<>(10000);
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }
        assertEquals(10000,list.size());
        for (int i = 0; i < 10000; i++) {
            System.out.println(list.get(i));;
        }
        assertEquals(10000,list.size());
        for (int i = 0; i < 10000; i++) {
            list.replace(i*2,i);
        }
        assertEquals(10000,list.size());
        for (int i = 0; i < 10000; i++) {
            list.remove(0);
        }
        assertEquals(0,list.size());
    }
}