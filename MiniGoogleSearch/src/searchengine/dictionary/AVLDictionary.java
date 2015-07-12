package searchengine.dictionary;

public class AVLDictionary <K extends Comparable<K>, V> implements DictionaryInterface <K,V>{
	 private AVLNode<K, V> root;
	class AVLNode<K extends Comparable<K>, V> {

	    K key;
	    V value;
	    int height;
	    AVLNode<K, V> left;
	    AVLNode<K, V> right;
	    AVLNode<K, V> parent;

	    public AVLNode() {
	        key = null;
	        value = null;
	        left = null;
	        right = null;
	        this.height = 0;
	    }

	    public AVLNode(K key, V value) {
	        this.key = key;
	        this.value = value;
	        this.left = null;
	        this.right = null;
	        this.height = 0;
	    }
	    
	    public K getKey() {
	        return key;
	    }
	    
	    public V getValue() {
	        return value;
	    }
	    
	    public AVLNode<K, V> getParent() {
	        return parent;
	    }

	    public void setParent(AVLNode<K, V> parent) {
	        this.parent = parent;
	    }
	    
	    public AVLNode<K, V> getLeft() {
	        return left;
	    }
	    
	    public void setLeft(AVLNode<K, V> left) {
	        this.left = left;
	    }
	    
	    public AVLNode<K, V> getRight() {
	        return right;
	    }
	    
	    public void setRight(AVLNode<K, V> right) {
	        this.right = right;
	    }
	}
	  boolean isLeftChild;
	  int count=0;
	    public AVLDictionary() {
	        this.root = new AVLNode<K, V>();
	        this.count = 0;
	        this.isLeftChild = true;
	    }
	public K[] getKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	public V getValue(K str) {
		// TODO Auto-generated method stub
		return null;
	}

	public void insert(K key, V value) {
		// TODO Auto-generated method stub
		
	}

	public void remove(K key) {
		// TODO Auto-generated method stub
		
	}
	public boolean contains(K key) {
		// TODO Auto-generated method stub
		return false;
	}

}
