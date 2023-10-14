import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

public class Queue<T> implements IQueue<T>{
    T[] dataArray;
    int front;
    int back;
    int currUsed;
    int currCapacity;

    public Queue(){
        dataArray = (T[]) new Object[1];
        front = 0;
        back = -1;
        currUsed = 0;
        currCapacity = 0;
    }

    public void push(T data){
        back++;
        if(currUsed == totalCapactiy()) {
            resize(2 * totalCapactiy());
        }
        if(back == totalCapactiy() && currUsed != totalCapactiy()) {
            back = 0;
        }
        if(front == totalCapactiy() && currUsed != totalCapactiy()) {
            front = 0;
        }
        dataArray[back] = data;
        currUsed++;
    }

    public T pop() throws NoSuchElementException {
        // exception throwing checks have already been added
        if(isEmpty()){
            throw new NoSuchElementException("Queue is empty");
        }
        while(dataArray[front] == null){
            if(front + 1 >= totalCapactiy()){
                front = 0;
            }else {
                front++;
            }
        }
        T temp = dataArray[front];
        dataArray[front] = null;
        currUsed--;
        if(front + 1 >= totalCapactiy() && currUsed > 0){
            front = 0;
        }else {
            front++;
        }
        if(currUsed > 0 && currUsed <= totalCapactiy()/4){
            resize(totalCapactiy()/2);
            currCapacity = totalCapactiy();
        }
        return temp;
    }

    public T peek() throws NoSuchElementException {
        // exception throwing checks have already been added
        if(isEmpty()){
            throw new NoSuchElementException("Queue is empty");
        }
        return dataArray[front];
    }

    public int size(){
        return currUsed;
    }

    public boolean isEmpty(){
        return currUsed == 0;
    }

    /* do not modify this function, call this to check the current size of your array */
    public int totalCapactiy(){
        return dataArray.length;
    }

    private void resize(int capacity){
        T[] temp = (T[]) new Object[capacity];
        for(int i = 0; i < currUsed; i++){
            if(front >= totalCapactiy()) {
                front = 0;
            }
            temp[i] = dataArray[front];
            front++;
        }
        dataArray = temp;
        front = 0;
        back = currUsed;
    }
}
