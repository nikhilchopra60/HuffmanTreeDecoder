package com.huffman;

public class DecodeTreeNode {

	DecodeTreeNode left= null;
	DecodeTreeNode right=null;
    String data=null;

    public DecodeTreeNode(){

    }
    public DecodeTreeNode(String data){
        this.data = data;
    }
    public void setData(String data){
        this.data = data;
    }
    public String getData(){
        return this.data;
    }
   
}
