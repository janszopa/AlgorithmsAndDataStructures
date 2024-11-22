package pl.edu.pw.ee.aisd2023zlab5.BitwiseOperations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class BitReader {
    private int byteToRead;

    private int bitsCounter;

    private RandomAccessFile fileToRead;

    public BitReader(String fileName) {
        bitsCounter = 0;

        try {
            fileToRead = new RandomAccessFile(fileName, "r");

        } catch (FileNotFoundException e) {
            System.err.println("Error when opening the file!" + e.getMessage());
        }

        try {
            byteToRead = fileToRead.read();
        } catch (IOException e) {
            System.err.println("Error when reading from file!" + e.getMessage());
        }
    }

    public int nextBit() {
        int bit = 0;

        if(bitsCounter == 8) {
            try {
                byteToRead = fileToRead.read();
            } catch (IOException e) {
                System.err.println("Error when reading from file!" + e.getMessage());
            }
            bitsCounter = 0;
        }

        if(byteToRead == -1) {
            return -1;
        }

        bit = (byteToRead >> (7 - bitsCounter)) & 1;
        bitsCounter++;

        return bit;
    }
    public int nextByte(){
        if(byteToRead == -1) {
            return -1;
        }

        int byteVal = 0;
        for(int i = 0 ;i < 8 ; i++)
        {
            byteVal = byteVal << 1;
            byteVal += nextBit();
        }

        return byteVal;
    }

    public void skipLeftOverBits(int bitsToSkip) {
        for(int i = 0; i < bitsToSkip ; i++) {
            nextBit();
        }
    }

    public boolean isLastByte() {
        boolean isLast = false;

        try {
            isLast = fileToRead.getFilePointer() == fileToRead.length();
        } catch (IOException e) {
            System.err.println("Error when performing operations on file!" + e.getMessage());
        }

        return isLast;
    }
}
