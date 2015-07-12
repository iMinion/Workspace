import java.util.Scanner;

class slist{
	snode h=new snode();
	public slist() {
		h.next=null;
		h.itm=null;
	}
	public void addelementlast(Object n) {
		snode a=h;
		while(a.next!=null){
			a=a.next;
		}
		snode s=new snode();
		s.itm=n;
		a.next=s;
		s.next=null;
	}
	
	public void printlist() {
		snode a=this.h;
		System.out.println();
		while(a.next!=null){
			System.out.print(a.next.itm+"\t");
			a=a.next;
		}
	}
	
}
class dlistnh {
	node f=new node();
	public dlistnh(Object s) {
		f.itm=s;
		f.pre=null;
		f.next=null;
	}
	public void addelementlast(Object n) {
		node a=new node();
		a.itm=n;
		if(f.next==null&&f.pre==null) {
			f.next=a;
			f.pre=a;
			a.next=null;
			a.pre=f;
		}else {
			f.pre.next=a;
			f.pre=a;		
		}
	}
	public void printlist() {
		node a=new node();
		a=f;
		System.out.println();
		while(a!=null) {
			System.out.print(a.itm+"\t");
			a=a.next;
		}
	}
}

class dlist {
	node h=new node();
	public dlist() {
		h.itm=null;
		h.pre=null;
		h.next=null;
	}
	public void addelementlast(Object n) {
		node a=new node();
		a.itm=n;
		if(h.next==null&&h.pre==null) {
			h.next=a;
			h.pre=a;
			a.next=null;
			a.pre=h;
		}else {
			a.pre=h.pre;
			h.pre.next=a;
			h.pre=a;
		}
    }
	
	public void printlist() {
		node a=new node();
		a=this.h.next;
		System.out.println();
		while(a!=null) {
			System.out.print(a.itm+"\t");
			a=a.next;
		}
	}
	
	public void splice(node a,node b,node c) {
		node t=b;
		System.out.println("t.node=="+t.itm+" "+t.next+"  "+t.pre);
		System.out.println("\n\nb.node=="+b.itm+" "+b.next+"  "+b.pre);
		while(t.pre!=a) {
			t=t.pre;
			if(c==t) {
				System.out.println("this operation can't be performed");
				return;
			}
			if(t.itm==null){
				System.out.println("this operation can't be performed");
				return;
			}	
			//System.out.println("hiii111");
		}
		t=a;
		a.pre.next=b.next;
		if(b.next!=null)
			b.next.pre=a.pre;
		b.next=c.next;
		c.next.pre=b;
		c.next=a;
		a.pre=c;
	}
	
	public void deleteelementat(node a) {
		a.pre.next=a.next;
		a.next.pre=a.pre;
	}
	public void insertatstart(Object j){
		node c=new node();
		c.itm=j;
		c.next=this.h.next;
		c.pre=h;
		c.next.pre=c;
		h.next=c;
	}
	
	public void insertinbetween(Object j,node a) {
		node c=new node();
		c.itm=j;
		c.next=a.next;
		c.pre=a;
		c.next.pre=c;
		a.next=c;		
	}
	
	public node getnode(Object j) {
		node a=h.next;
		if(h.next==null){
			System.out.println("cant be done");
			return h;
		}
		while(!(a.itm.equals(j))) {
			a=a.next;
			if(a==null){
				System.out.println("cant be done");
				return h;
			}
		} 
			return a;		
	}
	
	public void insertlistafter(dlist l1,node a){
		splice(l1.h.next,l1.h.pre,a);
	}
	public void insertlistbefore(dlist l1,node a){
		splice(l1.h.next,l1.h.pre,a.pre);
	}
	public void concat(dlist l2)  {
		l2.printlist();
		splice(this.h.next,this.h.pre,l2.h.pre);
	}
	public void swap(node a1,node b1,node a2,node b2) {
		@SuppressWarnings("unused")
		node temp=h.next;
		if(b1.next!=a2.pre){
				a1.pre.next=a2;
				b1.next.pre=b2;
				this.printlist();
				a2.pre.next=a1;
				if(b2.next!=null)
					b2.next.pre=b1;
				else
					h.pre=b1;
				this.printlist();
				node t1=a1.pre;
				a1.pre=a2.pre;
				a2.pre=t1;
				node t;
				t=b1.next;
				
				b1.next=b2.next;
				b2.next=t;	
				//this.printlist();
		}else {
			a1.pre.next=a2;
			if(b2.next!=null)
				b2.next.pre=b1;
			else
				h.pre=b1;
			node t1=a1.pre;
			a1.pre=a2.pre;
			a2.pre=t1;
			node t;
			t=b1.next;
			
			b1.next=b2.next;
			b2.next=t;	
		}
	}	
}


public class LinkedList {
    public static void main(String[] args) {
		dlist l1=new dlist();
		dlist l5=new dlist();
        slist l2=new slist();
        slistnh l3=new slistnh(21);
        dlistnh l4=new dlistnh(123);
		Scanner in=new Scanner(System.in);
		System.out.println("give the elements of list");
		for(int i=0;i<10;i++) {
            String s=in.next();
			l1.addelementlast(s);
            l2.addelementlast(s);
            l3.addelementlast(s);
            l4.addelementlast(s);
            l5.addelementlast(s);
		}
		l5.printlist();
		l5.swap(l5.getnode("1"),l5.getnode("4"), l5.getnode("7"), l5.getnode("0"));
		l5.printlist();
		in.close();
	}
}

class slistnh{
	snode f=new snode();
	public slistnh(Object n) {
		f.next=null;
		f.itm=n;
	}
	
	public void addelementlast(Object n) {
		snode a=f;
		while(a.next!=null){
			a=a.next;
		}
		snode s=new snode();
		s.itm=n;
		a.next=s;
		s.next=null;
	}
	
	public void printlist() {
		snode a=this.f;
		System.out.println();
		while(a!=null){
			System.out.print(a.itm+"\t");
			a=a.next;
		}
	}
	
}
class snode {
	Object itm;
	snode next; 
}

class node {
	Object itm;
	node pre,next;
	
	public node(){
		pre=null;
		next=null;
		itm=null;
	}
	
	public node(Object s,node a,node b) {
		itm=s;
		pre=a;
		next=b;
	}
	
	public void setnext(node a) {
		next=a;
	}
	public void setprev(node a) {
		pre=a;
	}
	public void setitm(Object s) {
		itm=s;
	}
	public node getnext() { return next; }
	
	public node getprev() { return pre;}
	
	public Object getitm() { return itm; }
		
}
