package hust.soict.dsai.garbage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NoGarbage {
    public static void main(String[] args) {
        try {
            // Read a large file into a byte array
            byte[] inputBytes = Files.readAllBytes(Paths.get("largeTextFile.txt"));
            String inputString = new String(inputBytes);

            // Use StringBuilder to avoid garbage creation
            long start = System.currentTimeMillis();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < inputString.length(); i++) {
                sb.append(inputString.charAt(i));
            }
            String result = sb.toString();
            System.out.println("Time with StringBuilder: " + (System.currentTimeMillis() - start) + " ms");

        } catch (IOException e) {
            System.err.println("File not found!");
            e.printStackTrace();
        }
    }
}
