import java.util.Iterator;

public class Bag<T> implements IBag<T>{
    T[] data;
    int idx;

    public Bag(){
        data = (T[]) new Object[100];
        idx = 0;
    }

    public void add(T item) {
       data[idx++] = item;
    }

    public boolean isEmpty() {
        if(idx == 0){
            return true;
        }
        return false;
    }

    public int size() {
        return idx-1;
    }

    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T>{
        int current;

        public ArrayIterator(){
            current = 0;
        }
        public boolean hasNext() {
            if(current < idx){
                return true;
            }
            return false;
        }

        public T next() {
            return data[current++];
        }
    }



    /*
    GenericList<T> list;

    public Bag(){
        list = new GenericList<>();
    }

    public void add(T item) {
        list.addToBack(item);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public Iterator<T> iterator() {
        return list.iterator();
    }
    */
}
