package com.huffman;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



public class HuffmanCoding {
	
	
	
	static final String code_table = "code_table.txt";
	
	
	public static HashMap<Integer,HashMapNode>hmap=new HashMap<Integer,HashMapNode>();
		
		
	public static void build_freq_table(String inputFile) throws IOException{
		
		
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		String line = "";
		
		while((line = br.readLine()) != null) {
			
			String vars[] = line.split(" ");
		
				int var = Integer.parseInt(vars[0]);
			
			if(hmap.containsKey(var)){
				
				HashMapNode node = hmap.get(var);
				node.freq = node.freq+1;
				hmap.put(var, node);
				
				
				
			}
			else{
				HashMapNode hmn=new HashMapNode();
				hmap.put(var,hmn);
			}
						
		}
		
		
		br.close();
		
	
	}
	
	
	
public static void build_tree_using_binary_heap(){
		
		
		try{
			HuffmanTreeNode treeRoot = null;
			
		
			HuffmanTreeNode[] nodes = new HuffmanTreeNode[hmap.size()];
			int i=0;
			for (Map.Entry<Integer,HashMapNode> e : hmap.entrySet()) {
				
				nodes[i] = new HuffmanTreeNode(e.getKey(),e.getValue().freq);
				i++;
			}
			
			
			
			
			MinBinaryHeap mbheap = new MinBinaryHeap(nodes);
			while (mbheap.getSize() != 1) {
				
				HuffmanTreeNode left = mbheap.extractMin();
				HuffmanTreeNode right = mbheap.extractMin();

				HuffmanTreeNode top = new HuffmanTreeNode(Integer.MAX_VALUE, left.getFrequency()+ right.getFrequency());
				top.setLeft(left);
				top.setRight(right);
				mbheap.insertItem(top);
			}
			treeRoot = mbheap.extractMin();
			
			BufferedWriter bw =  new BufferedWriter(new FileWriter(code_table));
			
			
			String s="";
			makeCodeTable(treeRoot, s,bw);
		
			bw.flush();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}

public static void build_tree_using_FourAry_heap(){
	
	
	try{
		HuffmanTreeNode treeRoot = null;
		
	
		HuffmanTreeNode[] nodes = new HuffmanTreeNode[hmap.size()];
		int i=0;
		for (Map.Entry<Integer,HashMapNode> e : hmap.entrySet()) {
			
			nodes[i] = new HuffmanTreeNode(e.getKey(),e.getValue().freq);
			i++;
		}
		
		
		
		
		MinFourAryHeap mbheap = new MinFourAryHeap(nodes);
		while (mbheap.getSize() != 3) {
			
			HuffmanTreeNode left = mbheap.extractMin();
			HuffmanTreeNode right = mbheap.extractMin();
			
			HuffmanTreeNode top = new HuffmanTreeNode(Integer.MAX_VALUE, left.getFrequency()+ right.getFrequency());
			top.setLeft(left);
			top.setRight(right);
			mbheap.insertItem(top);
		}
		treeRoot = mbheap.extractMin();
		
		BufferedWriter bw =  new BufferedWriter(new FileWriter(code_table));
		
		
		String s="";
		makeCodeTable(treeRoot, s,bw);
		bw.flush();
	}
	catch(Exception e){
		e.printStackTrace();
	}
	
}

public static void build_tree_using_pairing_heap(){
	
	
	try{
		HuffmanTreeNode treeRoot = null;
		
	
		HuffmanTreeNode[] nodes = new HuffmanTreeNode[hmap.size()];
		int i=0;
		for (Map.Entry<Integer,HashMapNode> e : hmap.entrySet()) {
			//setting the data and frequency
			nodes[i] = new HuffmanTreeNode(e.getKey(),e.getValue().freq);
			i++;
		}
		
		
		//HuffmanTree.makeHuffmanTreeAndMakeCodeTable(nodes);
		
		PairingHeap mbheap = new PairingHeap(nodes);
		while (mbheap.root.child!=null) {
			
			HuffmanTreeNode left = mbheap.removeMin();
			//System.out.println("removed value1 = "+left.getData());
			HuffmanTreeNode right = mbheap.removeMin();
			//System.out.println("removed value2 = "+right.getData());
			//if(right!=null){
			HuffmanTreeNode top = new HuffmanTreeNode(Integer.MAX_VALUE, left.getFrequency()+ right.getFrequency());
			top.setLeft(left);
			top.setRight(right);
			mbheap.insertItem(top);
		//	}
			//else{
			//	treeRoot = left;
			//	break;
		//	}
		}
		treeRoot = mbheap.removeMin();
		//System.out.println("calling bw ");
		BufferedWriter bw =  new BufferedWriter(new FileWriter(code_table));
		
		///////////////////////////////////////////////////////////////////
		String s="";
		makeCodeTable(treeRoot, s,bw);
	/////////////////////////////////////////////////////////////////////
		bw.flush();
	}
	catch(Exception e){
		e.printStackTrace();
	}
	
}






public static void makeCodeTable(HuffmanTreeNode root, String code,BufferedWriter bw) throws IOException {
	// Assign 0 to left edge and recur
	if (root!=null){
		//System.out.println("in code table " + root.getData());
		//code = code+"0";			
		makeCodeTable(root.left, code+"0",bw);
		
		if(root.left==null && root.right==null){
		
		int var=root.getData();	
			
		bw.write(var + " " + code);
		//codeTableHashMap.put(root.getData(), code);
		
		HashMapNode node = hmap.get(var);
		node.bits = code;
		hmap.put(var, node);
		
		
		bw.newLine();
		}
		//code = code+"1";
		makeCodeTable(root.right, code+"1",bw);
		
		
		//System.out.println();
		
	}
}


	public static void main(String args[]){
		Scanner sc=new Scanner(System.in);
		String input_file=sc.next();
		try {
			HuffmanCoding.build_freq_table(input_file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(hmap);
		
		long startTime   = System.currentTimeMillis();
		build_tree_using_pairing_heap();
		long endTime   = System.currentTimeMillis();
		
		long totalTime = endTime - startTime;
		System.out.println("Time using binary heap (millisecond): " +totalTime);
		
		sc.close();
		
		//hc.convertInputToencoded();
		
		
	}
	
	public static String convertInputToencoded(String inputFile) throws IOException{
				BufferedReader br = new BufferedReader(new FileReader(inputFile));
				String line = "";
				StringBuilder pText=new StringBuilder();
				while((line = br.readLine()) != null) {
					
					String vars[] = line.split(" ");
					
					
					
						int var = Integer.parseInt(vars[0]);
						HashMapNode node = hmap.get(var);
						pText.append(node.bits);
					
								
				}
				
				
				br.close();
				//this.hmap=hmap;
			return pText.toString();
		
	}
	
}