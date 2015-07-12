package searchengine.dictionary;

public class BSTDictionary <K extends Comparable<K>, V> implements DictionaryInterface <K,V>{
    BinaryNode<K,V> root;
    int size;
    int count=0;
    class BinaryNode< Key extends Comparable<Key>, Value>{
    	Value value;
    	Key key;
    	BinaryNode left;
    	BinaryNode right;
    	BinaryNode() {
    		left = null;
    		right = null;
    		value = null;
    		key = null;
    	}
    	BinaryNode(Key key , Value value) {
    		this.value = value;
    		this.key = key;
    		left = null;
    		right = null;
    	}
    	public BinaryNode getLeft(){
    		return right;
    	}
    	public BinaryNode getRight(){
    		return left;
    	}
    	public void setRight(BinaryNode r){
    		right=r;
    	}
    	public void setLeft(BinaryNode l){
    		left=l;
    	}
    	public Value getValue(){
    		return value;
    	}
    	public void setValue(Value v){
    		value=v;
    	}
    	public Key getKey(){
    		return key;
    	}
    	public void setKey(Key k){
    		key=k;
    	}
    }
	public BSTDictionary()
     {
    	 root=new BinaryNode<K,V>();
    	 size=0;
     }
	public K[] getKeys() {
		// TODO Auto-generated method stub
		System.out.println(count+" count");
		   K arr[] = (K[]) new String[count];
	        this.counter = 0;
	        return inOrder(this.root, arr);
	}

	public V getValue(K str) {
		// TODO Auto-generated method stub
        BinaryNode<K, V> current = root;
        try {
            while (current.getKey() != null) {
                if (str.compareTo(current.getKey()) <= 0) {
                    if (current.getKey().compareTo(str) == 0) {
                        return current.getValue();
                    }
                    current = current.getLeft();
                } else {
                    if (current.getKey().compareTo(str) == 0) {
                        return current.getValue();
                    }
                    current = current.getRight();
                }
            }
        } catch (Exception e) {
        }
        return null;
	}
	public void insert(K key,V value){
		BinaryNode<K,V> node=new BinaryNode<K,V>(key,value);
		insert1(root,node);
		
	}
	public void insert1(BinaryNode node,BinaryNode node1) {
		// TODO Auto-generated method stub
		try{
		//System.out.println(node.getValue()+" "+node1.getValue());
		if(node.getValue()==null){
			root=node1;
			count++;
		}
		else{
			int c=(int)(( (Comparable<K>) node.getValue()).compareTo((K) node1.getValue()));
			System.out.println(c+" "+node.getValue()+" "+node1.getValue());
			if(c>0){
				if(node.left==null){
					System.out.println(node1.getValue()+" bbb");
					node.left=node1;
					count++;
					System.out.println(node1.getValue()+" b1");
					
				}
				else{
					System.out.println("not null");
					insert1(node.getLeft(),node1);
				}
				
			}
			else if(c<0){
				if(node.right==null){
					node.right=node1;
					count++;
					System.out.println(node1.getValue()+" r1");
				}
				else{
					insert1(node.getRight(),node1);
				}
				
			}
		}
		}catch(Exception e){}
			
	
		}

	public void remove(K key) {
		// TODO Auto-generated method stub
		
	}
	int counter=0;
	   public K[] inOrder(BinaryNode<K, V> root, K[] arr1) {
	        try {
		   System.out.println(root.getValue()+" root");
		   System.out.println(root.getLeft()+" "+root.getRight());
		 
	            if (root.getLeft()!=null) {
	            //	System.out.println("lllls");
	            	System.out.println(root.value+" vvv");
	            	arr1[counter] = root.getKey();
            	counter++;
	                inOrder(root.getLeft(), arr1);
	            }
		   
	         //   System.out.println(root.getKey()+" kkk");
	            if(root.getLeft()==null && root.getRight()==null)
	            {
	            	System.out.println("leaf"+ counter);
	            	  arr1[counter]=root.getKey();
	 				 counter++;
	            }
	          
	            if (root.getRight()!= null) {
	            	System.out.println("ppps");
	            	arr1[counter] = root.getKey();
            	counter++;
	                inOrder(root.getRight(), arr1);
	            }
	            
	        
	        } catch (Exception e) {
	        }
	        for(int i=0;i<arr1.length;i++)
	        System.out.print( arr1[i]+" aaa ");
	        System.out.println();
	        return arr1;
	    }
	public boolean contains(K key) {
		// TODO Auto-generated method stub
		return false;
	}

}
