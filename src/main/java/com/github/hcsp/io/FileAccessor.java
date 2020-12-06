package com.github.hcsp.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileAccessor {
    public static List<String> readFile1(File file) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        InputStream inputStream = new FileInputStream(file);
        int i = inputStream.read();
        while (i > -1) {
            stringBuffer.append((char) i);
            i = inputStream.read();
        }
        String string = new String(stringBuffer);
        return Arrays.asList(string.split("\n"));
    }

    public static List<String> readFile2(File file) throws IOException {
        List<String> acc = new ArrayList();
        InputStream in = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String sum = "";
        String l;
        while ((l = br.readLine()) != null) {
            acc.add(l);
        }
        return acc;
    }

    public static List<String> readFile3(File file) throws IOException {
        byte[] contentByte = Files.readAllBytes(Paths.get(file.getPath()));
        String content = new String(contentByte);
        return Arrays.asList(content.split("\n"));
    }

    public static void writeLinesToFile1(List<String> lines, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                byte[] b = line.getBytes();
                os.write(b);
                if (i != lines.size() - 1) {
                    String NEW_LINE_OPERATE = "\n";
                    os.write(NEW_LINE_OPERATE.getBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeLinesToFile2(List<String> lines, File file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                writer.write(line);
                if (i != lines.size() - 1) {
                    String NEW_LINE_OPERATE = "\n";
                    writer.write(NEW_LINE_OPERATE);
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeLinesToFile3(List<String> lines, File file) {
        try {
            Files.write(file.toPath(), getContentString(lines).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getContentString(List<String> lines) {
        String[] contentArray = new String[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            contentArray[i] = lines.get(i);
        }
        return String.join("\n", contentArray);

    }

    public static void main(String[] args) throws IOException {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        File testFile = new File(projectDir, "target/test.txt");
        List<String> lines = Arrays.asList("AAA", "BBB", "CCC");
        writeLinesToFile1(lines, testFile);
        writeLinesToFile2(lines, testFile);
        writeLinesToFile3(lines, testFile);

        System.out.println(readFile1(testFile));
        System.out.println(readFile2(testFile));
        System.out.println(readFile3(testFile));
    }
}
