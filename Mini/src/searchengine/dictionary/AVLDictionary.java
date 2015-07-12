package searchengine.dictionary;

import java.io.Serializable;
import java.util.ArrayList;

public class AVLDictionary implements DictionaryInterface, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2992716841609416582L;
	private AVLNode root;
	private ArrayList<String> keys;

	private class AVLNode {
		private AVLNode left;
		private AVLNode right;
		private AVLNode parent;
		private String key;
		private Object value;
		private int balance;

		public AVLNode(String k, Object v) {
			left = right = parent = null;
			balance = 0;
			key = k;
			value = v;
		}
		public String toString() {
			return "" + key;
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

	private void inOrder(AVLNode node) {
		if(node == null)
			return;
		inOrder(node.left);
		keys.add(node.key);
		inOrder(node.right);
	}

	public Object getValue(String key) {
		return getValue(root, key);
	}
	
	private Object getValue(AVLNode node, String key){
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
		AVLNode n = new AVLNode(key, value);
		insert(this.root, n);
	}

	private void insert(AVLNode root, AVLNode n) {
		if(root == null) {
			this.root = n;
		} else {

			// If compare node is smaller, continue with the left node
			if(n.key.compareTo(root.key) < 0) {
				if(root.left==null) {
					root.left = n;
					n.parent = root;

					// Node is inserted now, continue checking the balance
					recursiveBalance(root);
				} else {
					insert(root.left, n);
				}

			} else if(n.key.compareTo(root.key) > 0){
				if(root.right==null) {
					root.right = n;
					n.parent = root;

					// Node is inserted now, continue checking the balance
					recursiveBalance(root);
				} else {
					insert(root.right, n);
				}
			} else {
				// do nothing: This node already exists
			}
		}
	}

	public void remove(String key) {
		remove(this.root, key);
	}
	
	private void remove(AVLNode node, String key) {
		if(node==null) {
			return;
		} else {
			if(node.key.compareTo(key) > 0){
				remove(node.left, key);
			} else if(node.key.compareTo(key) < 0){
				remove(node.right, key);
			} else if(node.key.equals(key)) {
				// we found the node in the tree.. now lets go on!
				removeFoundNode(node);
			}
		}
	}
	
	private void removeFoundNode(AVLNode node) {
		AVLNode r;
		// at least one child of q, q will be removed directly
		if(node.left==null || node.right==null) {
			// the root is deleted
			if(node.parent==null) {
				this.root=null;
				node=null;
				return;
			}
			r = node;
		} else {
			// q has two children --> will be replaced by successor
			r = successor(node);
			node.key = r.key;
		}

		AVLNode p;
		if(r.left!=null) {
			p = r.left;
		} else {
			p = r.right;
		}

		if(p!=null) {
			p.parent = r.parent;
		}

		if(r.parent==null) {
			this.root = p;
		} else {
			if(r==r.parent.left) {
				r.parent.left=p;
			} else {
				r.parent.right = p;
			}
			// balancing must be done until the root is reached.
			recursiveBalance(r.parent);
		}
		r = null;
	}
	
	/**
	 * Returns the successor of a given node in the tree (search recursivly).
	 * 
	 * @param q The predecessor.
	 * @return The successor of node q.
	 */
	public AVLNode successor(AVLNode q) {
		if(q.right!=null) {
			AVLNode r = q.right;
			while(r.left!=null) {
				r = r.left;
			}
			return r;
		} else {
			AVLNode p = q.parent;
			while(p!=null && q==p.right) {
				q = p;
				p = q.parent;
			}
			return p;
		}
	}
	
	/**
	 * Check the balance for each node recursivly and call required methods for balancing the tree until the root is reached.
	 * @param cur : The node to check the balance for, usually you start with the parent of a leaf.
	 */
	public void recursiveBalance(AVLNode cur) {
		// we do not use the balance in this class, but the store it anyway
		setBalance(cur);
		int balance = cur.balance;

		// check the balance
		if(balance==-2) {

			if(height(cur.left.left)>=height(cur.left.right)) {
				cur = rotateRight(cur);
			} else {
				cur = doubleRotateLeftRight(cur);
			}
		} else if(balance==2) {
			if(height(cur.right.right)>=height(cur.right.left)) {
				cur = rotateLeft(cur);
			} else {
				cur = doubleRotateRightLeft(cur);
			}
		}

		// we did not reach the root yet
		if(cur.parent!=null) {
			recursiveBalance(cur.parent);
		} else {
			this.root = cur;
		}
	}

	/**
	 * Left rotation using the given node.
	 * @param n
	 *            The node for the rotation.
	 * @return The root of the rotated tree.
	 */
	public AVLNode rotateLeft(AVLNode n) {

		AVLNode v = n.right;
		v.parent = n.parent;

		n.right = v.left;

		if(n.right!=null) {
			n.right.parent=n;
		}

		v.left = n;
		n.parent = v;

		if(v.parent!=null) {
			if(v.parent.right==n) {
				v.parent.right = v;
			} else if(v.parent.left==n) {
				v.parent.left = v;
			}
		}

		setBalance(n);
		setBalance(v);

		return v;
	}

	/**
	 * Right rotation using the given node.
	 * 
	 * @param n
	 *            The node for the rotation
	 * 
	 * @return The root of the new rotated tree.
	 */
	public AVLNode rotateRight(AVLNode n) {
		AVLNode v = n.left;
		v.parent = n.parent;

		n.left = v.right;

		if(n.left!=null) {
			n.left.parent=n;
		}

		v.right = n;
		n.parent = v;

		if(v.parent!=null) {
			if(v.parent.right==n) {
				v.parent.right = v;
			} else if(v.parent.left==n) {
				v.parent.left = v;
			}
		}

		setBalance(n);
		setBalance(v);

		return v;
	}
	/**
	 * 
	 * @param u The node for the rotation.
	 * @return The root after the double rotation.
	 */
	public AVLNode doubleRotateLeftRight(AVLNode u) {
		u.left = rotateLeft(u.left);
		return rotateRight(u);
	}

	/**
	 * 
	 * @param u The node for the rotation.
	 * @return The root after the double rotation.
	 */
	public AVLNode doubleRotateRightLeft(AVLNode u) {
		u.right = rotateRight(u.right);
		return rotateLeft(u);
	}


	/**
	 * Calculating the "height" of a node.
	 * 
	 * @param cur
	 * @return The height of a node (-1, if node is not existent eg. NULL).
	 */
	private int height(AVLNode cur) {
		if(cur==null) {
			return -1;
		}
		if(cur.left==null && cur.right==null) {
			return 0;
		} else if(cur.left==null) {
			return 1+height(cur.right);
		} else if(cur.right==null) {
			return 1+height(cur.left);
		} else {
			return 1 + Math.max(height(cur.left),height(cur.right));
		}
	}

	private void setBalance(AVLNode cur) {
		cur.balance = height(cur.right)-height(cur.left);
	}

	public boolean contains(String key) {
		String []allKeys = getKeys();
		for(String k: allKeys) {
			if(k.equals(key)) return true;
		}
		return false;
	}
}