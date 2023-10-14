import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;
import java.util.Iterator;

// NOTE: All required exceptions have already been added to the code
// HINT: This code will be _almost_ identical to the DoublyLinkedList code. Slight modifications to the insert/remove methods
//       will need to be done
public class CircularList<T> implements ILinkedList<T>, Iterable<T>{
    private Node<T> front;
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
            n.setNext(n);
            n.setPrev(n);
        }else{
            n.setPrev(front.getPrev());
            front.getPrev().setNext(n);
            front.setPrev(n);
            n.setNext(front);
        }
        front = n;
        len++;
    }

    public void insertBack(T item) {
        Node<T> n = new Node<>();
        n.setData(item);
        if(front == null){
            insertFront(item);
        }else{
           front.getPrev().setNext(n);
           n.setPrev(front.getPrev());
           n.setNext(front);
           front.setPrev(n);
           len++;
        }
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
        return front.getPrev().getData();
    }

    public T getAt(int position) throws NoSuchElementException {
        if(isEmpty()){ throw new NoSuchElementException("List is empty");}
        if(position >= len) {
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
        front.getPrev().setNext(front.getNext());
        front.getNext().setPrev(front.getPrev());
        front = front.getNext();
        len--;
    }

    public void removeBack() throws NoSuchElementException {
        if(isEmpty()){ throw new NoSuchElementException("List is empty");}
        front.setPrev(front.getPrev().getPrev());
        front.getPrev().setNext(front);
        len--;
    }

    public void removeAt(int position) throws NoSuchElementException {
        if(isEmpty()){ throw new NoSuchElementException("List is empty");}
        else if(position >= len()){ throw new NoSuchElementException("Position greater then list length");}
        else if(position == 0){
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
        do{
            StdOut.printf("%s -> ", n.getData());
            n = n.getNext();
        }while(n != front);
        StdOut.println("back");
    }

    public void printReverse() {
        StdOut.print("back -> ");
        Node<T> n = front.getPrev();
        if(isEmpty()){
            StdOut.println("front\n");
            return;
        }
        do{
            StdOut.printf("%s -> ", n.getData());
            n = n.getPrev();
        }while(n != front.getPrev());
    }

    public void printDataAt(int position) {
        if(position >= len()){
            throw new NoSuchElementException("position is greater then the length of the list");
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

    /* The iterator has already been completed for you, do not modify this class or any functions inside of it */
    private class LinkedIterator implements Iterator<T> {
        private Node<T> current;

        public LinkedIterator(Node<T> first) {
            current = first;
        }

        public boolean hasNext()  {
            return current.getNext() != front;
        }

        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T item = current.getData();
            current = current.getNext();
            return item;
        }
    }

    /* this method is fully implemented, do not modify */
    public boolean isCircularForward(){
        Node<T> n = front;
        if(isEmpty()){
            return true;
        }
        do {
            n = n.getNext();
            if(n == null){
                return false;
            }
        } while(n != front);
        return true;
    }

    /* this method is fully implemented, do not modify */
    public boolean isCircularBackward(){
        if(isEmpty()){
            return true;
        }
        Node<T> n = front.prev;
        do {
            n = n.getPrev();
            if(n == null){
                return false;
            }
        } while(n != front.prev);
        return true;
    }

    // if you wish to write specific test code without running Main.java and using StdIn you can use this file's main
    // method to write test code. After you have written your test code you can run it by hilighting this file in the
    // project explorer on the left hand side of your screen, right clicking on it, and selecting `Rebuild`. Once it has
    // built you can run it by right clicking on the file again selecting `Run with arguments`
    public static void main(String[] args){

    }
}
