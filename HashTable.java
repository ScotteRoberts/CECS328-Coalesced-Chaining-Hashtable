package cecs328.project_2;

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;
import java.io.*;

public class HashTable {
	//=============================================== Local Constants
	final int TABLE_SIZE_M = 233; //maximum table size
	final int r = 37; //prime number to multiply by
	final double p = 0.618033989; //golden ratio
	final int KEYS_USED = 40; // number of keys allowed in the table
	//=============================================== Lists
	String[] pageList = new String[100];	//the titles of each wikipedia page
	String[] paragraphs = new String[100];	//the first paragraph of each wikipedia page
	final ArrayList<String> table = new ArrayList<String>(TABLE_SIZE_M);
	int[] hashIndex = new int[TABLE_SIZE_M];
	int[] paragraphIndex = new int[TABLE_SIZE_M];
	//=============================================== Local Variables
	File inFile;
	Scanner read;
	int collisions = 0;
	boolean loopFlag;
	boolean flag;
	int intKey;
	int tempKey;
	int tableType;
	int runningInsert = 0;
	//=============================================== Constructor
	public HashTable(int choice)
	{
		for (int i = 0; i < TABLE_SIZE_M; i++)
		{
			table.add(null);
			hashIndex[i] = -1;
		}
		tableType = choice;
		fileInput();
	}
	//=============================================== File Input
	void fileInput()
	{
		try
		{
			inFile = new File ("/Users/ScottRoberts/Desktop/wikipediaTitles.txt");
			read = new Scanner(inFile);
		}
		catch(FileNotFoundException e){
			System.out.println(e);}
		int i = 0;
		while(read.hasNextLine() && i < KEYS_USED)
		{
			pageList[i] = read.nextLine();
			i++;
		}
		read.close();
		try
		{
			inFile = new File ("/Users/ScottRoberts/Desktop/wikipediaParagraphs.txt");
			read = new Scanner(inFile);
		}
		catch(FileNotFoundException e){
			System.out.println(e);}
		i = 0;
		while(read.hasNextLine() && i < KEYS_USED)
		{
			paragraphs[i] = read.nextLine();
			i++;
		}
		read.close();
	}
	//=============================================== Key Padding
	//Ensure all strings are at least 25 characters long
	String paddingInput(String stringKey)
	{
		if (stringKey.length() < 25)
			for (int i = stringKey.length(); i <= 25; i++)
				stringKey = stringKey + " ";
		else
			stringKey = stringKey.substring(0, 25);
		return stringKey;
	}
	//========================================= Division Hash Function
	void divFunct(String stringKey)
	{
		intKey = 0;
		for (int i = 0; i< stringKey.length(); i++)
		{
			int ascii = (int) stringKey.charAt(i);
			intKey += (ascii * Math.pow(r, i)) % TABLE_SIZE_M;
		}
		intKey %= TABLE_SIZE_M;
	}
	//========================================= Multiplication Hash Function
	/*
	 * Using the multiplication hash function, we need a Rho (p), table
	 * size (m), and multiple (r) to ensure fewer collisions.
	 */
	public void multFunct(String stringKey)
	{
		intKey = 0;
		for (int i = 0; i< stringKey.length(); i++)
		{
			int ascii = (int) stringKey.charAt(i);
			intKey += (ascii);
		}
		double firstFloor = intKey * p;
		double decimal = (intKey * p - (int)firstFloor);
		double secondFloor = TABLE_SIZE_M * decimal;
		if ((decimal*10) > 4)
			secondFloor++;
		intKey = (int)secondFloor;
	}
	//=============================================== Insert Key/Value
	void insertKey(String stringKey)
	{
		stringKey = paddingInput(stringKey);
		switch(tableType) //Determine which table we are working with
		{
		case 1:
			divFunct(stringKey);
			break;
		case 2:
			multFunct(stringKey);
			break;
		}
		loopFlag = false;
		if(null != table.get(intKey))
		{
			collisions++;
			//If first link is a duplicate
			if (table.get(intKey).equals(stringKey))
			{
				loopFlag = true;
				System.out.println("Duplicate: " + stringKey);
			}
			//If a chain exists
			while(hashIndex[intKey] != -1)
			{
				//Same key
				if (table.get(intKey).equals(stringKey))
				{
					loopFlag = true;
					System.out.println("Duplicate:" + stringKey);
				}	
				intKey = hashIndex[intKey];
				collisions++;
			}
			//If not a duplicate
			if(!loopFlag)
			{
				tempKey = intKey;	//Save the previous value
				while(!loopFlag && null != table.get(intKey))
					intKey++;	//Linear probe until open space
				hashIndex[tempKey] = intKey;
				paragraphIndex[intKey] = runningInsert;
				table.set(intKey,  stringKey);
			}
		}
		else
		{
			table.set(intKey, stringKey);
			paragraphIndex[intKey] = runningInsert;
		}
		runningInsert++;
	}
	
	//=============================================== Search Key/Value
	boolean searchKey(String stringKey)
	{
		stringKey = paddingInput(stringKey);
		collisions = 0;
		switch(tableType) //Determine which table we are working with
		{
		case 1:
			divFunct(stringKey);
			break;
		case 2:
			multFunct(stringKey);
			break;
		}
		loopFlag = false;
		flag = false;
		
		while (!loopFlag)
		{
			if(null == table.get(intKey)) //Blank Space: nothing here
				loopFlag = true;
			else if (table.get(intKey).equals(stringKey)) //Found!
			{
				loopFlag = true;
				flag = true;
			//Print the Article
				String tempString = paragraphs[paragraphIndex[intKey]];
				while(tempString != null && tempString.length() > 50)
				{
					System.out.println(tempString.substring(0, 50));
					tempString = tempString.substring(50, tempString.length());
				}
				System.out.println(tempString);
			}
			else //Continue through the chain
			{
				collisions++;
				if(hashIndex[intKey] != -1)
					intKey = hashIndex[intKey];
				else
					loopFlag = true;
			}
		}
		
		return flag;
	}
	//=============================================== Delete Key/Value
	boolean deleteKey(String stringKey)
	{
		stringKey = paddingInput(stringKey);
		collisions = 0;
		switch(tableType) //Determine which table we are working with
		{
		case 1:
			divFunct(stringKey);
			break;
		case 2:
			multFunct(stringKey);
			break;
		}
		
		loopFlag = false;
		flag = false;
		
		while(!loopFlag && intKey != -1)
		{
			//System.out.println(intKey);
			if(null == table.get(intKey))
				loopFlag = true;
			else if (table.get(intKey).equals(stringKey))
			{
				loopFlag = true;
				flag = true;
				int tempKey = intKey;
				//adjust future keys, if there are any
				if(hashIndex[intKey] == -1)
				{
					System.out.println("First entry.");
					table.set(intKey, null);
				}	
				while(hashIndex[intKey] != -1)
				{
					table.set(intKey, table.get(hashIndex[intKey]));
					tempKey = intKey;
					intKey = hashIndex[intKey];
				}
				table.set(intKey, null);
				hashIndex[tempKey] = -1;
			}
			else
			{
				collisions++;
				intKey = hashIndex[intKey];
			}
		} 
		return flag;
	}
	//=============================================== Print Table
	public void printTable()
	{
		int count = 0;
		System.out.println("=============================================");
		for (int i = 0; i < TABLE_SIZE_M; i++)
		{
			if(null != table.get(i))
			{
				System.out.println("Element " + i + ": " + table.get(i));
				count++;
			}	
		}
		System.out.println("Number of Elements: " + count);
		System.out.println("=============================================");
	}
}
