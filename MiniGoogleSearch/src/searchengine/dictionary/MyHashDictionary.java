package searchengine.dictionary;
public class MyHashDictionary <K extends Comparable<K>, V> implements DictionaryInterface <K,V>{
   int size;
   int count;
	ListNode<K,V>[] list;
	public MyHashDictionary()
	{
		size=1300;
		list=new ListNode[size];
		  for (int i = 0; i < this.size; i++) {
	            list[i] = new ListNode<K, V>(null, null);
	        }
		
	}
	public K[] getKeys() {
		// TODO Auto-generated method stub
		K[] keys = (K[]) new String[count];
		System.out.println(keys.length+" lll");
        int j = 0;
        System.out.println(size+" sss");
    //    String k="";
        for (int i = 0; i < size; i++)
        {
        	//System.out.println(list[448] +" lis");
        	//try
        //	{
        		
        		//System.out.println(k+" str");
        	//System.out.println(list[i].key+" kk");
        	
            if (list[i].key!=null)
            {
            	//System.out.println(i+"i");
            //	System.out.println(list[i].key+" kk");
                keys[j] = list[i].key;
              //  System.out.println(keys[i]+" key");
                j++;
            }
            else{
            	
            }
        	//}catch(Exception e){};
           // System.out.println(keys[i]+" key");
        }
		return keys;
		
		
	}

	public V getValue(K str) {
		// TODO Auto-generated method stub
	
			int key1=hashCode((String) str);
		//	System.out.println(list[key1].value+" nnn");
			return list[key1].value;
		
	}

	public void insert(K key, V value) 
	{
		// TODO Auto-generated method stub
		ListNode<K, V> ptr = new ListNode<K,V>(key,value);
		System.out.println(key+" "+value);
		try
		{
          if (getValue(key)==null)
          {
             count++; 
             System.out.println(count+" ccc");
         }
        int key1=hashCode((String) key);
        list[key1] = ptr;
       // System.out.println(list[key1].value+" iii");
       // System.out.println(list[key1]);
		}catch(Exception e){};
     

	}

	public void remove(K key) {
		// TODO Auto-generated method stub
		try
		{
		if (getValue(key) != null) 
		{
            count--;
        }
		int key1=hashCode((String) key);
        list[key1].value = null;
        list[key1].key = null;
		}catch(Exception e){}
		
	}
	 public int hashCode(String str) {
	        int code = 0;
	        if (str.length() >=4) 
	        {
	            for (int i = 0; i < 4; i++)
	            {
	                code = code* str.charAt(i);
	            }
	        } 
	        else 
	        {
	            for (int i = 0; i < str.length(); i++) 
	            {
	                code = code+ str.charAt(i);
	            }
	        }
	        System.out.println(code+" code");
	        return code;
	    }
	public boolean contains(K k) {
		// TODO Auto-generated method stub
		int key1=hashCode((String) k);
		if(list[key1].key!=null){
			return true;
		}
		return false;
	}

}
