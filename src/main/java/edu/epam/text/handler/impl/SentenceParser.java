package edu.epam.text.handler.impl;

import edu.epam.text.composite.TextComponent;
import edu.epam.text.composite.impl.TextComposite;
import edu.epam.text.handler.AbstractHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractHandler {

    private static final Logger logger = LogManager.getLogger(SentenceParser.class);

    private static final String PUNCTUATION_MATCHER = "[\\p{P}| ]";

    public SentenceParser() {
        super(new WordParser());
    }

    @Override
    public TextComponent handleRequest(String data) {
        TextComponent sentenceComponent = new TextComposite();
        Pattern pattern = Pattern.compile(PUNCTUATION_MATCHER);
        Matcher matcher = pattern.matcher(data);
        int indexStart = 0;
        while (matcher.find()) {
            if (indexStart != matcher.start()) {
                String word = data.substring(indexStart, matcher.start());
                TextComponent wordComponent = chain(word);
                sentenceComponent.add(wordComponent);
            }
            String symbol = data.substring(matcher.start(), matcher.end());
            TextComponent symbolComponent = chain(symbol);
            sentenceComponent.add(symbolComponent);
            indexStart = matcher.end();
        }
        if (indexStart != data.length()) {
            String word = data.substring(indexStart);
            TextComponent wordComponent = chain(word);
            sentenceComponent.add(wordComponent);
        }
        logger.info("Parse sentence: {}", data);
        return sentenceComponent;
    }
}
