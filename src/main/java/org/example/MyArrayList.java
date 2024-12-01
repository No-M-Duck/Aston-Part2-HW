package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

/**
 * A custom implementation of an ArrayList that supports generic types.
 *
 * <p>This class provides basic functionality similar to {@link java.util.ArrayList},
 * such as adding, removing, and retrieving elements. It dynamically resizes the internal
 * array as elements are added, ensuring efficient memory usage.
 *
 * @param <T> the type of elements stored in this list
 */
public class MyArrayList<T> {
    /**
     * Internal array for storing elements.
     */
    private Object[] elementData;
    /**
     * The number of elements currently in the list.
     */
    private int size;
    /**
     * Default initial capacity of the list.
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * An empty array used for lists with an initial capacity of zero.
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};

    /**
     * Creates a list with the default capacity.
     */
    public MyArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Creates a list with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the list
     * @throws IllegalArgumentException if the initial capacity is negative
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        }
    }

    /**
     * Checks if the list is empty.
     *
     * @return {@code true} if the list is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Converts the list to an array containing all of its elements.
     *
     * @return an array containing all elements of the list
     */
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element the element to be added
     */
    public void add(T element) {
        ensureCapacity(size + 1);
        elementData[size++] = element;
    }

    /**
     * Retrieves the element at the specified index.
     *
     * @param index the index of the element to retrieve
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        rangeCheck(index);
        return (T) elementData[index];
    }

    /**
     * Removes the element at the specified index.
     *
     * @param index the index of the element to remove
     * @return the removed element
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        rangeCheck(index);
        T removeElem = (T) elementData[index];
        int move = size - index - 1;
        if (move > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, move);
        }
        elementData[--size] = null;
        return removeElem;
    }

    /**
     * Removes the first occurrence of the specified element from the list, if present.
     *
     * @param o the element to remove
     * @return {@code true} if the element was removed; {@code false} otherwise
     */
    public boolean remove(Object o) {
        final Object[] localElems = elementData;
        final int size = this.size;
        int i = 0;
        found:
        {
            if (o == null) {
                for (; i < size; i++)
                    if (localElems[i] == null)
                        break found;
            } else {
                for (; i < size; i++)
                    if (o.equals(localElems[i]))
                        break found;
            }
            return false;
        }
        removeObj(localElems, i);
        return true;
    }

    /**
     * Removes an element at the specified index and shifts subsequent elements to the left.
     *
     * @param elements the array of elements
     * @param i        the index of the element to remove
     */
    private void removeObj(Object[] elements, int i) {
        final int newSize;
        if ((newSize = size - 1) > i)
            System.arraycopy(elements, i + 1, elements, i, newSize - i);
        elements[size = newSize] = null;
    }

    /**
     * Adds an element at the specified index.
     *
     * @param element the element to add
     * @param index   the index at which the element is to be added
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void add(T element, int index) {
        rangeCheck(index);
        ensureCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    /**
     * Removes all elements from the list.
     */
    public void clear() {
        final Object[] localElems = elementData;
        for (int start = size, i = size = 0; i < start; i++)
            localElems[i] = null;
    }

    /**
     * Replaces the element at the specified index with a new element.
     *
     * @param element the new element
     * @param index   the index of the element to replace
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void replace(T element, int index) {
        rangeCheck(index);
        elementData[index] = element;
    }

    /**
     * Ensures that the list has enough capacity to hold the specified minimum number of elements.
     *
     * @param minCapacity the desired minimum capacity
     */
    public void ensureCapacity(int minCapacity) {
        if (minCapacity > elementData.length) {
            int newCapacity = elementData.length + (elementData.length >> 1);
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    /**
     * Checks if the index is within the valid range.
     *
     * @param index the index to check
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    private void rangeCheck(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(
                    String.format("Index %d, Size %d", index, size));
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return the number of elements in the list
     */
    public int size() {
        return size;
    }
}
