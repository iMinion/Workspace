package searchengine.dictionary;

import java.io.Serializable;
import java.util.ArrayList;

public class BSTDictionary implements DictionaryInterface,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8613858787997695923L;
	private BinaryNode root;
	private ArrayList<String> keys;
	private class BinaryNode{
		private String key;
		private Object value;
		private BinaryNode left, right;

		public BinaryNode(String key, Object value){
			this.key = key;
			this.value = value;
			left = null;
			right = null;
		}
	}

	public String[] getKeys() {
		keys = new ArrayList<String>();
		inOrder(root);
		String[] k = new String[keys.size()];
		for(int i=0; i<keys.size(); i++)
			k[i] = keys.get(i);
		return k;
	}

	private void inOrder(BinaryNode node) {
		if(node == null)
			return;
		inOrder(node.left);
		if(!keys.contains(node.key)){
		keys.add(node.key);}
		inOrder(node.right);
	}

	public Object getValue(String key) {
		return getValue(root, key);
	}

	private Object getValue(BinaryNode node, String key){
		if(node != null){
			int cmp = key.compareTo(node.key);
			if (cmp < 0)
				node = node.left;
			else if (cmp > 0)
				node = node.right;
			else{
				return node.value;
			}
			return getValue(node, key);
		}
		return null;
	}

	public void insert(String key, Object value) {
		if(root == null){
			root = new BinaryNode(key, value);
		
			return;
		}
		else if(contains(key)){
			remove(root,key);
			insert(root,key,value);
			
			
		}
		else{
		insert(root, key, value);
		}
	}

	private BinaryNode insert(BinaryNode node, String key, Object value){
		if(node == null){
			node = new BinaryNode(key, value);
		}
		else{
			int cmp = key.compareTo(node.key);
			if(cmp < 1)
				node.left = insert(node.left, key, value);
			else
				node.right = insert(node.right, key, value);
		}
		return node;
	}

	public void remove(String key) {
		remove(root, key);
	}

	private BinaryNode remove(BinaryNode node, String key) {
		int cmp = key.compareTo(node.key);
		if (cmp == 0){
			if (node.left == null && node.right == null)
				return null;
			else if (node.left == null)
				node = node.right;
			else if (node.right == null)
				node = node.left;
			else{
				BinaryNode temp = node.left;
				while (temp.right != null)
					temp = temp.right;
				node.key = temp.key;
				node.left = node.left;
				node.left.right = null;
			}
		} 
		else if (cmp < 0)
			node.left = remove(node.left, key);
		else
			node.right = remove(node.right, key);             
		return node;
	}

	public boolean contains(String key) {
		String []allKeys = getKeys();
		for(String k: allKeys) {
			if(k.equals(key)) return true;
		}
		return false;
	}
}
