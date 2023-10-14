import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;
import java.util.Queue;

public class BST <Key extends Comparable<Key>, Value>{

    private class Node {
        public Key key;
        public Value value;
        public Node left;
        public Node right;

        public Node(Key key, Value value){
            this.key = key;
            this.value = value;
        }
    }

    Node root;

    public BST(){
        root = null;
    }

    public void put(Key key, Value value){
        root = put(key, value, root);
    }

    private Node put(Key key, Value value, Node n){
        if(n == null){
            return new Node(key, value);
        }
        int cmp = key.compareTo(n.key);
        if(cmp < 0){
            n.left = put(key, value, n.left);
        }else if(cmp > 0){
            n.right = put(key, value, n.right);
        }else{
            n.value = value;
        }
        return n;
    }

    public void inorder(){
        inorder(root);
    }

    private void inorder(Node n){
        if(n == null){
            return;
        }
        inorder(n.left);
        StdOut.print(n.key + " ");
        inorder(n.right);
    }

    public boolean contains(Key k){

    }

    public Value get(Key k){

    }

    public void del(Key k){

    }

    public Queue<Key> keys(){

    }

    public static void main(String[] args){
        BST<String, Integer> tree = new BST<>();
        tree.put("A", 10);
        tree.inorder();

        if(tree.contains("A")){
            int val = tree.get("A");
            tree.del("A");
        }

        for(String k : tree.keys()){
            StdOut.println(k);
        }
    }
}
