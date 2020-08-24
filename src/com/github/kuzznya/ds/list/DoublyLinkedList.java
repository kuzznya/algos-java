package com.github.kuzznya.ds.list;

public class DoublyLinkedList<T> implements MyList<T> {

    private Node<T> first;
    private Node<T> last;
    int size = 0;

    @Override
    public T get(int idx) {
        if (idx < 0 || idx >= size)
            throw new IndexOutOfBoundsException();

        Node<T> node;
        if (idx < size / 2) {
            node = first;
            for (int i = 0; i < idx; i++)
                node = node.next;
        } else {
            node = last;
            for (int i = size - 1; i > idx; i--)
                node = node.prev;
        }

        return node.value;
    }

    @Override
    public void set(int idx, T value) {
        if (idx < 0 || idx >= size)
            throw new IndexOutOfBoundsException();

        Node<T> node;
        if (idx < size / 2) {
            node = first;
            for (int i = 0; i < idx; i++)
                node = node.next;
        } else {
            node = last;
            for (int i = size - 1; i > idx; i--)
                node = node.prev;
        }

        node.value = value;
    }

    @Override
    public void add(T value) {
        if (size == 0) {
            first = new Node<>(value);
            last = first;
        } else {
            last.next = new Node<>(value);
            last.next.prev = last;
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
            Node<T> node;
            if (idx < size / 2) {
                node = first;
                for (int i = 0; i < idx - 1; i++)
                    node = node.next;
            } else {
                node = last;
                for (int i = size - 1; i > idx - 1; i--)
                    node = node.prev;
            }

            if (node.next == last)
                last = node;

            node.next.next.prev = node;
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
        Node<T> cur = first;

        while (cur != null) {
            Node<T> temp = cur.next;
            cur.next = cur.prev;
            cur.prev = temp;
            cur = cur.prev;
        }

        Node<T> temp = last;
        last = first;
        first = temp;
    }

    private static class Node<E> {
        E value;
        private Node<E> prev;
        private Node<E> next;

        public Node(E value) {
            this.value = value;
        }

        public Node(E value, Node<E> prev, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

}
