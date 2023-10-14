import java.util.NoSuchElementException;

public class Stack<T> implements IStack<T>{
    Queue<T> q1;
    Queue<T> q2;

    public Stack(){
        q1 = new Queue<>();
        q2 = new Queue<>();
    }

    public void push(T data){
        if(!q2.isEmpty()){
            q2.push(data);
        }else{
            q1.push(data);
        }
    }

    public T pop() throws NoSuchElementException {
        int counter;
        // exception throwing checks have already been added
        if(isEmpty()){ throw new NoSuchElementException("queue is empty");
        }else if(!q1.isEmpty()){
            counter = q1.currUsed -1;
            while(counter > 0){
                q2.push(q1.pop());
                counter--;
            }
            return q1.pop();
        }else{
            counter = q2.currUsed -1;
            while(counter > 0){
                q1.push(q2.pop());
                counter--;
            }
            return q2.pop();
        }
    }

    public T peek(){
        int counter;
        T temp = null;
        // exception throwing checks have already been added
        if(isEmpty()){ throw new NoSuchElementException("queue is empty"); }
        if(!q1.isEmpty()){
            counter = q1.currUsed;
            while(counter != 0) {
                temp = q1.peek();
                q2.push(q1.pop());
                counter--;
            }
        }else{
            counter = q2.currUsed;
            while(counter != 0){
                temp = q2.peek();
                q1.push(q2.pop());
                counter--;
            }
        }
        return temp;
    }

    public int size(){

        return q1.size() + q2.size();
    }

    public boolean isEmpty(){
        return (q1.isEmpty() && q2.isEmpty());
    }
}
