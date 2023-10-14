import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

// NOTE: All required exceptions have already been added to the code

public class DoublyLinkedList<T> implements ILinkedList<T>, Iterable<T>{
    private Node<T> front;
    private Node<T> back;
    private int len;

    private Node<T> getNodeAtPosition(int pos) throws NoSuchElementException {
        if (pos >= len()) {
            throw new NoSuchElementException("requested position greater then length of list");
        }
        Node<T> n = front;
        int counter = 0;
        while(counter != pos){
            n = n.getNext();
            counter++;
        }
        return n;
    }

    public void insertFront(T item) {
        Node<T> n = new Node<>();
        n.setData(item);
        if(front == null){
            back = n;
        }else{
            front.setPrev(n);
            n.setNext(front);
        }
        front = n;
        len++;
    }

    public void insertBack(T item) {
        Node<T> n = new Node<>();
        n.setData(item);
        if(back == null){
            front = n;
        }else{
            back.setNext(n);
            n.setPrev(back);
        }
        back = n;
        len++;
    }

    public void insertAt(T item, int position) throws NoSuchElementException {
        Node<T> n = front;
        Node<T> i = new Node<>();
        i.setData(item);
        if(position == 0){
            insertFront(item);
        }else if(position >= len){
            insertBack(item);
        }else{
            int counter = 0;
            while(counter != position){
                n = n.getNext();
                counter++;
            }
            n.getPrev().setNext(i);
            i.setPrev(n.getPrev());
            i.setNext(n);
            n.setPrev(i);
            len++;
        }
    }

    public T getFront() throws NoSuchElementException {
        if(isEmpty()){ throw new NoSuchElementException("List is empty");}
        return front.getData();
    }

    public T getBack() throws NoSuchElementException {
        if(isEmpty()){ throw new NoSuchElementException("List is empty");}
        return back.getData();
    }

    public T getAt(int position) throws NoSuchElementException {
        if(isEmpty()){ throw new NoSuchElementException("List is empty");}
        if(position >= len){
            throw new NoSuchElementException("position " + Integer.toString(position) + " does not exist in the list");
        }
        Node<T> n = front;
        int counter = 0;
        while(counter != position){
            n = n.getNext();
            counter++;
        }
        return n.getData();
    }

    public void removeFront() throws NoSuchElementException {
        if(isEmpty()){ throw new NoSuchElementException("List is empty");}
        front = front.getNext();
        front.setPrev(null);
        len--;
    }

    public void removeBack() throws NoSuchElementException {
        if(isEmpty()){ throw new NoSuchElementException("List is empty");}
        back = back.getPrev();
        back.setNext(null);
        len--;
    }

    public void removeAt(int position) throws NoSuchElementException {
        if(isEmpty()){
            throw new NoSuchElementException("List is empty");
        }else if(position >= len()){
            throw new NoSuchElementException("Position greater then list length");
        }else if(position == 0){
            removeFront();
        }else if(position == len -1){
            removeBack();
        }else{
            Node<T> n = front;
            int counter = 0;
            while(counter != position){
                n = n.getNext();
                counter++;
            }
            n.getPrev().setNext(n.getNext());
            n.getNext().setPrev(n.getPrev());
            len--;
        }
    }

    public int len() {
        return len;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void print() {
        StdOut.print("front -> ");
        Node<T> n = front;
        if(isEmpty()){
            StdOut.print("back\n");
            return;
        }
        while( n != null){
            StdOut.printf("%s -> ", n.getData());
            n = n.getNext();
        }
        StdOut.println("back");
    }

    public void printReverse() {
        StdOut.print("back -> ");
        Node<T> n = back;
        while( n != null){
            StdOut.printf("%s -> ", n.getData());
            n = n.getPrev();
        }
        StdOut.println("front");
    }

    public void printDataAt(int position) {
        if(position >= len()){
            throw new NoSuchElementException();
        }
        Node<T> n = front;
        int counter = 0;
        while(counter != position){
            counter++;
            n = n.getNext();
        }
        StdOut.printf("%s\n", n.getData());
    }

    /* The iterator has already been completed for you, do not modify this function */
    public Iterator<T> iterator() {
        return new LinkedIterator(front);
    }

    /* The iterator has already been completed for you, do not modify this class or any functions inside it */
    private class LinkedIterator implements Iterator<T> {
        private Node<T> current;

        public LinkedIterator(Node<T> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null; }

        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T item = current.getData();
            current = current.getNext();
            return item;
        }
    }

    // if you wish to write specific test code without running Main.java and using StdIn you can use this file's main
    // method to write test code. After you have written your test code you can run it by highlighting this file in the
    // project explorer on the left hand side of your screen, right-clicking on it, and selecting `Rebuild`. Once it has
    // built you can run it by right-clicking on the file again selecting `Run with arguments`
    public static void main(String[] args){

    }
}
