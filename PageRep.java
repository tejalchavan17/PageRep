import java.util.*;
class PageRep
{
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		int pn,i,fn;
		String page[]=new String[50];
		System.out.println("\nEnter page stream size");
		pn=sc.nextInt();
		System.out.println("\nEnter page stream");
		for(i=0;i<pn;i++)
			page[i]=sc.next();
		System.out.println("\nEnter frame size");
		fn=sc.nextInt();
		System.out.println("---------- FIFO Page Replacement ----------------");
		fifo(page,pn,fn);		
		System.out.println("---------- LRU Page Replacement ----------------");
		lru(page,pn,fn);		
		System.out.println("---------- OPTIMAL Page Replacement ----------------");
		optimal(page,pn,fn);    
	}
	public static void fifo(String page[],int pn,int fn)
	{
		int i,j,c,fault=0,hit=0,point=0,fla,it=1,t,comp;
		float faultrate;
		String frame[]=new String[50];
		for(i=0;i<fn;i++)
		{
			frame[i]="ze";
		}
		c=0;
		for(i=0;i<pn;i++)
		{
			fla=0;
			if(frame[c]=="ze")
			{
				frame[c]=page[i];
				fault++;
				c++;
				System.out.println("iteration"+i);
				System.out.println("page:"+page[i]+"\n");
				for(t=0;t<fn;t++)
					System.out.print(frame[t]+"\t");
				System.out.println("Fault:"+fault);
			}
			else
			{
				
				for(j=0;j<fn;j++)
				{
					
					comp=frame[j].compareTo(page[i]);
					
					if(comp==0)
					{
						hit++;
						fla=1;
						System.out.println("iteration"+i);
						System.out.println("page:"+page[i]+"\n");
						for(t=0;t<fn;t++)
							System.out.print(frame[t]+"\t");
						System.out.println("Hit:"+hit);
					}
				}
				if(fla==0)
				{
					fault++;
					frame[point]=page[i];
					System.out.println("iteration"+i);
					System.out.println("page:"+page[i]+"\n");
					for(t=0;t<fn;t++)
						System.out.print(frame[t]+"\t");
					System.out.println("Fault:"+fault);
					point=(point+1)%fn;
				}
			}
		}
		System.out.println("\nResult:");
		System.out.println("Total Fault:"+fault);
		faultrate=(float)fault/pn;
		System.out.println("faultrate="+faultrate);
	}
	public static void lru(String page[],int pn,int fn)
	{
		int i,j,c,fault=0,hit=0,point=0,fla,it=1,t,comp,itemp,lr,min=999,ct,com;
		float faultrate;
		String ch=new String();
		String frame[]=new String[50];
		for(i=0;i<fn;i++)
		{
			frame[i]="ze";
		}
		c=0;
		for(i=0;i<pn;i++)
		{
			fla=0;
			if(frame[c]=="ze")
			{
				frame[c]=page[i];
				fault++;
				c++;
				System.out.println("iteration"+i);
				System.out.println("page:"+page[i]+"\n");
				for(t=0;t<fn;t++)
					System.out.print(frame[t]+"\t");
				System.out.println("Fault:"+fault);
			}
			else
			{
				
				for(j=0;j<fn;j++)
				{
					
					comp=frame[j].compareTo(page[i]);
					
					if(comp==0)
					{
						hit++;
						fla=1;
						System.out.println("iteration"+i);
						//it++;
						System.out.println("page:"+page[i]+"\n");
						for(t=0;t<fn;t++)
							System.out.print(frame[t]+"\t");
						System.out.println("Hit:"+hit);
					}
				}
				if(fla==0)
				{
					fault++;
					for(lr=0;lr<fn;lr++)
					{
						itemp=i;
						itemp=itemp-1;
						while((com=frame[lr].compareTo(page[itemp]))!=0)
						{
							itemp--;
						}
						if(itemp<min)
						{
							min=itemp;
							ch=page[min];
						}
					}
					for(ct=0;ct<fn;ct++)
					{
						if(frame[ct].equals(ch))
							frame[ct]=page[i];
							min=999;
					}
					System.out.println("iteration"+i);
					System.out.println("page:"+page[i]+"\n");
					for(t=0;t<fn;t++)
						System.out.print(frame[t]+"\t");
					System.out.println("Fault:"+fault);
				}
			}
		}
		System.out.println("\nResult:");
		System.out.println("Total Fault:"+fault);
		faultrate=(float)fault/pn;
		System.out.println("faultrate="+faultrate);
	}
	public static void optimal(String page[],int pn,int fn)
	{
		int i,j,c,fault=0,hit=0,point=0,fla,it=1,t,comp,itemp=0,lr,max=0,ct,com;
		float faultrate;
		String ch=new String();
		String frame[]=new String[50];
		for(i=0;i<fn;i++)
		{
			frame[i]="ze";
		}
		c=0;
		for(i=0;i<pn;i++)
		{
			fla=0;
			if(frame[c]=="ze")
			{
				frame[c]=page[i];
				fault++;
				c++;
				System.out.println("iteration"+i);
				System.out.println("page:"+page[i]+"\n");
				for(t=0;t<fn;t++)
					System.out.print(frame[t]+"\t");
				System.out.println("Fault:"+fault);
			}
			else
			{
				
				for(j=0;j<fn;j++)
				{
					
					comp=frame[j].compareTo(page[i]);
					
					if(comp==0)
					{
						hit++;
						fla=1;
						System.out.println("iteration"+i);
						System.out.println("page:"+page[i]+"\n");
						for(t=0;t<fn;t++)
							System.out.print(frame[t]+"\t");
						System.out.println("Hit:"+hit);
					}
				}
				if(fla==0&&i==(pn-1))
				{
					fault++;
					frame[point]=page[i];
					System.out.println("iteration"+i);
					System.out.println("page:"+page[i]+"\n");
						for(t=0;t<fn;t++)
							System.out.print(frame[t]+"\t");
					System.out.println("Fault:"+fault);
				}
				else
				{
				if(fla==0)
				{
					fault++;
					
					for(lr=0;lr<fn;lr++)
					{
						itemp=i;
						itemp=itemp+1;
						while((com=frame[lr].compareTo(page[itemp]))!=0)
						{
							itemp++;
							if(itemp==pn)
							break;
						}
						if(itemp==pn)
							break;
						if(itemp>max)
						{
							max=itemp;
							ch=page[max];
						}
					}
					if(itemp==pn)
						frame[lr]=page[i];
					else
					{
						for(ct=0;ct<fn;ct++)
						{
							if(frame[ct].equals(ch))
								frame[ct]=page[i];
							max=0;
						}
					}
					System.out.println("iteration"+i);
					System.out.println("page:"+page[i]+"\n");
					for(t=0;t<fn;t++)
						System.out.print(frame[t]+"\t");
					System.out.println("Fault:"+fault);
				}
				}
			}
		}
		System.out.println("\nResult:");
		System.out.println("Total Fault:"+fault);
		faultrate=(float)fault/pn;
		System.out.println("faultrate="+faultrate);
	}
}
/*OUTPUT
tejal@ubuntu:~/Desktop/coa$ javac PageRep.java
tejal@ubuntu:~/Desktop/coa$ java PageRep
Enter page stream size
10
Enter page stream
C A D B E B A B C D
Enter frame size
4
---------- FIFO Page Replacement ----------------
iteration0
page:C
C	ze	ze	ze	Fault:1
iteration1
page:A
C	A	ze	ze	Fault:2
iteration2
page:D
C	A	D	ze	Fault:3
iteration3
page:B
C	A	D	B	Fault:4
iteration4
page:E
E	A	D	B	Fault:5
iteration5
page:B
E	A	D	B	Hit:1
iteration6
page:A
E	A	D	B	Hit:2
iteration7
page:B
E	A	D	B	Hit:3
iteration8
page:C
E	C	D	B	Fault:6
iteration9
page:D
E	C	D	B	Hit:4
Result:
Total Fault:6
faultrate=0.6
---------- LRU Page Replacement ----------------
iteration0
page:C
C	ze	ze	ze	Fault:1
iteration1
page:A
C	A	ze	ze	Fault:2
iteration2
page:D
C	A	D	ze	Fault:3
iteration3
page:B
C	A	D	B	Fault:4
iteration4
page:E
E	A	D	B	Fault:5
iteration5
page:B
E	A	D	B	Hit:1
iteration6
page:A
E	A	D	B	Hit:2
iteration7
page:B
E	A	D	B	Hit:3
iteration8
page:C
E	A	C	B	Fault:6
iteration9
page:D
D	A	C	B	Fault:7
Result:
Total Fault:7
faultrate=0.7
---------- OPTIMAL Page Replacement ----------------
iteration0
page:C
C	ze	ze	ze	Fault:1
iteration1
page:A
C	A	ze	ze	Fault:2
iteration2
page:D
C	A	D	ze	Fault:3
iteration3
page:B
C	A	D	B	Fault:4
iteration4
page:E
C	A	E	B	Fault:5
iteration5
page:B
C	A	E	B	Hit:1
iteration6
page:A
C	A	E	B	Hit:2
iteration7
page:B
C	A	E	B	Hit:3
iteration8
page:C
C	A	E	B	Hit:4
iteration9
page:D
D	A	E	B	Fault:6
Result:
Total Fault:6
faultrate=0.6*/

