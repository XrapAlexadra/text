package edu.epam.text.handler.impl;

import edu.epam.text.composite.TextComponent;
import edu.epam.text.composite.impl.Symbol;
import edu.epam.text.handler.AbstractHandler;

public class SymbolParser extends AbstractHandler{

    public SymbolParser() {
    }

    @Override
    protected TextComponent chain(String data) {
        return null;
    }

    @Override
    public TextComponent handleRequest(String data) {
        TextComponent symbol = new Symbol(data.charAt(0));
        return symbol;
    }
}
