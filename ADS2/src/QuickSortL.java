import java.util.*;

public class QuickSortL{
    public static void main(String[] args)
    {
        int num,x;
        String str;
        Scanner scan=new Scanner(System.in);
        Quick qs=new Quick();
        str=scan.nextLine();
        String[] sarr=str.split(" ");
        int[] arr=new int[sarr.length];
        for(int i=0;i<arr.length;i++)
        {
            arr[i]=Integer.parseInt(sarr[i]);
        }
        num=scan.nextInt();
        x=scan.nextInt();
        qs.set(num,x);
        int count=Quick.quick_Sort(arr,0,arr.length-1);	
        scan.close();
        display(arr,count);
    }
    public static void display(int[] arr,int count){
        for(int i=0;i<arr.length;i++)
        {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
        System.out.print(count);
    }
}
class Quick
{
    static int n,x,c=0;
    
    public void set(int n,int x){
        Quick.n=n;  
        Quick.x=x;	
    }
    public static int quick_Sort(int[] Arr,int left,int right)
    {
        while(right-left+1>n)
        {
                //int mid=l+(r-l)/2;
            int pvt=Arr[left];
            int i=left;
            int j=right;
            while(i<=j)
            {
                while(Arr[i]<pvt)
                {
                    if(x==Arr[i])
                        {
                            c++;
                        }
                    i++;
                }
                  if(Arr[i]==pvt)
                    {
                        if(x==Arr[i])
                            {
                                c++;
                            }
                    }
                while(Arr[j]>pvt)
                {
                    if(x==Arr[j])
                        {
                            c++;
                        }
                    j--;
                }
                  if(Arr[j]==pvt)
                    {
                        if(x==Arr[j])
                            {
                                c++;
                            }
                    }
                if(i<=j)
                {
                    int temp=Arr[i];
                    Arr[i]=Arr[j];
                    Arr[j]=temp;
                    if(Arr[j]==x)
                        {
                            c=c+2;
                        }
                    i++;
                    j--;
                }
            }
                           
            if(i<(left+right)/2)
            {
                   quick_Sort(Arr,left,j);
                left=i;
            }
            else
            {
                quick_Sort(Arr,i,right);
                right=j;
            }
        }
        
        insertion_Sort(Arr);   
        return c; 
    }
    public static void insertion_Sort(int[] intarr){
        int position;
        int t;
        for (int i = 1 ; i <= intarr.length - 1; i++) {
          position= i;
              while ( position > 0 && intarr[position] < intarr[position-1]) {
              t = intarr[position];
              intarr[position]   = intarr[position-1];
              intarr[position-1] = t;
              position--;
              
              }
          }

    }
}