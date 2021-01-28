package edu.epam.text.handler.impl;

import edu.epam.text.composite.TextComponent;
import edu.epam.text.composite.impl.TextComposite;
import edu.epam.text.handler.AbstractHandler;

import java.util.Arrays;

public class WordParser extends AbstractHandler {

    private static final String SYMBOL_DELIMITER = "";
    private static final String PUNCTUATION_MATCHER = "[\\p{P}| ]";

    public WordParser() {
        super(new SymbolParser());
    }

    @Override
    public TextComponent handleRequest(String data) {
        TextComponent wordComponent;
        if (data.matches(PUNCTUATION_MATCHER)) {
            wordComponent = chain(data);
        } else {
            wordComponent = new TextComposite();
            String[] symbols = data.split(SYMBOL_DELIMITER);
            Arrays.stream(symbols)
                    .map(this::chain)
                    .forEach(wordComponent::add);
        }
        return wordComponent;
    }
}
