package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;


public class FileReader {

    public Profile getDataFromFile(File file) {
        Profile profile = new Profile();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(file.getPath()), Charset.defaultCharset())) {
            String[] params = new String[4];
            int lastParamIndex = 0;
            for (int i = 0; i < 4; i++) {
                String line = reader.readLine();
                for (int j = 0; j < line.length(); j++) {
                    if (line.charAt(j) == ' ') params[lastParamIndex++] = line.substring(j + 1);
                }
            }
            profile.setName(params[0]);
            profile.setAge(Integer.valueOf(params[1]));
            profile.setEmail(params[2]);
            profile.setPhone(Long.valueOf(params[3]));

        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }

        return profile;
    }

    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        System.out.println(fileReader.getDataFromFile(new File("src/main/resources/Profile.txt")).toString());
    }
}
