package pl.edu.pw.ee.aisd2023zlab5.HuffmanTree;

public class Node<K extends Comparable<K>, V> implements Comparable<Node<K, V>>{
    private K counter;
    private V value;


    private Node<K, V> left;
    private Node<K, V> right;

    public Node(K counter, V value) {
        this.counter = counter;
        this.value = value;
    }

    public K getCounter() {
        return counter;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public void setCounter(K counter) {
        this.counter = counter;
    }

    public Node<K, V> getLeft() {
        return left;
    }

    public void setLeft(Node<K, V> leftNode) {
        left = leftNode;
    }

    public Node<K, V> getRight() {
        return right;
    }

    public void setRight(Node<K, V> rightNode) {
        right = rightNode;
    }

    public boolean isLeaf(Node<K, V> node){ return node.getLeft() == null && node.getRight() == null; }

    public boolean isParent(Node<K, V> node){ return node.getLeft() != null || node.getRight() != null; }

    @Override
    public int compareTo(Node<K, V> other) {
        return  ((Comparable<K>) this.counter).compareTo(other.counter);
    }
}
