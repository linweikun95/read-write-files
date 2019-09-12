package com.github.hcsp.io;

import org.apache.commons.io.FileUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileAccessor {
    public static List<String> readFile1(File file) throws IOException {
        return FileUtils.readLines(file, "UTF-8");
    }

    public static List<String> readFile2(File file) {

        ArrayList<String> infoValue = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line= bufferedReader.readLine())!=null){
                infoValue.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return infoValue;
    }

    public static List<String> readFile3(File file) throws IOException {
        Charset ENCODING = StandardCharsets.UTF_8;
        Path path = file.toPath();
        return Files.readAllLines(path, ENCODING);
    }

    public static void writeLinesToFile1(List<String> lines, File file) throws IOException {
        FileUtils.writeLines(file, lines, true);

    }

    public static void writeLinesToFile2(List<String> lines, File file) {


        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))){
            for (String line : lines) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public static void writeLinesToFile3(List<String> lines, File file) throws IOException {
        Path path = file.toPath();
        Charset ENCODING = StandardCharsets.UTF_8;
        Files.write(path, lines, ENCODING);
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
