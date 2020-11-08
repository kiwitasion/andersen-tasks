package com.company;

public class MyLinkedList {

    private Node head;
    private Node tail;
    private int length = 0;

    public MyLinkedList(){
        this.head = null;
        this.tail = null;
    }

    static class Node {
        public int data;
        public Node next;
        public Node prev;

        public Node(int nodeData) {
            this.data = nodeData;
            this.next = null;
            this.prev = null;
        }
    }

    public void addLast(int value){
        Node node = new Node(value);

        if (length > 0) {
            head.next = node;
            node.prev = head;
            node.next = null;
            head = node;
        } else {
            tail = node;
            head = node;
        }
        length++;
    }


    public void addFirst(int value){
        Node node = new Node(value);

        if (length > 0) {
            tail.prev = node;
            node.next = tail;
            node.prev = null;
            tail = node;
        } else {
            tail = node;
            head = node;
        }
        length++;
    }

    public void add(int value, int index) {

        if (index < length) {

            Node element = tail;
            Node node = new Node(value);

            while (index-- > 0) {
                element = element.next;
            }
            Node left = element;
            Node right = element.next;

            node.next = right;
            node.prev = left;

            right.prev = node;
            left.next = node;

            length++;

        } else if (index == 0){
            addFirst(value);
        } else if (index == length){
            addLast(value);
        } else {
            System.out.println("You cann't add your element on index " + index);
        }

    }

    public void deleteFirst(){
        Node oldTail = tail;
        tail = oldTail.next;
        tail.prev = null;
        length--;
    }

    public void deleteLast(){
        Node oldHead = head;
        head = oldHead.prev;
        head.next = null;
        length--;
    }

    public void deleteByIndex(int index){
        if (index < length) {

            Node element = tail;

            while (index-- > 0) {
                element = element.next;
            }
            Node left = element.prev;
            Node right = element.next;

            left.next = right;
            right.prev = left;
            length--;

        } else if (index == 0){
            deleteFirst();
        } else if (index == length - 1){
            deleteLast();
        } else {
            System.out.println("You haven't index " + index);
        }
    }

    public void print(){
        Node element = tail;

        for (int i = 0; i < length; i++) {
            System.out.print(element.data + " ");
            element = element.next;
        }
        System.out.println();
    }


    public static void main(String[] args) {

        MyLinkedList list = new MyLinkedList();
        list.addFirst(2);
        list.addLast(3);
        list.add(4, 2);
        list.add(5, 4);
        list.addFirst(1);
        list.addLast(5);

        list.print();

        list.deleteFirst();
        list.deleteLast();
        list.deleteByIndex(1);

        list.print();
    }
}

