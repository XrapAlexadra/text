package edu.epam.text.handler.impl;

import edu.epam.text.composite.TextComponent;
import edu.epam.text.composite.impl.TextComposite;
import edu.epam.text.handler.AbstractHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends AbstractHandler {

    private static final Logger logger = LogManager.getLogger(ParagraphParser.class);

    private static final String SENTENCE_DELIMITER = "[!?.]+ +";

    public ParagraphParser() {
        super(new SentenceParser());
    }

    @Override
    public TextComponent handleRequest(String data) {
        TextComponent paragraphComponent = new TextComposite();
        List<String> sentences = new ArrayList<>();
        Pattern pattern = Pattern.compile(SENTENCE_DELIMITER);
        Matcher matcher = pattern.matcher(data);
        String sentence;
        int startIndex = 0;
        while (matcher.find()){
            int endIndex = matcher.end();
            sentence = data.substring(startIndex, endIndex);
            sentences.add(sentence);
            startIndex = endIndex;
        }
        if(startIndex != data.length()) {
            sentence = data.substring(startIndex);
            sentences.add(sentence);
        }
        sentences.stream()
                .map(this::chain)
                .forEach(paragraphComponent::add);
        logger.info("Parse paragraph: {}", data);
        return paragraphComponent;
    }
}
