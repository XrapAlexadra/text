package edu.epam.text.service.impl;

import edu.epam.text.composite.TextComponent;
import edu.epam.text.composite.impl.TextComposite;
import edu.epam.text.composite.impl.Symbol;
import edu.epam.text.handler.impl.TextParser;
import edu.epam.text.reader.TextReader;
import edu.epam.text.service.TextService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TextServiceImpl implements TextService {

    private static final Logger logger = LogManager.getLogger(TextServiceImpl.class);

    private static final String VOWEL_MATCHER = "[яЯиИюЮэЭоОаАыуУеЕёЁaAeEyYuUiIoO]";

    private static final TextServiceImpl instance = new TextServiceImpl();

    private TextServiceImpl() {
    }

    public static TextServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Map<String, Integer> findAllRepeatWords(TextComponent text) {
        Map<String, Integer> result = text.stream()
                .flatMap(TextComponent::stream)
                .flatMap(TextComponent::stream)
                .filter(x -> !(x instanceof Symbol))
                .map(TextComponent::toString)
                .map(String::toLowerCase)
                .collect(Collectors.toMap(
                        Function.identity(),
                        i -> 1,
                        Integer::sum
                ));
        result.values().removeIf(x -> x == 1);
        logger.info("Find all repeat words in text: {}", text);
        return result;
    }

    @Override
    public void deleteSentencesWithWordNumberLess(TextComponent text, int minWordNumber) {
        for (int i = 0; i < text.size(); i++) {
            TextComponent paragraph = text.getChild(i);
            List<TextComponent> result = paragraph.stream()
                    .filter(x -> (x.textComponentCount() < minWordNumber))
                    .collect(Collectors.toList());
            result.forEach(paragraph::remove);
        }
        logger.info("Delete sentences with words number less than {} from text: {}", minWordNumber, text);
    }

    @Override
    public TextComponent sortParagraphs(TextComponent text) {
        Comparator<TextComponent> comparator = Comparator.comparingInt(TextComponent::size);
        TextComponent result = new TextComposite(text.stream()
                .sorted(comparator)
                .collect(Collectors.toList()));
        logger.info("Sort paragraphs in text: {}", text);
        return result;
    }

    @Override
    public List<TextComponent> findAllSentenceWithMaxWord(TextComponent text) {
        Comparator<TextComponent> comparator = Comparator.comparingInt(TextComponent::findMaxChildSize);
        Optional<TextComponent> sentenceWithMaxWord = text.stream()
                .flatMap(TextComponent::stream)
                .max(comparator);
        int maxChildSize = sentenceWithMaxWord.map(TextComponent::findMaxChildSize).orElse(0);
        List<TextComponent> results = text.stream()
                .flatMap(TextComponent::stream)
                .filter(x -> x.findMaxChildSize() == maxChildSize)
                .collect(Collectors.toList());
        logger.info("Find all sentences with max word in text: {}", text);
        return results;
    }

    @Override
    public long countVowel(TextComponent sentence) {
        long result = sentence.stream()
                .filter(x -> !(x instanceof Symbol))
                .flatMap(TextComponent::stream)
                .map(TextComponent::toString)
                .filter(x -> x.matches(VOWEL_MATCHER))
                .count();
        System.out.println(sentence);
        System.out.println(result);
        return result;
    }

    @Override
    public long countConsonant(TextComponent sentence) {
        long vowelCount = countVowel(sentence);
        long allLetterCount = sentence.stream()
                .filter(x -> !(x instanceof Symbol))
                .flatMap(TextComponent::stream)
                .count();
        long result = allLetterCount - vowelCount;
        return result;
    }

    public static void main(String[] args) {
        TextReader textReader = TextReader.getInstance();
        TextParser textParser = new TextParser();
        TextComponent  text = textParser.handleRequest(textReader.readText("data/text.txt"));
        text.stream()
                .flatMap(TextComponent::stream)
                .map(instance::countVowel)
                .forEach(System.out::println);

    }
}