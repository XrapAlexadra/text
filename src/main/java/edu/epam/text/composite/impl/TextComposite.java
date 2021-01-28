package edu.epam.text.composite.impl;

import edu.epam.text.composite.TextComponent;
import java.util.*;
import java.util.stream.Stream;

public class TextComposite implements TextComponent {

    private List<TextComponent> textComponents = new ArrayList<>();

    public TextComposite() {
    }

    public TextComposite(List<TextComponent> textComponents) {
        this.textComponents = textComponents;
    }

    @Override
    public void add(TextComponent textComponent) {
        textComponents.add(textComponent);
    }

    @Override
    public void remove(TextComponent textComponent) {
        textComponents.remove(textComponent);
    }

    @Override
    public TextComponent getChild(int index) {
        return textComponents.get(index);
    }

    @Override
    public int size() {
        return textComponents.size();
    }

    @Override
    public int textComponentCount() {
        return (int) textComponents.stream().filter(x -> !(x instanceof Symbol)).count();
    }

    @Override
    public int findMaxChildSize() {
        OptionalInt result = textComponents.stream()
                .mapToInt(TextComponent::size)
                .max();
        return result.orElse(0);
    }

    @Override
    public Stream<TextComponent> stream() {
        return textComponents.stream();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        textComponents.forEach(sb::append);
        return sb.toString();
    }
}
