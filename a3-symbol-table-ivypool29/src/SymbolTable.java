import edu.princeton.cs.algs4.StdOut;

import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class SymbolTable<Key extends Comparable<Key>, Value> implements ISymbolTable<Key , Value> {
    private class Node{
        Key key;            /* The key that the node will store */
        Value val;          /* The value the node will store */
        Node left, right;   /* pointers to the left and right children of this node */
        int size;           /* The number of nodes in the subtree rooted by this node */
        public Node(Key k, Value v, int s){
            this.key = k;
            this.val = v;
            this.size = s;
        }
    }

    /* the root of our tree */
    Node root;

    public SymbolTable(){
        root = null;
    }

    public void put(Key k, Value v)throws InvalidParameterException{
        if(k == null){
            throw new InvalidParameterException("no key was passed");
        }
        if(v == null){
            throw new InvalidParameterException("no value was passed");
        }
        root = put(k, v, root);
    }

    private Node put(Key k, Value v, Node n)throws InvalidParameterException{
        if(k == null){
            throw new InvalidParameterException("invalid key");
        }
        if(v == null){
            throw new InvalidParameterException("invalid value");
        }
        if(n == null){
            return new Node(k, v, 1);
        }

        int cmp = k.compareTo(n.key);
        if(cmp == 0){
            throw new InvalidParameterException("duplicate key was passed");
        }else if(cmp > 0){
            n.right = put(k, v, n.right);
        }else{
            n.left = put(k, v, n.left);
        }
        n.size = 1 + size(n.left) + size(n.right);
        return n;
    }

    public Value get(Key k){
        return get(root, k);
    }

    private Value get(Node n, Key k)throws NoSuchElementException, InvalidParameterException{
        if(k == null){
            throw new InvalidParameterException("no key was passed");
        }else if(n == null){
            throw new NoSuchElementException("The key is not in the table");
        }
        int cmp = k.compareTo(n.key);
        if(cmp < 0){
            return get(n.left, k);
        }else if(cmp > 0){
            return get(n.right, k);
        }else{
            return n.val;
        }
    }

    public void del(Key k){
        root = delete(root, k);
    }

    private Node delete(Node x, Key key)throws NoSuchElementException, InvalidParameterException{
        //checking if input info is null or not
        if(key == null){
            throw new InvalidParameterException();
        }else if(x == null){
            throw new NoSuchElementException();
        }

        //recursively finding and deleting correct node
        int cmp = key.compareTo(x.key);
        if(cmp < 0){
            x.left  = delete(x.left,  key);
        }else if(cmp > 0){
            x.right = delete(x.right, key);
        }else{
            //if it's a 1-child case or no-children case these 2 if-statements resolve that
            if(x.right == null){
                return x.left;
            }
            if(x.left  == null){
                return x.right;
            }

            //2-child case
            Node t = x;
            x = min(t.right);               //setting x equal to the right node's leftmost node
            x.right = deleteMin(t.right);   //setting x's right node to the next largest node after min
                                            //x has been replaced with the right branch of the deleted node
            x.left = t.left;                //setting new x's left node to the original x's left node
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    private Node deleteMin(Node x){
        if(x.left == null){     //if nothing to the left return right node
            return x.right;
        }                       //continues if there's a node to the left
        x.left = deleteMin(x.left);     //recursively calls delete function to find leftmost node, replacing it with the next largest node
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    private Node min(Node x){
        if(x.left == null){
            return x;
        }else{
            return min(x.left);
        }
    }

    public boolean contains(Key k)throws InvalidParameterException{
        if(k == null){
            throw new InvalidParameterException("no key was passed");
        }
        Node n = root;
        while(n != null){
            int cmp = k.compareTo(n.key);
            if(cmp == 0){
                return true;
            }else if(cmp > 0){
                n = n.right;
            }else{
                n = n.left;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        if(root == null){
            return 0;
        }
        return root.size;
    }

    private int size(Node x) {
        if(x == null){
            return 0;
        }
        return x.size;
    }

    public LinkedList<Key> keys() {
        LinkedList<Key> list = new LinkedList<>();
        inorder(root, list);
        return list;
    }

    private void inorder(Node n, LinkedList<Key> list){
        if(n == null){
            return;
        }
        inorder(n.right, list);
        list.push(n.key);
        inorder(n.left, list);
    }
    /*******************************************************************************************************************
     * Tree integrity checking functions as written by the book's authors. Do not modify any of the following functions,
     * they should be used to ensure that your tree is correctly structured
     ******************************************************************************************************************/

    public boolean check() {
        if (!isBST())            StdOut.println("Not in symmetric order");
        if (!isSizeConsistent()) StdOut.println("Subtree counts not consistent");
        if (!isRankConsistent()) StdOut.println("Ranks not consistent");
        return isBST() && isSizeConsistent() && isRankConsistent();
    }

    // does this binary tree satisfy symmetric order?
    // Note: this test also ensures that data structure is a binary tree since order is strict
    public boolean isBST() {
        return isBST(root, null, null);
    }

    // is the tree rooted at x a BST with all keys strictly between min and max
    // (if min or max is null, treat as empty constraint)
    // Credit: Bob Dondero's elegant solution
    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    // are the size fields correct?
    private boolean isSizeConsistent() { return isSizeConsistent(root); }
    private boolean isSizeConsistent(Node x) {
        if (x == null) return true;
        if (x.size != size(x.left) + size(x.right) + 1) return false;
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    }

    // check that ranks are consistent
    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (Key key : keys())
            if (key.compareTo(select(rank(key))) != 0) return false;
        return true;
    }

    /**
     * Return the number of keys in the symbol table strictly less than {@code key}.
     *
     * @param  key the key
     * @return the number of keys in the symbol table strictly less than {@code key}
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
        return rank(key, root);
    }

    // Number of keys in the subtree less than key.
    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else              return size(x.left);
    }

    public Key select(int rank) {
        if (rank < 0 || rank >= size()) {
            throw new IllegalArgumentException("argument to select() is invalid: " + rank);
        }
        return select(root, rank);
    }

    // Return key in BST rooted at x of given rank.
    // Precondition: rank is in legal range.
    private Key select(Node x, int rank) {
        if (x == null) return null;
        int leftSize = size(x.left);
        if      (leftSize > rank) return select(x.left,  rank);
        else if (leftSize < rank) return select(x.right, rank - leftSize - 1);
        else                      return x.key;
    }
}
