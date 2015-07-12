
import java.util.*;
public class kWayMerge{
    public static void main(String args[]){
        int no_of_parts,search_num;
        String str=null;
        Solution sol=new Solution();
		Scanner scan = new Scanner(System.in);
		str = scan.nextLine();
		String[] s = str.split(" ");
		no_of_parts=scan.nextInt();
		search_num = scan.nextInt();
		int[] intArray = new int[s.length];
		for(int i=0;i<intArray.length;i++)
			intArray[i] = Integer.parseInt(s[i]);
		int first=0,last=intArray.length;
        sol.set(no_of_parts,search_num);
		Solution.doSort(intArray,first,last);	
		scan.close();
	}
    
}
class Solution
{
    static int k;
    static int num;
    public void set(int k,int num){
        Solution.k=k;		
        Solution.num=num;		
    }
    public static void process(int first,int last,int[] intArray){
        for(int i = first;i<last;i++){
			if(intArray[i]==num){
				for(int j=first;j<last;j++){
					System.out.print(intArray[j]+" ");
				}
                System.out.println();
			}
		}
    }
	
	public static void doSort(int[] intArray,int first,int last){
		int len = last-first,mid = len/k,low=first,high=low+mid;
		process(first,last,intArray);
		if(len<=k)
        {
			int[] array = new int[len];
			int position=first;
			for(int i=0;i<len;i++){
				array[i]=intArray[position++];
			}
			insertion_Sort(array);
			position=first;
			for(int i=0;i<len;i++){
				intArray[position++]=array[i];
			}
		}
		else{
			for(int i=0;i<k;i++){
				if(i==k-1)
					doSort(intArray,low,last);
				else{
					doSort(intArray,low,high);
					low=high;
					high=high+mid;
				}
			}
            
			int[] tempArray = new int[len];
			int position=first;
			for(int i=0;i<len;i++){
				tempArray[i]=intArray[position++];
			}
			insertion_Sort(tempArray);
			position=first;
			for(int i=0;i<len;i++){
				intArray[position++]=tempArray[i];
			}
			
		}
        display(first,last,intArray,num);
		
	}
    public static void display(int first,int last,int[] intArray,int num){
        for(int i =first;i<last;i++){
			if(intArray[i]==num){
				for(int j=first;j<last;j++){
					System.out.print(intArray[j]+" ");
				}
                System.out.println();
			}
		}
    }
	public static void insertion_Sort(int[] intArray){
		int position;
		int t;
		for (int i = 1 ; i <= intArray.length - 1; i++) {
          position= i;
              while ( position > 0 && intArray[position] < intArray[position-1]) {
              t = intArray[position];
              intArray[position]   = intArray[position-1];
              intArray[position-1] = t;
              position--;
              
              }
          }

	}
}