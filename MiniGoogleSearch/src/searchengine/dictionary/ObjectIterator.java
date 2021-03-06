/**  
 * 
 * Copyright: Copyright (c) 2004 Carnegie Mellon University
 * 
 * This program is part of an implementation for the PARKR project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * 
 */ 

package searchengine.dictionary;

import java.util.Vector;
import java.util.Iterator;
//import java.util.List;

import searchengine.element.PageElementInterface;


/** This class creates Iterators out of Vectors.
 *
 */
public class ObjectIterator<E> implements Iterator<E>
{
    /** The constructor for ObjectIterator.
     */
    public ObjectIterator(Vector<E> data)
    {
		this.data=data;
		i=0;
    }
    
    public int size()
    {
    	return data.size();
    }

    /** Determine whether there are more elements in the Iterator.
     *
     * @return true if there are more elements, otherwise false
     */
    public boolean hasNext()
    {
		return i<data.size();
    }
    public int getI(){
    	return i;
    }
    public void setI(int k){
    	i=k;
    }

    public String toString() {
    	return data.toString();
    }
    
    /** Get the next Object in the iteration.
     * 
     * @return the next Object
     */
    public E next()
    {
		if(i>=data.size())
			return null;
		return data.elementAt(i++);
    }

    public Vector<E> returnVec() {
        return data;
    }
    
    /** Unimplemented.
     */
    public void remove() {}

    public Vector<E> data;
    public int i;
	
}

