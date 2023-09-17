package org.p;

import org.apache.tools.ant.types.Commandline;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> allArgs = new ArrayList<>();
        allArgs.add("java");
        allArgs.add("-jar");
        allArgs.add(System.getProperty("user.home") + "\\DreamBot\\BotData\\client.jar");
        

        try {
            String source = new File(Main.class.getProtectionDomain().getCodeSource().getLocation()
                    .toURI().getPath()).getAbsoluteFile().toString();

            String fileName = source.substring(source.lastIndexOf("\\") + 1);

            source = source.replace(fileName, "db_config.txt");
            BufferedReader Buff = new BufferedReader(new FileReader(source));
            String data = Buff.readLine();
            if (!data.isEmpty()) {
                String[] finalData = Commandline.translateCommandline(data);
                allArgs.addAll(Arrays.asList(finalData));
            }

            ProcessBuilder processBuilder = new ProcessBuilder(allArgs);
            Process process = processBuilder.start();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}