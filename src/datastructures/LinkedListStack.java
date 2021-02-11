package datastructures;


import util.OutUtil;

import java.util.Iterator;

/**
 * This is the linked list implementation of a LIFO (Last-In-First-Out) stack. Each item in the stack is represented as a node,
 * and nodes are chained together in a way that fulfills the stack interface.
 *
 * To add a node, a new node is created, and that node's "next" pointer is set to the first element of the current linked list.
 * Then, the first element is set as the node that was just created.
 *
 * To remove an item from the top of the stack, simply return the value of the first element, and then set the first element to the "next"
 * pointer of the current first element.
 *
 * Every operation on this stack runs in constant time in the worst case. The deficit to this implementation would be that using a new object
 * for each element in the list creates both time overhead and memory overhead. Compared to an array implementation, this stack have consistent
 * times to add and remove elements, however the time may not be as low.
 */
public class LinkedListStack<T> implements Iterable<T> {
    private Node first;

    public void add(T element) {
        Node n = new Node();
        n.next = first;
        n.value = element;

        this.first = n;
    }

    public T remove() {
        if(this.first == null) {
            return null;
        }

        T val = first.value;
        this.first = first.next;

        return val;
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }

    /**
     * A node class that represents a single "link" in the linked list.
     */
    private class Node {
        Node next;
        T value;
    }

    /**
     * The iterator returned by the iterator() method
     */
    private class StackIterator implements Iterator<T> {
        private Node cur = first;

        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public T next() {
            T val = cur.value;
            cur = cur.next;

            return val;
        }
    }

    public static void main(String[] args) {
        LinkedListStack<String> stringStack = new LinkedListStack<>();

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
