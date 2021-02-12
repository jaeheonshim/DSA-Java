package datastructures;

import util.OutUtil;

import java.util.Iterator;
import java.util.Random;

/**
 * This is an example of a Queue, or a FIFO (First In First Out) data structure implemented using linked list features.
 *
 * To add a node, we will add it to the end of the list by retrieving the last element and setting it's pointer to the
 * new node. Therefore, a reference to the last node will always be kept.
 *
 * To remove a node, we can simply take it from the front, and set the front node to the previous front node's pointer.
 */
public class LinkedListQueue<T> implements Iterable<T> {
    private Node first;
    private Node last;

    private class Node {
        T value;
        Node next;
    }

    public void enqueue(T item) {
        Node n = new Node();
        n.value = item;

        if(last == null) {
            // if the last node is null, we need to set both the first node and last node pointers to our new node.
            this.first = this.last = n;
        } else {
            last.next = n;
            last = n;
        }
    }

    public T dequeue() {
        if(first == null) {
            return null;
        }

        T item = first.value;
        first = first.next;

        // if all of the elements have been removed, the last element should be removed as well
        if(first == null) {
            last = null;
        }

        return item;
    }

    @Override
    public Iterator<T> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<T> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T val = current.value;
            current = current.next;

            return val;
        }
    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();

        for(int i = 0; i < 10; i++) {
            linkedListQueue.enqueue(i);
        }

        for(int i = 0; i < 10; i++) {
            System.out.println(linkedListQueue.dequeue());
        }

        System.out.println();
        System.out.println("Test iterator");

        for(int i = 0; i < 10; i++) {
            linkedListQueue.enqueue(i);
        }

        OutUtil.iterablePrint(linkedListQueue);

        linkedListQueue = new LinkedListQueue<>();

        System.out.println();
        System.out.println("Repeatedly enqueue and dequeue");

        for(int i = 0; i < 10; i++) {
            linkedListQueue.enqueue(i);
            System.out.println(linkedListQueue.dequeue());
        }

        System.out.println();
        System.out.println("Empty");
        System.out.println(linkedListQueue.dequeue());
    }
}
