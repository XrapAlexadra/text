package edu.epam.text.composite;


import java.util.stream.Stream;

public interface TextComponent {

    void add(TextComponent textComponent);

    void remove(TextComponent textComponent);

    TextComponent getChild(int index);

    int size();

    int textComponentCount();

    int findMaxChildSize();

    Stream<TextComponent> stream();
}
