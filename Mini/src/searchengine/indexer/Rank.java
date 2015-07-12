package searchengine.indexer;

public class Rank {
	String url;
	int rank;
	public Rank(String u,int i){
		url=u;
		rank=i;
	}
	public int getRank(){
		return rank;
	}
	public String getUrl(){
		return url;
	}
	public void setRank(int i){
		rank=i;
	}
	public void setUrl(String u){
		url=u;
	}
	
	public String toString() {
		return url + "\t" + rank;
	}
	
}
