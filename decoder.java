package com.huffman;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



public class decoder {
	
	
	public static void main(String args[]) throws IOException{
		//Scanner sc=new Scanner(System.in);
		String encoded_file=args[0];
		String codeTable_file=args[1];
		
		long startTime   = System.currentTimeMillis();
		//System.out.print("here inside1");
		parseCodeTableBuildDecodeTree(codeTable_file);
		parseEncodedFileDecode(encoded_file);
		//sc.close();
		
		long endTime   = System.currentTimeMillis();
		
		long totalTime = endTime - startTime;
		System.out.println("Time using binary heap (millisecond): " +totalTime);
		
		
	}
	
	/* Parses Encoded File */
	public static void parseEncodedFileDecode(String encoded_file) {

		try {
			Path path = Paths.get(encoded_file);
			byte[] bytearray = Files.readAllBytes(path);
			StringBuilder s = new StringBuilder();
			for (byte nextbyte : bytearray) {
				s.append(String.format("%8s", Integer.toBinaryString(nextbyte & 0xff)).replace(' ', '0'));
			}
			DecodeTree.decodeMessage(s.toString());
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found Exception in parse_encoded_file_and_decode : " + e.getMessage());
			e.printStackTrace();
		} catch (IOException ex) {
			System.out.println("IO Exception in parse_encoded_file_and_decode : " + ex.getMessage());
			ex.printStackTrace();
		}

	}
	
	/*public static void parseEncodedFileDecode(String encoded_file) throws IOException{
		try (
		        InputStream inputStream = new BufferedInputStream(new FileInputStream(encoded_file));
					) {
			StringBuilder sb=new StringBuilder();
            Integer c=0;
            while ((  c = inputStream.read()) != -1) {
            	//System.out.println("printing c"+c);
            	
            	
            	sb.append(String.format("%8s", Integer.toBinaryString(c)).replace(" ", "0"));
            	
            }
			//System.out.println(sb);
            DecodeTree.decodeMessage(sb.toString());
		}
		
		 catch (IOException ex) {
		        ex.printStackTrace();
		}
			
		
	}*/
	
	
	
	public static void parseCodeTableBuildDecodeTree(String codeTable_file) throws IOException{
		//System.out.println("inside building decode tree");
		BufferedReader br = new BufferedReader(new FileReader(codeTable_file));
		String line = "";
		///////////////////////////////////////////////////////////
		while((line = br.readLine()) != null) {
			
			String vars[] = line.split(" ");
			//System.out.println("1st value is "+vars[0]+" 2nd value is "+ vars[1]);
			DecodeTree.addToDecodeTree(vars[0],vars[1]);
		}
		
		br.close();
		
	}
	
	
}
