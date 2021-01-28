package edu.epam.text.composite.impl;

import edu.epam.text.composite.TextComponent;
import java.util.stream.Stream;

public class Symbol implements TextComponent {

    private char symbol;

    public Symbol(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public void add(TextComponent textComponent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(TextComponent textComponent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public TextComponent getChild(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public int textComponentCount() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int findMaxChildSize() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Stream<TextComponent> stream() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
