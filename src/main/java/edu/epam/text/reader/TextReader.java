package edu.epam.text.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.stream.Collectors;

public class TextReader {

    private static final Logger logger = LogManager.getLogger(TextReader.class);

    private static final String DEFAULT_FILE_NAME = "data/text.txt";

    private static TextReader instance = new TextReader();

    private TextReader() {
    }

    public static TextReader getInstance(){
        return instance;
    }

    public String readText(){
        String result = readText(DEFAULT_FILE_NAME);
        return result;
    }

    public String readText(String fileName) {
        File file = new File(fileName);
        String text = "";
        try (FileReader fileReader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            text = bufferedReader.lines().collect(Collectors.joining());
            logger.info(text);
        } catch (IOException e) {
            logger.error(e);
        }
        logger.info("Read from file: {} text: {}", fileName, text);
        return text;
    }
}
