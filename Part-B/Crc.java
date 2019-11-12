package practice;

import java.io.*;

public class Crc {
	
	public static void main(String args []) throws IOException
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		int [] message;
		int [] gen;
		int [] app_message;
		int [] rem;
		int [] trans_message;
		int message_bits, gen_bits, total_bits;
		
		System.out.println("Enter the no of msg bits");
		message_bits = Integer.parseInt(br.readLine());

		message = new int[message_bits];
		System.out.println("enter the message");
		for(int i=0; i<message_bits; i++)
			message[i] = Integer.parseInt(br.readLine());

		System.out.println("Enter the number generator bits");
		gen_bits = Integer.parseInt(br.readLine());

		gen = new int[gen_bits];
		System.out.println("enter the generate bits");
		for(int i=0; i<gen_bits; i++)
			gen[i] = Integer.parseInt(br.readLine());

		total_bits = message_bits+ gen_bits-1;
		app_message = new int[total_bits];
		rem = new int[total_bits];
		trans_message = new int[total_bits];

		for(int i=0;i<message.length;i++)
			app_message[i] = message[i];

		System.out.println("message bits");
		for(int i=0;i<message_bits;i++)
			System.out.println(message[i]);

		System.out.println("Generated bits");
		for(int i=0;i<gen_bits;i++)
			System.out.println(gen[i]);

		System.out.println("Appended message");
		for(int i=0;i<app_message.length;i++)
			System.out.println(app_message[i]);

		for(int i=0;i<app_message.length;i++)
			rem[i] = app_message[i];
		rem = computecrc(gen,rem);

		for(int i=0;i<app_message.length;i++)
			trans_message[i] = app_message[i]^rem[i];

		System.out.println("Transmitted msg from transmitter");
		for(int i=0;i<trans_message.length;i++)
			System.out.println(trans_message[i]);

		System.out.println("Enter received message");
		for(int i=0;i<trans_message.length;i++)
			trans_message[i] = Integer.parseInt(br.readLine());

		System.out.println("Message of"+total_bits+"bits received");
		System.out.println("Received message is");
		for(int i=0;i<trans_message.length;i++)
			System.out.println(trans_message[i]);

		for(int j=0;j<trans_message.length;j++)
			rem[j] = trans_message[j];
		rem = computecrc(gen,rem);

		for(int i=0;i<rem.length;i++)
		{
			if(rem[i] != 0)
			{
				System.out.println("error");
				break;
			}
			if(i == rem.length-1)
				System.out.println("No error");
		}
		
			
	}
	
	static int [] computecrc( int gen[], int rem[])
	{
		int current = 0;
		while(true)
		{
			for(int i=0;i<gen.length;i++)
				rem[current+i] = (rem[current+i]^gen[i]);
			while((rem[current] == 0) && (current != rem.length-1))
			{
				current++;
			}
			if(rem.length-current<gen.length)
			{
				break;
			}
		}
		return rem;
	}
}
