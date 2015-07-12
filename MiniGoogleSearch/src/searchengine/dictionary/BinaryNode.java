package searchengine.dictionary;


public class BinaryNode<K, V> {
	    private K key;
	    private V value;
	    private BinaryNode<K, V> parent;
	    private BinaryNode<K, V> left;
	    private BinaryNode<K, V> right;

	    public BinaryNode(K k, V v) {
	        this.key = k;
	        this.value = v;
	        this.parent = null;
	        this.left = null;
	        this.right = null;
	    }

	    public BinaryNode() {
	    }

	    public K getKey() {
	        return key;
	    }

	    public void setKey(K key) {
	        this.key = key;
	    }

	    public BinaryNode<K, V> getParent() {
	        return parent;
	    }

	    public void setParent(BinaryNode<K, V> parent) {
	        this.parent = parent;
	    }

	    public BinaryNode<K, V> getLeft() {
	        return left;
	    }

	    public void setLeft(BinaryNode<K, V> left) {
	        this.left = left;
	    }

	    public BinaryNode<K, V> getRight() {
	        return right;
	    }

	    public void setRight(BinaryNode<K, V> right) {
	        this.right = right;
	    }

	    public V getValue() {
	        return value;
	    }

	    public void setValue(V value) {
	        this.value = value;
	    }

}
