package searchengine.dictionary;


public class ListDictionary <K extends Comparable<K>, V> implements DictionaryInterface <K,V>{

	protected ListNode<K,V> start;
	protected ListNode<K,V> end;
	protected int size;
	public ListDictionary()
	{
		this.start=null;
		this.end=null;
		size=0;
	}
	public K[] getKeys() {
		// TODO Auto-generated method stub
		ListNode<K,V> nptr=start;
		K[] keys;
		keys=(K[])(new String[size]);
		int i=0;
		try{
		while(nptr.next!=null)
		{
			keys[i]=nptr.getKey();
			 nptr = nptr.next;
			i++;
		}
		 keys [i] =nptr.getKey();
		}catch(Exception e){}
		return keys;
		
	}

	public V getValue(K str) {
		// TODO Auto-generated method stub
		ListNode<K,V> nptr=start;
		if(nptr!=null && nptr.next==null){
			if(nptr.key.equals(str))
			{
				return nptr.value;
			}
		}
		else{
		while(nptr.next!=null)
		{
			if(nptr.key.equals(str))
			{
				return nptr.value;
			}
			nptr=nptr.next;
		}
		}
		return null;
	}

	public void insert(K k, V v) {
		// TODO Auto-generated method stub
	
		ListNode<K,V> nptr=new ListNode<K,V>(k,v);
		if(start==null)
		{
			//System.out.println("s "+start.key+" "+start.value);
			start=nptr;
			end=start;
		}
		else
		{
			end.setNext(nptr);
			//end.setLinkNext(nptr);
			end=nptr;
			//System.out.println("e "+end.key+" "+end.value);

		}
		size++;
	}

	public void remove(K key) {
		// TODO Auto-generated method stub
		ListNode<K,V> nptr=start;
		////System.out.println("r"+nptr.value);
	//	System.out.println(nptr.next);

		ListNode<K,V> prev=new ListNode<K,V>(null,null);
		while(nptr.next!=null)
		{
			//System.out.println("r"+key);

			if(nptr.key.equals(key))
			{
				//System.out.println("r"+key);
				size--;
				nptr=nptr.next;
				prev.setNext(nptr);
				break;
			}
			prev=nptr;
			nptr=nptr.next;
		}
		
	}
	public void display()
    {
        System.out.print("\nSingly Linked List = ");
        if (size == 0) 
        {
            System.out.print("empty\n");
            return;
        }    
        if (start.getNext() == null) 
        {
            System.out.println(start.getValue() );
            return;
        }
        ListNode<K,V> ptr = start;
        System.out.print(start.getValue()+ "->");
        ptr = start.getNext();
        while (ptr.getNext() != null)
        {
            System.out.print(ptr.getValue()+ "->");
            ptr = ptr.getNext();
        }
        System.out.print(ptr.getValue()+ "\n");
    }
	public boolean contains(K key) {
		// TODO Auto-generated method stub
		ListNode<K,V> nptr=start;
		while(nptr.next!=null){
			if(key.equals(nptr.getKey())){
				return true;
			}
			nptr=nptr.next;
		}
		return false;
	}
}
