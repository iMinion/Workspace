public class MySingleLinkedList {
	SingleLinkNode node = new SingleLinkNode();
	int size = 0;
	public MySingleLinkedList(Object item) {
		node.setItem(item);
		node.setNext(null);
	}
	
	public void addElement(Object item) {
		SingleLinkNode entryNode = new SingleLinkNode(item, null);
		SingleLinkNode traveller = node;
		while(traveller.getNext() != null) {
			traveller = traveller.getNext();
		}
		traveller.setNext(entryNode);
		++size;
	}
	
	public void splice(SingleLinkNode a, SingleLinkNode b, SingleLinkNode c) {
		SingleLinkNode traveller = a;
		while(traveller.getNext() != b) {
			
		}
	}
	
	public void remove(Object item) {
		SingleLinkNode traveller = node;
		SingleLinkNode prevTraveller = null;
		while(traveller.getNext() != null) {
			if(!traveller.getItem().equals(item)) {
				prevTraveller = traveller;
				traveller = traveller.getNext();
			}
		}
		prevTraveller.setNext(traveller.getNext());
	}
	
	public String toString() {
		SingleLinkNode traveller = node;
		StringBuilder sb = new StringBuilder();
		while(traveller.getNext() != null) {
			sb.append(traveller.toString());
			sb.append(",");
			traveller = traveller.getNext();
		}
		return sb.toString().substring(0, sb.length() - 1);
	}
	
}

class SingleLinkNode {
	Object item;
	SingleLinkNode next;
	
	public SingleLinkNode() {
		item = null;
		next = null;
	}
	
	public SingleLinkNode(Object item) {
		this.item = item;
	}
	
	public SingleLinkNode(Object item, SingleLinkNode next) {
		this.item = item;
		this.next = next;
	}
	
	public Object getItem() {
		return item;
	}
	
	public SingleLinkNode getNext() {
		return next;
	}
	
	public void setItem(Object item) {
		this.item = item;
	}
	
	public void setNext(SingleLinkNode next) {
		this.next = next;
	}
	
	public String toString() {
		return item.toString();
	}
}

class DoubleLinkNode {
	Object item;
	DoubleLinkNode next;
	DoubleLinkNode prev;
	
	public DoubleLinkNode() {
		item = null;
		next = null;
		prev = null;
	}
	
	public DoubleLinkNode(Object item) {
		this.item = item;
		next = null;
		prev = null;
	}
	
	public DoubleLinkNode(Object item, DoubleLinkNode next, DoubleLinkNode prev) {
		this.item = item;
		this.next = next;
		this.prev = prev;
	}
	
	public Object getItem() {
		return this.item;
	}
	
	public DoubleLinkNode getNext() {
		return this.next;
	}
	
	public DoubleLinkNode getPrev() {
		return this.prev;
	}
	
	public void setItem(Object item) {
		this.item = item;
	}
	
	public void setNext(DoubleLinkNode next) {
		this.next = next;
	}
	
	public void setPrev(DoubleLinkNode prev) {
		this.prev = prev;
	}
}