package practice;

import java.util.*;

public class Leaky {
	
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in);
		int no_groups,bucket_size;
		System.out.println("Enter the size of the bucket");
		bucket_size = in.nextInt();
		System.out.println("Enter the number of groups");
		no_groups = in.nextInt();
		int no_packets[] = new int[no_groups];
		int in_bw[] = new int[no_groups];
		int out_bw,reqd_bw=0,total_packets=0;
		
		for(int i=0;i<no_groups;i++)
		{
			System.out.println("Enter the no of packets for group"+(i+1));
			no_packets[i] = in.nextInt();
			System.out.println("Enter the input bandwidth of group"+(i+1));
			in_bw[i] = in.nextInt();
			if((total_packets+no_packets[i])<=bucket_size)
				total_packets += no_packets[i];
			
			else
			{
				do
				{
					System.out.println("Bucket overflow");
					System.out.println("Enter packets less than "+(bucket_size-total_packets));
					no_packets[i] = in.nextInt();
				}
				while((total_packets+no_packets[i])>bucket_size);
				
				total_packets += no_packets[i];
			}
			reqd_bw += no_packets[i]*in_bw[i];
		}
		
		System.out.println("Enter the output bandwidth");
		out_bw = in.nextInt();
		int temp = reqd_bw;
		int rem_packets = total_packets;
		while((out_bw<temp)&&(rem_packets>0))
		{
			System.out.println("Data sent"+(--rem_packets)+" Packets remaining");
			System.out.println("Bandwidth left "+(temp-=out_bw));
			if((out_bw>temp)&&(rem_packets>0))
				System.out.println("Cant send data due to insufficient bandwidth");
			
		}
		
	}

}
