package datastructures;

import util.OutUtil;

import java.util.Iterator;

/**
 * This is a "Dynamic" array implemented by resizing a "primitive" array within the code. It works similarly to Java's
 * ArrayList.
 *
 * The array will start out at a fixed size. When the size of elements in that array reaches the maximum size of the
 * array, we double the size of the array. By doubling the size every time, each operation can be run in "amortized"
 * constant time, with the sense that we "pay" for the resizing operation
 * every time we insert an element into the array without resizing.
 *
 * When the number of elements in the array becomes 1/4th of the size of the array, we will halve the size of the array.
 * We do this at 1/4th capacity because if we were to do it at 1/2 capacity, in a scenario where elements are added and
 * removed on a full array, the array would keep being grown and shrunk over and over again, leading to massive
 * inefficiency.
 */
public class DynamicArray<T> implements Iterable<T> {
    T[] arr;
    int nextpos; // This will represent the next possible index an element can be inserted. It also acts as the size of elements currently in the array.

    @SuppressWarnings("unchecked")
    public DynamicArray() {
        arr = (T[]) new Object[4]; // it is impossible to create a generic array in Java
    }

    public void add(T element) {
        if(arr.length >= nextpos) {
            // array must be resized!
            resize(arr.length * 2);
        }

        arr[nextpos] = element;
        nextpos++;
    }

    public T get(int i) {
        if(i >= nextpos) {
            throw new IndexOutOfBoundsException();
        }

        return arr[i];
    }

    public void remove(int i) {
        if(i < 0 || i >= nextpos) {
            throw new IndexOutOfBoundsException();
        }

        nextpos--;
        arr[i] = null; // this is important, as retaining a reference could lead to unforeseen memory issues

        // now elements are shifted back one.
        for(int j = i + 1; j < arr.length; j++) {
            arr[j - 1] = arr[j];
        }

        // if element size is less than/equal to 1/4 capacity
        if(nextpos == arr.length / 4) {
            resize(arr.length / 2);
        }
    }

    public int size() {
        return nextpos;
    }

    @SuppressWarnings("unchecked")
    private void resize(int cap) {
        if(cap < 4) {
            return;
        }

        T[] newArr = (T[]) new Object[cap];

        // here, we have the conditions i < newArr.length AND i < arr.length. This is so that the resize method can be used for shrinking as well as growing, in which case the size of the target array will be less than the size of the source.
        for(int i = 0; i < newArr.length && i < arr.length; i++) {
            newArr[i] = arr[i];
        }

        this.arr = newArr;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T> {
        private int i;

        @Override
        public boolean hasNext() {
            return i < size();
        }

        @Override
        public T next() {
            return get(i++);
        }
    }

    public static void main(String[] args) {
        // shitty unit testing
        DynamicArray<Integer> dynamicArray = new DynamicArray<>();

        // adding an element
        dynamicArray.add(1);

        // getting that element
        System.out.println(dynamicArray.get(0));

        // getting an element at an invalid index
        try {
            System.out.println("Retrieving at invalid index!");
            System.out.println(dynamicArray.get(1));
        } catch (RuntimeException e) {
            e.printStackTrace(System.out);
        }

        System.out.println();

        // removing element at invalid index
        try {
            System.out.println("Removing at invalid index!");
            dynamicArray.remove(1);
        } catch (RuntimeException e) {
            e.printStackTrace(System.out);
        }

        System.out.println();

        // removing element at valid index
        dynamicArray.remove(0);

        // retrieving element at now invalid index
        try {
            System.out.println("Retrieving at invalid index that used to be valid!");
            System.out.println(dynamicArray.get(0));
        } catch (RuntimeException e) {
            e.printStackTrace(System.out);
        }

        System.out.println();

        System.out.printf("Size of array: %d%n", dynamicArray.size());

        // test adding
        for(int i = 0; i < 5; i++) {
            dynamicArray.add(i);
        }

        OutUtil.iterablePrint(dynamicArray);

        // test printing one by one
        for(int i = 0; i < dynamicArray.size(); i++) {
            System.out.println(dynamicArray.get(i));
        }
    }
}
