//Sung Ho Youn 12/11/2015
//CSE 143 AQ / TA: Melissa Medsker
//Assignment #8: Huffman Coding
//This program -1: We're not working with any files directly in this
//numbers that leads to certain ASCII code. The program also decodes a compressed
//file that is encoded with the given code file and text file.  

import java.util.*;
import java.io.*;

public class HuffmanTree{
   private HuffmanNode code; //A collection of characters
   
//Pre : Takes a list of total possible ASCII characters and their counts.
//Post : Convert the list by storing only characters that occurs atleast once.  
//The list contains one character that represent end of file. The list is sorted by 
//characters' occurrences. Using this list, build and store the collection
//of characters in form of ASCII code.
   public HuffmanTree(int[] counts){
   Queue<HuffmanNode> codes = new PriorityQueue<HuffmanNode>();
      for(int i = 0; i < counts.length; i++){
         if(counts[i] != 0){
            code = new HuffmanNode(counts[i], i);
            codes.add(code); 
         }
      }
      codes.add(new HuffmanNode(1, counts.length));
      while(codes.size() > 1){
         HuffmanNode left = codes.remove();
         HuffmanNode right = codes.remove();
         code = new HuffmanNode(left.freq + right.freq, left, right);
         codes.add(code);
      }
   }
   
//Pre  : Write output of characters' ASCII codes and their binary codes to create characters.
//Post : An output writes the ASCII code of character then the specific code next line. 
//       Repeats until it has gone over the whole collection. 
   public void write(PrintStream output){
      write(output, code, "");
   }
   
//Pre : Goes over the whole structures of a collection. When going over, the specific 
//      binary number is stacked up to later represent the specific code for the character. 
//Post : The specific codes and ASCII codes are written down 
//       until no more sides of structures are left to check.   
   private void write(PrintStream output, HuffmanNode current, String bits){
      if(current.left == null && current.right == null) {
         output.println(current.ascii);
         output.println(bits);
      } else { 
         write(output, current.left, bits + "0");
         write(output, current.right, bits + "1");
      }
   }
   
//Constructs a collection of characters for decoding.
//Pre  : Takes a file that contains the ASCII code and specific code which leads to that ASCII code character.
//Post : The collection of characters are made and stored until file has no lines to go over.
//       Since the frequency of characters are unknown it is not stored.
   public HuffmanTree(Scanner input){
      while(input.hasNext()){
         code = build(Integer.parseInt(input.nextLine()), input.nextLine(), code);
      }
   }
   
//Helps to construct a collection of characters.
//Pre : Takes a ASCII code of character, and a binary code from a file.
//Post : Initiates a new collection when a collection is null. Left-side of structure
//       is created if the digit from binary code is 0, otherwise create right-side. 
//       After all digits are gone over, return the current structure with ASCII code data.
   private HuffmanNode build(int letter, String bits, HuffmanNode current){
      if (current == null){
         current = new HuffmanNode(0);
      } 
      if(bits.length() == 0){
         current = new HuffmanNode(0, letter);
      } else if (bits.substring(0, 1).equals("0")){
         current.left = build(letter, bits.substring(1), current.left);
      } else {
         current.right = build(letter, bits.substring(1), current.right);
      }
      return current;
   }
   
//Decodes and produce an output of the encoded file. 
//Pre  : Takes an -1: Not necessarily8-bits form encoded file and the ASCII code that represent the 'end of file'.
//Post : Go over each bit from encoded file. 0 means the left-side of the structure and
//       1 is the right side of the structure of the collection. 
//       When there are no sides to go over, check the ASCII code of the structure. If it
//       represents end of file, decode end. Otherwise, put a character that represents 
//       following ASCII code in an output file. 
   public void decode(BitInputStream input, PrintStream output, int eof){
      HuffmanNode current = code;
      while(current.ascii != eof){
         if(current.left == null && current.right == null){
            output.write(current.ascii);
            current = code;
         }
         if(input.readBit() == 0){
            current = current.left;
         } else {
            current = current.right;
         }
      }
   }
}   