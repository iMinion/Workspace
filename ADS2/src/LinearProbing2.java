import java.util.*;
public class LinearProbing2 {

    public static String[] str=new String[13];
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc=new Scanner(System.in);
        String s=null;
        for(int k=0;k<13;k++)
            str[k]="0";
        char ch;
        do{
            System.out.println("Enter String ");
            s=sc.next();
            add(s);
            System.out.println("if you eant to continue press 'y'");
            ch=sc.next().charAt(0);
        }while(ch=='y' || ch=='Y');
        for(int i=0;i<13;i++)
            System.out.print(str[i]+"  ");
        delete("clip");
        System.out.println();
        for(int i=0;i<13;i++)
            System.out.print(str[i]+"  ");
        s = sc.nextLine();
        System.out.println(between(s, 2, 8));
        sc.close();
        
    }
    public static void add(String s)
    {
        
        int i=(s.charAt(s.length()-1)-97)%13;
        int temp=i;
        if(isfull())
            System.out.println("array is full");
        else if(str[i].equals("0"))
        {
            str[i]=s;
        }
        else
        {
            while(str[i]!="0")
            {
                i=(i+1)%13;
                if(temp==i)
                    break;
            }
            str[i]=s;
        }
    }
    
    public static void delete(String s)
    {
        int i=(s.charAt(s.length()-1)-97)%13;
        int temp=i;
        int j=0;
        int k=0;
        //str[i]="0";
        //i=(i+1)%13;
        for( k=i;k<12;k++)
        {
            if(str[k].equals(s))
            {
                str[k]="0";
                temp=k;
                j=k;
                break;
            }
        }
        k=(k+1)%13;
        while(!str[k].equals("0"))
        {
            String temp1=str[k];
            int res=(temp1.charAt(temp1.length()-1)-97)%13;
            if(res>temp)
                k=(k+1)%13;
            else
            {
                String s1=str[temp];
                str[temp]=str[k];
                str[k]=s1;
                temp=k;
                k=(k+1)%13;
            }
                
            
            
            if(j==k)
                break;
        }
        
        
    }
    public int find(String s)
    {
        int i=0;
        for(i=0;i<13;i++)
    
            if(str[i].equals(s))
            
                return i+1;
                
            return i+1;
        
    }
    public static boolean isfull()
    {
        for(int i=0;i<13;i++)
        {
            if(str[i].equals("0"))
                return false;
            
        }
        return true;
    }
    public static boolean between(String s, int j, int k) {
    	int i = ((s.charAt(s.length() - 1) -97)%13);
    	boolean flag = false;
    	if(str[i].equals("0")) return flag;
    	else {
    		String t = str[i];
    		for(int l = j; l <= k; l++) {
    			if(str[l].equals(t)) return !flag;
        	}
        	return flag;
    	}
    }
}