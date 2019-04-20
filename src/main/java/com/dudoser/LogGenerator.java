package com.dudoser;

import java.io.File;
import java.io.FileReader;
import java.io.Serializable;

public class LogGenerator  {

    public static void main(String[] args) throws Exception {
        File basedir = new File("/home/dudoser/IdeaProjects/exrates-inout-service/src/main/java/com/exrates/inout/");
        process(basedir);
    }

    private static void process(File basedir) throws Exception {
        for (File file : basedir.listFiles()) {

            if(file.isFile()){
                processFile(file);
            } else {
                process(file);
            }


        }
    }

    private static void processFile(File file) throws Exception {
        String classText = readFile(file);
        if(classText.contains("enum")) return;

        if (classText.contains("@Log4j")){
            String newClassText = classText.substring(0, classText.indexOf("{"));
            newClassText += "\n" + "private static final Logger logger = LogManager.getLogger(tpcname);";
            String topicName = getTopicName(classText);
            System.out.println(topicName);
            newClassText.replace("tpcname", topicName);
        }
    }

    private static String getTopicName(String classText) {
        String topicInAnnotation = "@Log4j2(topic = ";
        if(classText.contains(topicInAnnotation)) {
            return extractTopicNameFromAnnotation(classText, topicInAnnotation);
        } else {
            return extractTopicNameAsClassName(classText);
        }
    }

    private static String extractTopicNameAsClassName(String classText) {
        try {
            String aClass = "class";
            String from = classText.substring(classText.indexOf(aClass) + aClass.length());

            if(classText.contains("SSMConfiguration")){
                System.out.println(2);
            }
            int endIndex = from.indexOf("{");
            if (!classText.contains("class SSMConfiguration") && classText.contains("extends")){
                endIndex = classText.indexOf("extends");
            } else
            if(!classText.contains("class SSMConfiguration") && classText.contains("implements")){
                endIndex = classText.indexOf("implements");
            }

            return from.substring(0 , endIndex).trim() + ".class";
        } catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    private static String extractTopicNameFromAnnotation(String classText, String topicInAnnotation) {
        String temp = classText.substring(classText.indexOf(topicInAnnotation) + topicInAnnotation.length());
        return temp.substring(0, temp.indexOf(")"));
    }

    private static String readFile(File file) throws Exception{
        FileReader reader = new FileReader(file);
        StringBuilder builder = new StringBuilder();
        int c;
        do{
            c = reader.read();
            builder.append((char)c);
        } while (c != -1);
        return builder.toString();
    }
}
