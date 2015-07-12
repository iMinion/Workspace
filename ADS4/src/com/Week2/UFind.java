package com.Week2;
/**
 * 
 * @author iMinion
 * 
 * INPUT 1
 * 6
 * Union(1,2)
 * Union(3,4)
 * Check(1,3)
 * Union(1,5)
 * Check(2,5)
 * Union(1,5)
 * 
 * OUTPUT 1
 * (1,2),(3),(4),(5),(6)
 * (1,2),(3,4),(5),(6)
 * No
 * (1,2,5),(3,4),(6)
 * Yes
 * There is a Cycle
 * 
 * INPUT 2
 * 6
 * Union(1,2)
 * Union(2,3)
 * Union(1,4)
 * Union(1,5)
 * Check(2,3)
 * Check(2,6)
 * Check(4,6)
 * Union(2,6)
 * Check(2,6)
 * 
 * OUTPUT 2
 * (1,2),(3),(4),(5),(6)
 * (1,2,3),(4),(5),(6)
 * (1,2,3,4),(5),(6)
 * (1,2,3,4,5),(6)
 * Yes
 * No
 * No
 * (1,2,3,4,5,6)
 * Yes
 */

public class UFind {

}

class Union_Find {
	int m;
	int[] parent;
	int[] rank;

}

class EdgeU implements Comparable<EdgeU> {

	private String src;
	private String dest;


	EdgeU(String src, String dest) {
		this.src = src;
		this.dest = dest;
	}

	public String getDest() {
		return dest;
	}

	public String getSrc() {
		return src;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	@Override
	public int compareTo(EdgeU o) {

		int compSrc = this.src.compareTo(o.src);
		if(compSrc == 0)
			return this.dest.compareTo(o.dest);
		return compSrc;
	}

public boolean equals(Object o) {
	if(this == o) return true;
	if(o instanceof EdgeL) {
		EdgeL temp = (EdgeL) o;
		return this.src.equals(temp.getSrc()) &&
				this.dest.equals(temp.getDest());
	}
	return false;
}

public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append('(');
	sb.append(this.src);
	sb.append(',');
	sb.append(this.dest);
	sb.append(')');
	return sb.toString();
}
}