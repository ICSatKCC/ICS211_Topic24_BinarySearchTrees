package binarysearchtree;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.HashMap;

/**
 * Represents a Huffman Tree for data compression and decompression.
 * The tree is built from character frequencies and generates optimal binary codes
 * for each character based on their frequency of occurrence.
 * 
 * @author Lisa Miller
 * @since 11/23/25  
 */
public class HuffmanTree {
    File file;
    BinaryNode<HuffmanNodeData> root;
    HashMap<Character, String> huffmanCodes;

    /**
     * Constructs a Huffman Tree from a file containing character frequencies.
     * 
     * @param file the file containing character-frequency pairs
     */
    public HuffmanTree(File file) {
        this.file = file;
        this.root = null;
        this.huffmanCodes = new HashMap<>();
        this.buildTree();
    }   

    /**
     * Gets the root node of the Huffman tree.
     * 
     * @return the root BinaryNode of the tree
     */
    public BinaryNode<HuffmanNodeData> getRoot() {
        return root;
    }   

    /**
     * Sets the root node of the Huffman tree.
     * 
     * @param root the BinaryNode to set as root
     */
    public void setRoot(BinaryNode<HuffmanNodeData> root) {
        this.root = root;
    }

    /**
     * Builds the Huffman tree from character frequencies.
     * Simplified: directly reads BinaryNode leaves into the priority queue.
     */
    public void buildTree() {
        PriorityQueue<BinaryNode<HuffmanNodeData>> nodeQueue =
            new PriorityQueue<>((a, b) -> a.getData().compareTo(b.getData()));
        readFrequencies(nodeQueue);
        while (nodeQueue.size() > 1) {
            BinaryNode<HuffmanNodeData> left = nodeQueue.poll();
            BinaryNode<HuffmanNodeData> right = nodeQueue.poll();
            HuffmanNodeData merged = new HuffmanNodeData('\0',
                left.getData().getFrequency() + right.getData().getFrequency());
            nodeQueue.add(new BinaryNode<>(merged, left, right));
        }
        this.root = nodeQueue.poll();
    }

    /**
     * Generates Huffman codes for all characters in the tree.
     * Traverses the tree and assigns binary codes where left = '0' and right = '1'.
     */
    public void generateCodes() {
        huffmanCodes.clear();
        generateCodes(this.root, "");
    }
    
    /**
     * Helper method to recursively generate Huffman codes.
     * 
     * @param node the current node being visited
     * @param code the binary code accumulated so far
     */
    private void generateCodes(BinaryNode<HuffmanNodeData> node, String code) {
        if (node == null) {
            return;
        }
        
        // If this is a leaf node (has a character), store the code
        if (node.getLeftChild() == null && node.getRightChild() == null) {
            if (node.getData().getCharacter() != '\0') {
                huffmanCodes.put(node.getData().getCharacter(), code);
            }
        }
        
        // Traverse left with '0' and right with '1'
        generateCodes(node.getLeftChild(), code + "0");
        generateCodes(node.getRightChild(), code + "1");
    }
    
    /**
     * Displays the generated Huffman codes in a formatted table.
     * Shows character, frequency, and corresponding binary code for each character.
     */
    public void displayCodes() {
        System.out.println("Huffman Codes:");
        System.out.println("Character | Frequency | Code");
        System.out.println("----------|-----------|-----");
        for (char ch : huffmanCodes.keySet()) {
            System.out.printf("    %c     |     %d     | %s%n", 
                ch, getFrequency(ch), huffmanCodes.get(ch));
        }
    }
    
    /**
     * Gets the frequency of a specific character.
     * 
     * @param ch the character to look up
     * @return the frequency of the character, or 0 if not found
     */
    private int getFrequency(char ch) {
        return findFrequency(this.root, ch);
    }
    
