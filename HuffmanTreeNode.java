package com.huffman;

public class HuffmanTreeNode {
	public int data;
	public HuffmanTreeNode right = null;
	public HuffmanTreeNode left = null;
	public int frequency;

	public HuffmanTreeNode(int data, int frequency) {
		this.data = data;
		this.frequency = frequency;
	}

	public HuffmanTreeNode getRight() {
		return right;
	}

	public void setRight(HuffmanTreeNode right) {
		this.right = right;
	}

	public HuffmanTreeNode getLeft() {
		return left;
	}

	public void setLeft(HuffmanTreeNode left) {
		this.left = left;
	}

	public int getFrequency() {
		return frequency;
	}

	public int getData() {
		return data;
	}

	

}
