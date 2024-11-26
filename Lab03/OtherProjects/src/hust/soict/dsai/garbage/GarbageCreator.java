package hust.soict.dsai.garbage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GarbageCreator {
    public static void main(String[] args) {
        try {
            // Read a large file into a byte array
            byte[] inputBytes = Files.readAllBytes(Paths.get("largeTextFile.txt"));
            String inputString = new String(inputBytes);

            // Create garbage using String concatenation
            long start = System.currentTimeMillis();
            String s = "";
            for (int i = 0; i < inputString.length(); i++) {
                s += inputString.charAt(i);
            }
            System.out.println("Time with String (+ operator): " + (System.currentTimeMillis() - start) + " ms");

        } catch (IOException e) {
            System.err.println("File not found!");
            e.printStackTrace();
        }
    }
}