    /**
     * Recursively finds the frequency of a character in the tree.
     * 
     * @param node the current node being searched
     * @param ch the character to find
     * @return the frequency of the character, or 0 if not found
     */
    private int findFrequency(BinaryNode<HuffmanNodeData> node, char ch) {
        if (node == null) {
            return 0;
        }
        if (node.getData().getCharacter() == ch) {
            return node.getData().getFrequency();
        }
        int leftFreq = findFrequency(node.getLeftChild(), ch);
        if (leftFreq > 0) return leftFreq;
        return findFrequency(node.getRightChild(), ch);
    }
    
    /**
     * Encodes a string using the generated Huffman codes in the HashMap.
     * 
     * @param data the string to encode
     * @return the encoded binary string
     */
    public String encode(String data) {
        // Implementation for encoding data goes here
        StringBuilder encoded = new StringBuilder();
        for (char ch : data.toCharArray()) {
            String code = huffmanCodes.get(ch);
            if (code != null) {
                encoded.append(code);
            } else {
                System.out.println("Character " + ch + " not found in Huffman codes.");
            }
        }
        return encoded.toString();
    }   
    
    /**
     * Decodes a binary string using the Huffman tree.
     * Traverses the tree based on the bits until reaching leaf nodes.
     * 
     * @param encodedData the binary string to decode
     * @return the decoded string
     */
    public String decode(String encodedData) {
        StringBuilder decoded = new StringBuilder();
        BinaryNode<HuffmanNodeData> currentNode = this.root;
        // Traverse the tree based on the bits in the encoded data
        for (char bit : encodedData.toCharArray()) {
            if (bit == '0') {
                currentNode = currentNode.getLeftChild();
            } else if (bit == '1') {
                currentNode = currentNode.getRightChild();
            } else {
                System.out.println("Invalid bit in encoded data: " + bit);
                continue;
            }
            // If we reach a leaf node, append the character and reset to root
            if (currentNode.getLeftChild() == null && currentNode.getRightChild() == null) {
                decoded.append(currentNode.getData().getCharacter());
                currentNode = this.root;
            }
        }
        return decoded.toString();
    }
    
    /**
     * Reads character-frequency pairs from the file and inserts each as a leaf BinaryNode
     * into the provided priority queue.
     *
     * @param nodeQueue priority queue that will receive leaf nodes
     */
    private void readFrequencies(PriorityQueue<BinaryNode<HuffmanNodeData>> nodeQueue) {
        try (Scanner scanner = new Scanner(this.file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] parts = line.split("\\s+");
                    if (parts.length == 2) {
                        try {
                            char character = parts[0].charAt(0);
                            int frequency = Integer.parseInt(parts[1]);
                            nodeQueue.add(new BinaryNode<>(new HuffmanNodeData(character, frequency), null, null));
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid frequency format in line: " + line);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + this.file.getPath());
        }
    }

    /**
     * Prints the entire Huffman tree structure.
     * Shows the prefix codes for each node in the tree.
     */
    public void printTree() {
        printTree(this.root, "");
    }
    
    /**
     * Helper method to recursively print the tree structure.
     * 
     * @param node the current node being printed
     * @param prefix the binary prefix representing the path to this node
     */
    private void printTree(BinaryNode<HuffmanNodeData> node, String prefix) {
          if (node != null) {
                System.out.println(prefix + node.getData());
                printTree(node.getLeftChild(), prefix + "0");
                printTree(node.getRightChild(), prefix + "1");
          }
         }  

    /**
     * Main method for testing the HuffmanTree implementation.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        File file = new File("frequencies.txt");
        HuffmanTree huffmanTree = new HuffmanTree(file);
        huffmanTree.generateCodes();
        huffmanTree.displayCodes();
        //huffmanTree.printTree();
        String data = "kapiolani";
        String encoded = huffmanTree.encode(data);
        System.out.println(data + " encoded in HuffmanTree: " + encoded);
        String decoded = huffmanTree.decode(encoded);
        System.out.println(encoded + " decoded from HuffmanTree: " + decoded);

       }
    }