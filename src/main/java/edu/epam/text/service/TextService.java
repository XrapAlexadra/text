package edu.epam.text.service;

import edu.epam.text.composite.TextComponent;

import java.util.List;
import java.util.Map;

public interface TextService<pu> {

    Map<String, Integer> findAllRepeatWords(TextComponent text);

    void deleteSentencesWithWordNumberLess(TextComponent text, int minWordNumber);

    TextComponent sortParagraphs(TextComponent text);

    List<TextComponent> findAllSentenceWithMaxWord(TextComponent text);

    long countVowel(TextComponent sentence);

    long countConsonant(TextComponent sentence);
}
