package pl.edu.pw.ee.aisd2023zlab5.HuffmanTree;


import java.io.FileReader;
import java.io.IOException;

public class HuffmanTree {

    private String fileName;

    private minHeap minHeap;

    private String[] keyCodes;

    int[] numOfOccurrences;
    public HuffmanTree(String fileName){
        this.fileName = fileName;
        keyCodes = new String[256];
        minHeap = new minHeap();
        numOfOccurrences = new int[256];
    }

    public void countNumOfOccurences(String fileName){
        int charCode;
        try {
            FileReader fileReader = new FileReader(fileName);

            while ((charCode = fileReader.read()) != -1){
                numOfOccurrences[charCode]++;
            }
        } catch (IOException e){
            System.err.println("Error when opening the file!");
        }
    }

    private void buildPriorityQueue(int[] numOfOccurrences){
        for (int i = 0; i <256; i++){
            if(numOfOccurrences[i] != 0) {
                Node<Integer, Integer> tmpNode = new Node<>( numOfOccurrences[i], i);
                minHeap.insertNode(tmpNode);
            }
        }
    }

    public Node<Integer, Integer> buildHuffTree(){
        countNumOfOccurences(fileName);

        buildPriorityQueue(numOfOccurrences);

        while (minHeap.getHeapSize() > 1) {
            Node<Integer, Integer> newTree = mergeTrees(minHeap.getMin(), minHeap.getMin());
            minHeap.insertNode(newTree);
        }

        return minHeap.getMin();
    }

    private Node<Integer, Integer> mergeTrees(Node<Integer, Integer> firstNode, Node<Integer, Integer> secondNode){
        Node<Integer, Integer> newTree;
        int newCounter = firstNode.getCounter() + secondNode.getCounter();

        newTree= new Node<>(newCounter, 256);

        newTree.setLeft(firstNode);
        newTree.setRight(secondNode);

        return newTree;
    }

    public void buildDictionary(Node<Integer, Integer> node, String code){
        if(node == null){
            System.out.println("Can not build dictonary out of null");
            return;
        }

        if(node.getLeft() == null && node.getRight() == null){
            keyCodes[node.getValue()] = code;
            return;
        }

        buildDictionary(node.getLeft(), code + "0");
        buildDictionary(node.getRight(), code + "1");
    }
    public String[] getKeyCodes(){return keyCodes;}

    public int[] getNumOfOccurrences(){return numOfOccurrences;}

}
