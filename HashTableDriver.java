package cecs328.project_2;

import java.io.IOException;
import java.util.Scanner;

public class HashTableDriver {
	final static Scanner input = new Scanner(System.in);
	public static void main(String[] args) throws IOException 
	{
		//============================================== Local variables
		int choice = -1;
		String test = "";
		StopWatch timer = new StopWatch();
		//=============================================== HTable Div
		HashTable ht = new HashTable(1);
		//Inserting with Division Function
		timer.Start();
		for (int i = 0; i < ht.KEYS_USED; i++)
			ht.insertKey(ht.pageList[i]);
		timer.Stop();
		System.out.println("Time required to insert 100 keys w/ Div function: " + timer.getElapsedTime() + " nanoseconds");
		System.out.println();
		//=============================================== HTable Mult
		HashTable htm = new HashTable(2);
		//Inserting with Multiplication Function
		timer.Start();
		for (int i = 0; i < ht.KEYS_USED; i++)
			htm.insertKey(ht.pageList[i]);
		timer.Stop();
		System.out.println("Time required to insert 100 keys w/ Mult function: " + timer.getElapsedTime() + " nanoseconds");
		//=============================================== Initial Print using Div
		System.out.println("Multiplication Method:");
		htm.printTable();
		System.out.println("Collisions: " + htm.collisions);
		System.out.println("Division Method:");
		ht.printTable();
		System.out.println("Collisions: " + ht.collisions);
		//=============================================== Menu Operations
		while(choice != 0)
		{
			System.out.println("What would you like to do?");
			System.out.println("1. Insert new key");
			System.out.println("2. Search for key");
			System.out.println("3. Delete key");
			System.out.println("4. Print table");
			System.out.println("0. Quit");
			choice = Integer.parseInt(input.nextLine());
			switch(choice)
			{
			case 1:
				System.out.println("What is the name of the key?");
				test = input.nextLine();
				timer.Start();
				ht.insertKey(test);
				break;
			case 2:
				System.out.println("What is the name of the key?");
				test = input.nextLine();
				timer.Start();
				if(ht.searchKey(test))
					System.out.println("true");
				else
					System.out.println("false");
				break;
			case 3:
				System.out.println("What is the name of the key?");
				test = input.nextLine();
				timer.Start();
				System.out.println(ht.deleteKey(test));
				break;
			case 4:
				timer.Start();
				ht.printTable();
				break;
			case 0:
				System.out.println("Have a nice day!");
				break;
			}
			timer.Stop();
			System.out.println("Operation took: " + timer.getElapsedTime() + " nanoseconds.");
		}
	}
}
