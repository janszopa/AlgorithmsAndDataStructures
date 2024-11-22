import org.junit.Assert;
import org.junit.Test;
import pl.edu.pw.ee.aisd2023zlab5.Compressor;
import pl.edu.pw.ee.aisd2023zlab5.Decompressor;
import pl.edu.pw.ee.aisd2023zlab5.HuffmanTree.HuffmanTree;
import pl.edu.pw.ee.aisd2023zlab5.HuffmanTree.Node;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class HuffmanTreeTest {

    private ArrayList<String> testFilesPaths;
    private void prepareTestFiles() {
        testFilesPaths = new ArrayList<>();
        File directory = new File("src\\test\\testFiles\\");
        if(!directory.isDirectory()) {
            return;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            testFilesPaths.add(file.getAbsolutePath());
        }
    }
    @Test
    public void sourceAndDecompressedFiles_shouldBeIdentical() {
        prepareTestFiles();

        //first file
        HuffmanTree huffTree = new HuffmanTree(testFilesPaths.get(0));
        Node<Integer, Integer> huffmanTree = huffTree.buildHuffTree();
        huffTree.buildDictionary(huffmanTree, "0");
        String[] keyCodes = huffTree.getKeyCodes();

        Compressor compressor = new Compressor(testFilesPaths.get(0), "src\\test\\testFilesOutput\\test1Compressed.txt");
        compressor.compressToFile(huffmanTree, keyCodes, huffTree.getNumOfOccurrences());

        Decompressor decompressor = new Decompressor("src\\test\\testFilesOutput\\test1Compressed.txt", "src\\test\\testFilesOutput\\test1Decompressed.txt");
        decompressor.decompressFile();

        //second file
        HuffmanTree huffTree1 = new HuffmanTree(testFilesPaths.get(1));
        Node<Integer, Integer> huffmanTree1 = huffTree1.buildHuffTree();
        huffTree1.buildDictionary(huffmanTree1, "0");
        String[] keyCodes1 = huffTree1.getKeyCodes();

        Compressor compressor1 = new Compressor(testFilesPaths.get(1), "src\\test\\testFilesOutput\\test2Compressed.txt");
        compressor1.compressToFile(huffmanTree1, keyCodes1, huffTree1.getNumOfOccurrences());

        Decompressor decompressor1 = new Decompressor("src\\test\\testFilesOutput\\test2Compressed.txt", "src\\test\\testFilesOutput\\test2Decompressed.txt");
        decompressor1.decompressFile();

        String tmp = null;
        String tmp1 = null;
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader("src\\test\\testFilesOutput\\test1Decompressed.txt"));
            tmp = fileReader.readLine();

            BufferedReader fileReader1 = new BufferedReader(new FileReader("src\\test\\testFilesOutput\\test2Decompressed.txt"));
            tmp1 = fileReader1.readLine();
        } catch (IOException e) {
            System.err.println("Error when opening the file!" + e.getMessage());
        }

        String secondFileContent = "adnasjnvnbvudgeege!$@#!$%%^^%!$@##@#@#!*&*()(_)+_+)&^*";

        Assert.assertEquals(true, tmp.equals("ala ma kota") && tmp1.equals(secondFileContent));

        Path pathCom = Paths.get("src\\test\\testFilesOutput\\test1Compressed.txt");
        Path pathDecom = Paths.get("src\\test\\testFilesOutput\\test1Decompressed.txt");
        Path path1Com = Paths.get("src\\test\\testFilesOutput\\test2Compressed.txt");
        Path path1Decom = Paths.get("src\\test\\testFilesOutput\\test2Decompressed.txt");

        try {
            Files.delete(pathCom);
            Files.delete(pathDecom);

            Files.delete(path1Com);
            Files.delete(path1Decom);
            System.out.println("usunięto");
        } catch (IOException e) {
            System.out.println("Error when deleting file!" + e.getMessage());
        }
    }
}
