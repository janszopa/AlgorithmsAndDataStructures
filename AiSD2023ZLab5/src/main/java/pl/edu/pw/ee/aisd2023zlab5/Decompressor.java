package pl.edu.pw.ee.aisd2023zlab5;

import pl.edu.pw.ee.aisd2023zlab5.BitwiseOperations.BitReader;
import pl.edu.pw.ee.aisd2023zlab5.HuffmanTree.Node;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Decompressor {
    private String filePathToRead;
    private String filePathToWrite;
    private Node<Integer, Integer> root;
    private BitReader bitReader;
    private String[] codes;

    private enum Side {
        LEFT, RIGHT, NONE;
    }

    public Decompressor(String filePathToRead, String filePathToWrite) {
        this.filePathToRead = filePathToRead;
        this.filePathToWrite = filePathToWrite;

        bitReader = new BitReader(filePathToRead);
        codes = new String[256];
        root = null;
    }

    public void decompressFile() {
        rebuildHuffTree(null, Side.NONE);

        RandomAccessFile fileToWrite;
        try {
            fileToWrite = new RandomAccessFile(filePathToWrite, "rw");

            decodeDataAndWriteToFile(fileToWrite);

            fileToWrite.close();
        } catch (IOException e) {
            System.err.println("Error when opening the file!" + e.getMessage());
        }
    }

    private void rebuildHuffTree(Node<Integer, Integer> parentNode, Side side){
        Node<Integer, Integer> childNode = createNode();
        parentNode = linkToTree(parentNode, childNode, side);

        if(childNode.getValue() != 256){
            return;
        }

        rebuildHuffTree(childNode, Side.LEFT);
        rebuildHuffTree(childNode, Side.RIGHT);
    }

    private Node<Integer, Integer> createNode(){
        int bitRead = bitReader.nextBit();
        int value;

        if(bitRead == 0){
            value = 256;
        } else {
            value = bitReader.nextByte();
        }

        return new Node<>(0, value);
    }

    private Node<Integer, Integer> linkToTree(Node<Integer, Integer> parentNode, Node<Integer, Integer> childNode, Side side){
        if(parentNode == null) {
            parentNode = childNode;
            this.root = parentNode;
            return parentNode;
        }

        if(side == Side.LEFT)
            parentNode.setLeft(childNode);
        else if(side == Side.RIGHT)
            parentNode.setRight(childNode);

        return parentNode;
    }
    private void decodeDataAndWriteToFile(RandomAccessFile fileToWrite) {
        int leftOverBits = bitReader.nextByte();

        boolean leftOverSkipped = false;
        boolean skipedRoot = false;

        Node<Integer, Integer> node = null;
        int tmpBit;
        while ( (tmpBit = bitReader.nextBit()) != -1 ) {
            if(bitReader.isLastByte() && !leftOverSkipped) {
                bitReader.skipLeftOverBits(7 - leftOverBits);

                leftOverSkipped = true;
                continue;
            }

            if(!skipedRoot) {
                node = root;
                skipedRoot = true;
            } else if (tmpBit == 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }

            if(node.getValue() != 256) {
                try {
                    fileToWrite.write(node.getValue());
                } catch (IOException e) {
                    System.err.println("Error when writing to file!" + e.getMessage());
                }
                skipedRoot = false;
            }
        }

    }
}
