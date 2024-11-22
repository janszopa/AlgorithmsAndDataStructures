package pl.edu.pw.ee.aisd2023zlab5.BitwiseOperations;

import java.io.IOException;
import java.io.RandomAccessFile;

public class BitWriter {
    private int byteToWrite;
    private int bitsCounter;
    public BitWriter(String fileName) {
        byteToWrite = 0;
        bitsCounter = 0;
    }

    private boolean isWholeByte() { return bitsCounter == 8; }

    public void writeZero(RandomAccessFile fileToWrite) {
        if(isWholeByte()) {
            writeToFile(byteToWrite, fileToWrite);
        }
        byteToWrite = byteToWrite << 1;
        bitsCounter++;
    }

    public void writeOne(RandomAccessFile fileToWrite) {
        if(isWholeByte()) {
            writeToFile(byteToWrite, fileToWrite);
        }
        byteToWrite = byteToWrite << 1;
        byteToWrite += 1;
        bitsCounter++;
    }
    public void writeChar(int decimalVal, RandomAccessFile fileToWrite) {
        for(int i = 0; i < 8; i++) {
            if (isWholeByte()) {
                writeToFile(byteToWrite, fileToWrite);
            }
            byteToWrite = byteToWrite << 1;
            byteToWrite += ( (decimalVal >> (7 - i) & 1) );
            bitsCounter++;
        }
    }
    public void writeCode(String code, RandomAccessFile fileToWrite){
        int n = code.length();
        for(int i = 0; i < n; i++) {
            if (isWholeByte()) {
                writeToFile(byteToWrite, fileToWrite);
            }
            byteToWrite = byteToWrite << 1;
            byteToWrite += code.charAt(i) - '0';
            bitsCounter++;
        }
    }
    public void writeLeftOver(RandomAccessFile fileToWrite){
        writeToFile(byteToWrite, fileToWrite);
    }
    private void writeToFile(int val, RandomAccessFile fileToWrite){
        try {
            fileToWrite.write(byteToWrite);
        } catch (IOException e) {
            System.err.println("Error when writing to file!" + e.getMessage());
        }
        bitsCounter = 0;
        byteToWrite = 0;
    }
    public int getBitsCounter() { return bitsCounter; }
}
