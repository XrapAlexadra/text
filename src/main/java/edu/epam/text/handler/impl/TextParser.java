package edu.epam.text.handler.impl;

import edu.epam.text.composite.TextComponent;
import edu.epam.text.composite.impl.TextComposite;
import edu.epam.text.handler.AbstractHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class TextParser extends AbstractHandler {

    private static final Logger logger = LogManager.getLogger(TextParser.class);

    private static final String PARAGRAPH_DELIMITER = " {4}|\\t";

    public TextParser() {
        super(new ParagraphParser());
    }

    @Override
    public TextComponent handleRequest(String data) {
        TextComponent textComponent = new TextComposite();
        String[] paragraphs = data.trim().split(PARAGRAPH_DELIMITER);
        Arrays.stream(paragraphs)
                .map(x -> x + " ")
                .map(this::chain)
                .forEach(textComponent::add);
        logger.info("Parse text: {}", data);
        return textComponent;
    }
}
