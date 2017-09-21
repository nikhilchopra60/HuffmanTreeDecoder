package com.huffman;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class DecodeTree{
	static DecodeTreeNode root=new DecodeTreeNode();
	static String decodeFile="decoded.txt";
	
	public static void addToDecodeTree(String data,String seq){
		
		 DecodeTreeNode temp = root;
	        int i = 0;
	        for(i=0;i<seq.length()-1;i++){

	          if(seq.charAt(i)=='0'){
	                if(temp.left == null){
	                    temp.left = new DecodeTreeNode();
	                    temp = temp.left;
	                }
	                else{
	                   temp = (DecodeTreeNode) temp.left;
	                }
	            }
	            else
	              if(seq.charAt(i)=='1'){
	                if(temp.right == null){
	                    temp.right = new DecodeTreeNode();
	                    temp = temp.right;
	                }
	                else{
	                    temp = (DecodeTreeNode) temp.right;
	                }
	         }}

	        if(seq.charAt(i)=='0'){

	            temp.left = new DecodeTreeNode(data);
	            //System.out.println("setting data"+ data);
	           }
	        else{
	            temp.right = new DecodeTreeNode(data);
	            //System.out.println("setting data"+ data);

	        }
		
		
	}
	
	public static void decodeMessage(String encoding) throws IOException{
		BufferedWriter bw =  new BufferedWriter(new FileWriter(decodeFile));
        DecodeTreeNode temp = root;
        for(int i = 0;i<encoding.length();i++){
        	//System.out.println(encoding.charAt(i));
        	
            if(encoding.charAt(i) == '0'){
                temp = temp.left;

                if(temp.left == null && temp.right == null){
                    
                    bw.write(temp.getData()+"\n");
                	
                	temp = root;
                }
            }
            else
            {
                temp = temp.right;
                if(temp.left == null && temp.right == null){
                	
                	bw.write(temp.getData()+"\n");
                	
                	temp = root;  
                }

            }
        }
        bw.close();
	}
	
	
	

}