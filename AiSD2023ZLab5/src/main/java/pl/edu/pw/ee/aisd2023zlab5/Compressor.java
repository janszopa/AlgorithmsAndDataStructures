package pl.edu.pw.ee.aisd2023zlab5;

import pl.edu.pw.ee.aisd2023zlab5.BitwiseOperations.BitWriter;
import pl.edu.pw.ee.aisd2023zlab5.HuffmanTree.Node;

import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Compressor {
    private final String fileToRead;
    private final String filePathToWrite;
    private BitWriter bitWriter;
    private int zerosCounter;
    private int onesCounter;
    public Compressor(String fileToRead, String filePathToWrite) {
        this.fileToRead = fileToRead;
        this.filePathToWrite = filePathToWrite;

        bitWriter = new BitWriter(filePathToWrite);

        zerosCounter = 0;
        onesCounter = 0;
    }
    public void compressToFile(Node<Integer, Integer> huffmanTree, String[] keyCodes, int[] numOfOccurences) {
        try {
            RandomAccessFile fileToWrite = new RandomAccessFile(filePathToWrite, "rw");

            encodeTreeAndWriteToFile(huffmanTree, fileToWrite);
            compressAndWriteData(keyCodes, numOfOccurences, fileToWrite);

            fileToWrite.close();
        } catch (IOException e) {
            System.err.println("Error when opening the file!" + e.getMessage());
        }

    }
    private void compressAndWriteData(String[] keyCodes, int[] numOfOccurences, RandomAccessFile fileToWrite){
        int charCode;
        int lastByteLeftOver;

        try {
            FileReader fileReader = new FileReader(fileToRead);

            lastByteLeftOver = countLeftOverBits(numOfOccurences, keyCodes);
            bitWriter.writeChar(lastByteLeftOver, fileToWrite);

            while ( (charCode = fileReader.read()) != -1 ) {
                bitWriter.writeCode(keyCodes[charCode], fileToWrite);
            }

            bitWriter.writeLeftOver(fileToWrite);
            fileReader.close();
        } catch (IOException e){
            System.err.println("Error when opening the file!" + e.getMessage());
        }
    }

    private int countLeftOverBits(int[] numOfOccurences, String[] keyCodes){
        int dataToWriteLength = 0;

        for (int i = 0; i < numOfOccurences.length; i++){
            if(numOfOccurences[i] != 0){
                dataToWriteLength += numOfOccurences[i] * keyCodes[i].length();
            }
        }

        return (dataToWriteLength + bitWriter.getBitsCounter()) % 8;
    }

    private void encodeTreeAndWriteToFile(Node<Integer, Integer> node, RandomAccessFile fileToWrite){
        if(node == null){
            System.err.println("Error, Tree is null");
            return;
        }
        if (node.isLeaf(node)) {
            int decimalValue = node.getValue();

            bitWriter.writeOne(fileToWrite);
            onesCounter++;

            bitWriter.writeChar(decimalValue, fileToWrite);
        } else if (node.isParent(node)) {
            bitWriter.writeZero(fileToWrite);
            zerosCounter++;

            encodeTreeAndWriteToFile(node.getLeft(), fileToWrite);
            encodeTreeAndWriteToFile(node.getRight(), fileToWrite);
        }
    }
}
