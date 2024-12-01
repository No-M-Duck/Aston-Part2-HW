package org.example;

import java.util.Comparator;

/**
 * Utility class for working with custom implementations of lists.
 * Provides methods for sorting a custom `MyArrayList` using different approaches.
 */
public class MyCollections {

    /**
     * Sorts the given list using the specified comparator.
     *
     * @param list       the list to be sorted
     * @param comparator the comparator to determine the order of the list
     * @param <T>        the type of elements in the list
     * @throws NullPointerException if the list or comparator is null
     */
    public static <T> void sort(MyArrayList<T> list, Comparator<? super T> comparator) {
        quickSort(list, comparator, 0, list.size() - 1);
    }

    /**
     * Sorts the given list in its natural order.
     * Elements in the list must implement the {@link Comparable} interface.
     *
     * @param list the list to be sorted
     * @param <T>  the type of elements in the list
     * @throws ClassCastException   if any element in the list does not implement {@link Comparable}
     * @throws NullPointerException if the list is null
     */
    public static <T> void sort(MyArrayList<T> list) {
        quickSortComparable(list, 0, list.size() - 1);
    }

    /**
     * Performs a quick sort on the list, assuming all elements implement {@link Comparable}.
     *
     * @param list the list to be sorted
     * @param low  the starting index
     * @param high the ending index
     * @param <T>  the type of elements in the list
     */
    private static <T> void quickSortComparable(MyArrayList<T> list, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(list, low, high);
            quickSortComparable(list, low, pivotIndex - 1);
            quickSortComparable(list, pivotIndex + 1, high);
        }
    }

    /**
     * Partitions the list for quick sort using the last element as the pivot.
     *
     * @param list the list to be partitioned
     * @param low  the starting index
     * @param high the ending index
     * @param <T>  the type of elements in the list
     * @return the index of the pivot element after partitioning
     * @throws ClassCastException if any element in the list does not implement {@link Comparable}
     */
    @SuppressWarnings("unchecked")
    private static <T> int partition(MyArrayList<T> list, int low, int high) {
        T pivot = list.get(high);

        if (!(pivot instanceof Comparable)) {
            throw new ClassCastException("Element does not implement Comparable: " + pivot);
        }

        Comparable<T> comparablePivot = (Comparable<T>) pivot;

        int wall = low - 1;
        for (int i = low; i < high; i++) {
            T current = list.get(i);

            if (!(current instanceof Comparable)) {
                throw new ClassCastException("Element does not implement Comparable: " + current);
            }

            Comparable<T> comparableCurrent = (Comparable<T>) current;

            if (comparableCurrent.compareTo((T) pivot) < 0) {
                wall++;
                swap(list, wall, i);
            }
        }
        swap(list, wall + 1, high);
        return wall + 1;
    }

    /**
     * Performs a quick sort on the list using a custom comparator.
     *
     * @param list       the list to be sorted
     * @param comparator the comparator to determine the order of the list
     * @param low        the starting index
     * @param high       the ending index
     * @param <T>        the type of elements in the list
     */
    private static <T> void quickSort(MyArrayList<T> list,
                                      Comparator<? super T> comparator,
                                      int low, int high) {
        if (low < high) {
            int pivotIndex = part(list, low, high, comparator);
            quickSort(list, comparator, low, pivotIndex - 1);
            quickSort(list, comparator, pivotIndex + 1, high);
        }
    }

    /**
     * Partitions the list for quick sort using the last element as the pivot and a custom comparator.
     *
     * @param list       the list to be partitioned
     * @param low        the starting index
     * @param high       the ending index
     * @param comparator the comparator to determine the order of the elements
     * @param <T>        the type of elements in the list
     * @return the index of the pivot element after partitioning
     */
    private static <T> int part(MyArrayList<T> list,
                                int low, int high,
                                Comparator<? super T> comparator) {
        T pivot = list.get(high);
        int wall = low - 1;
        for (int index = low; index < high; index++) {
            if (comparator.compare(list.get(index), pivot) < 0) {
                wall++;
                swap(list, wall, index);
            }
        }
        swap(list, wall + 1, high);
        return wall + 1;
    }

    /**
     * Swaps two elements in the list.
     *
     * @param list the list in which elements are to be swapped
     * @param i    the index of the first element
     * @param j    the index of the second element
     * @param <T>  the type of elements in the list
     */
    private static <T> void swap(MyArrayList<T> list, int i, int j) {
        T temp = list.get(i);
        list.replace(list.get(j), i);
        list.replace(temp, j);
    }

}
