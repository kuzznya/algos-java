package com.github.kuzznya.ds.list;

public class SinglyLinkedList<T> implements MyList<T> {

    private Node<T> first;
    private Node<T> last;
    int size = 0;

    @Override
    public T get(int idx) {
        if (idx < 0 || idx >= size)
            throw new IndexOutOfBoundsException();

        Node<T> node = first;
        for (int i = 0; i < idx; i++)
            node = node.next;

        return node.value;
    }

    @Override
    public void set(int idx, T value) {
        if (idx < 0 || idx >= size)
            throw new IndexOutOfBoundsException();

        Node<T> node = first;
        for (int i = 0; i < idx; i++)
            node = node.next;

        node.value = value;
    }

    @Override
    public void add(T value) {
        if (size == 0) {
            first = new Node<>(value);
            last = first;
        } else {
            last.next = new Node<>(value);
            last = last.next;
        }
        size++;
    }

    @Override
    public void remove(int idx) {
        if (idx < 0 || idx >= size)
            throw new IndexOutOfBoundsException();

        if (size == 1)
            first = null;
        else if (idx == 0)
            first = first.next;
        else {
            Node<T> node = first;
            for (int i = 0; i < idx - 1; i++)
                node = node.next;

            if (node.next == last)
                last = node;

            node.next = node.next.next;
        }

        size--;
    }

    @Override
    public void clear() {
        first = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void reverse() {
        Node<T> prev = null;
        Node<T> cur = first;
        Node<T> next;

        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        Node<T> temp = last;
        last = first;
        first = temp;
    }

    private static class Node<E> {
        E value;
        private Node<E> next;

        public Node(E value) {
            this.value = value;
        }

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

}
