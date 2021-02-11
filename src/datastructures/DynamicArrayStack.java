package datastructures;

import util.OutUtil;

import java.util.Iterator;

/**
 * This is a stack implemented using the same dynamic array concepts in {@link DynamicArray}. Basically, the end of our
 * array will represent the head of our stack. Whenever a new element is to be added, it will be added at the end of
 * the array. When an element is removed, it will be removed from the end of the stack.
 *
 * The benefit to this implementation is that it takes *overall* less time than a linked list implementation and will
 * also consume less memory. However, because of the occasional resize operation that is performed, this implementation
 * will occasionally have "spikes" in terms of performance.
 */
public class DynamicArrayStack<T> implements Iterable {
    private T[] array = (T[]) new Object[4];
    private int next;

    public void add(T element) {
        if(next >= array.length) {
            resize(array.length * 2);
        }

        array[next++] = element;
    }

    public T remove() {
        if(next <= 0) {
            return null;
        }

        T val = array[--next];
        array[next] = null;

        if(next <= array.length / 4) {
            resize(array.length / 2);
        }

        return val;
    }

    @SuppressWarnings("unchecked")
    private void resize(int size) {
        if(size < 4) {
            return;
        }
        T[] newarray = (T[]) new Object[size];

        for(int i = 0; i < newarray.length && i < array.length; i++) {
            newarray[i] = array[i];
        }

        this.array = newarray;
    }

    @Override
    public Iterator iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<T> {
        private int i = next;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public T next() {
            return array[--i];
        }
    }

    public static void main(String[] args) {
        DynamicArrayStack<String> stringStack = new DynamicArrayStack<>();

        // add the numbers 0 - 4 to our stack
        for(int i = 0; i < 5; i++) {
            stringStack.add(Integer.toString(i));
        }

        // demo printing using iterator
        OutUtil.iterablePrint(stringStack);

        // demo manual printing
        String val;
        while((val = stringStack.remove()) != null) {
            System.out.println(val);
        }

        System.out.println();

        // add and remove in succession
        for(int i = 0; i < 5; i++) {
            stringStack.add(Integer.toString(i));
            System.out.println(stringStack.remove());
        }
    }
}
