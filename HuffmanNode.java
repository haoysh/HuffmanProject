//Sung Ho Youn 12/11/2015
//CSE 143 AQ / TA: Melissa Medsker
//Assignment #8: Huffman Coding
//This program governs the structures for the collection of characters.

import java.util.*;

public class HuffmanNode implements Comparable<HuffmanNode>{
    public int freq; //number of occurrences of the character.
    public int ascii; //number representing a character in form of ASCII
    public HuffmanNode left; //The further left-side structure of a collection
    public HuffmanNode right; //The further right-side structure of a collection
   
   //Pre  : Takes the frequency and the ASCII number of a character.
   //Post : Set the structure into given frequency and ASCII number.
   public HuffmanNode(int freq, int ascii){
      this.freq = freq;
      this.ascii = ascii;
   }
   
   //Pre  : Takes the frequency of character only.
   //Post : set the structure to a given frequency, and no further structures made.
   public HuffmanNode(int freq){
      this(freq, null, null);
   }
   
 //Pre : Takes the frequency of character, and further structures from the collection.
 //Post : Each structures are assigned, as well as the current structure's frequency.
   public HuffmanNode(int freq, HuffmanNode left, HuffmanNode right){
      this.freq = freq;
      this.left = left;
      this.right = right;
   }
   
 //Pre : Takes another structure of a collection to compare to.Great compareTo comment!
 //Post : Compares the two structures' frequency. If other onehas higher
 // frequency then return negative value, if other is lower, returnpositive value.
 // If they have same frequency then return 0.
   public int compareTo(HuffmanNode other){
      if(other.freq > freq){
         return -1;
      } else if (other.freq < freq){
         return 1;
      } else {
         return 0;
      }
   }
}