package pl.edu.pw.ee.aisd2023zlab5;

import pl.edu.pw.ee.aisd2023zlab5.HuffmanTree.HuffmanTree;
import pl.edu.pw.ee.aisd2023zlab5.HuffmanTree.Node;

public class Main {
    public static void main(String[] args) {
        if(args.length == 0) {
            printHelp();
            return;
        }

        String mode = args[0];
        String inputFilePath = args[1];

        if(mode.equals("compress") || mode.equals("Compress")) {
            String outputCompFilePath = args[2];

            HuffmanTree huffTree = new HuffmanTree(inputFilePath);
            Node<Integer, Integer> huffmanTree = huffTree.buildHuffTree();

            huffTree.buildDictionary(huffmanTree, "0");
            String[] keyCodes = huffTree.getKeyCodes();

            Compressor compressor = new Compressor(inputFilePath, outputCompFilePath);
            compressor.compressToFile(huffmanTree, keyCodes, huffTree.getNumOfOccurrences());

        } else if (mode.equals("decompress") || mode.equals("Decompress")) {
            String outputDecompFilePath = args[2];

            Decompressor decompressor = new Decompressor(inputFilePath, outputDecompFilePath);
            decompressor.decompressFile();
        } else {
            printHelp();
        }
    }

    public static void printHelp(){
        System.out.println("-----------------------------HELP---------------------------------");
        System.out.println("    Please run the program using arguments as described below:");
        System.out.println("\n    mode srcFilepath dstFilePath\n");
        System.out.println("    Use compress mode to compress");
        System.out.println("    Use decompress mode to decompress\n");
        System.out.println("    In given dstFilePath please specify \n" +
                           "    the new file name, you want create");
        System.out.println("-------------------------------------------------------------------");
    }
}
