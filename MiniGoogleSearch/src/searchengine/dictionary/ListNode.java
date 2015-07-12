package searchengine.dictionary;

class ListNode<K extends Comparable<K>, V>
{
	K key;
	V value;
	ListNode<K, V> next;
	public ListNode(K k, V v) {
		this.key = k;
		this.value = v;
		this.next = null;
	}
	public ListNode() {
		this.key = null;
		this.value = null;
		this.next = null;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public ListNode<K, V> getNext() {
		return next;
	}

	public void setNext(ListNode<K, V> next) {
		this.next = next;
	}
}
