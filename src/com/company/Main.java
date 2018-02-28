package com.company;



import java.io.BufferedReader;
import java.io.*;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

//bug record
// unable to find any ladder

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        Set<String> dictionary = new HashSet<String>();
        String filename = "dictionary.txt";
        String word1;
        String word2;
        Scanner sc = new Scanner(System.in);
        //read the dictionary file and store the words in the set
        //File dic = new File("dictionary.txt");
        BufferedReader dic_reader= new BufferedReader(new FileReader(filename));
        String tmpword = null;
        while((tmpword=dic_reader.readLine())!=null)
        {
            dictionary.add(tmpword);
        }

        //
        Queue<Stack<String>> words = new LinkedBlockingQueue<Stack<String>>();
        Stack<String> final_result = new Stack<>();
        Set<String> word_collection = new HashSet<>();
        //get word1 and word2
        System.out.println("Word #1 (or Enter to quit):");
        word1 = sc.nextLine();
        if(word1=="")
        {
            System.out.println("Have a nice day.");
            return;
        }
        System.out.println("Word #2 (or Enter to quit):");
        word2 = sc.nextLine();
        if(word2=="")
        {
            System.out.println("Have a nice day.");
            return;
        }

        //error handling
        if(word1==word2 || dictionary.contains(word1)==false||dictionary.contains(word2)==false)
        {
            System.out.println("word error");
            return;
        }

        //build the ladder
        Stack<String> word_container = new Stack<String>();
        boolean get_result = false;
        word_container.push(word1);
        words.add(word_container);

        while (!words.isEmpty())
        {
            System.out.println("words cycle");
            Stack<String> tmp_stack = words.remove();
            String tmp_word = tmp_stack.peek();

            for (int i = 0; i < tmp_word.length() ; i++) {
                for(char character= 'a';character<='z';character++)
                {
                    String new_word = tmp_word.replace(tmp_word.charAt(i),character);
                    //System.out.println(new_word);
                    if(new_word==word2)
                    {
                        get_result=true;
                        tmp_stack.push(new_word);
                        while (!tmp_stack.isEmpty())
                        {
                            final_result.push(tmp_stack.pop());
                        }
                        break;
                    }
                    if(!dictionary.contains(new_word)||new_word==tmp_word||word_collection.contains(new_word)) continue;
                    word_collection.add(new_word);
                    Stack<String> tmp_stack2 = tmp_stack;
                    tmp_stack2.push(new_word);
                    words.add(tmp_stack2);
                }

            }
            if(get_result==true) break;
        }
        if(final_result.isEmpty()) System.out.println("the ladder does not exist.");
        else
        {
            System.out.println("A ladder from "+word1+" to "+word2+": ");
            while (!final_result.isEmpty())
            {
                System.out.println(final_result.pop());
            }
        }

    }
}
