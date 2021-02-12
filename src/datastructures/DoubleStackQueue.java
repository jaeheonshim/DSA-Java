package datastructures;

import util.OutUtil;

import java.util.Iterator;

/**
 * This is an interesting implementation of a queue using two stacks.
 *
 * This concept works on the fact that if you add elements to a stack, and then pop those elements off one by one and
 * put them in another stack, they will contain the elements in the first stack, only in reversed order.
 *
 * We have two stacks, s1 and s2. Every time we want to add an element, we can simply put it in s1. Whenever we need to
 * remove an element, we do the following:
 *
 * If s2 is empty, we will remove every element from s1 (one by one) and put it in s2 until s1 is empty. Now, s2 will
 * contain s1's elements in reverse order.
 *
 * Otherwise, we return the top element from s2.
 *
 * Here's a demonstration
 *
 * Let's add the numbers 1, 2, 3, 4 to a stack.
 *
 * s1: [4, 3, 2, 1]
 *
 * Above is the resulting stack, since the new elements get "stacked" on top of the previous elements.
 *
 * Now, let's say we want to remove an element.
 *
 * Our other stack (s2) is currently empty, so we can pop every element off of stack s1 and put it in s2.
 *
 * s2: [1, 2, 3, 4]
 *
 * Now we have stack s2 in the correct order for removal in order to correctly implement a queue.
 *
 * We will use the LinkedListStack created in the other class.
 */
public class DoubleStackQueue<T> implements Iterable<T> {
    private LinkedListStack<T> s1 = new LinkedListStack<>();
    private LinkedListStack<T> s2 = new LinkedListStack<>();

    public boolean isEmpty() {
        return s1.isEmpty() && s2.isEmpty();
    }

    public void enqueue(T item) {
        s1.add(item);
    }

    public T dequeue() {
        if(s2.isEmpty()) {
            if(s1.isEmpty()) {
                return null; // stack is empty
            }

            while(!s1.isEmpty()) {
                s2.add(s1.remove());
            }
        }

        return s2.remove();
    }

    @Override
    public Iterator<T> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<T> {
        private LinkedListStack<T> s = new LinkedListStack<>();
        private Iterator<T> s2it = s2.iterator();

        public QueueIterator() {
            for(T item : s1) {
                s.add(item);
            }
        }

        @Override
        public boolean hasNext() {
            return s2it.hasNext() || !s.isEmpty();
        }

        @Override
        public T next() {
            if(s2it.hasNext()) {
                return s2it.next();
            } else {
                return s.remove();
            }
        }
    }

    public static void main(String[] args) {
        DoubleStackQueue<Integer> doubleStackQueue = new DoubleStackQueue<>();

        for(int i = 0; i < 10; i++) {
            doubleStackQueue.enqueue(i);
        }

        for(int i = 0; i < 10; i++) {
            System.out.println(doubleStackQueue.dequeue());
        }

        System.out.println();
        System.out.println("Test iterator");

        for(int i = 0; i < 10; i++) {
            doubleStackQueue.enqueue(i);
        }

        OutUtil.iterablePrint(doubleStackQueue);

        doubleStackQueue = new DoubleStackQueue<>();

        System.out.println();
        System.out.println("Repeatedly enqueue and dequeue");

        for(int i = 0; i < 10; i++) {
            doubleStackQueue.enqueue(i);
            System.out.println(doubleStackQueue.dequeue());
        }

        System.out.println();
        System.out.println("Empty");
        System.out.println(doubleStackQueue.dequeue());
    }
}
