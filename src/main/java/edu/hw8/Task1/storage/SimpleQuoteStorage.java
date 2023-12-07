package edu.hw8.Task1.storage;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SimpleQuoteStorage implements QuoteStorage {
    private final List<String> quotes;

    public SimpleQuoteStorage() {
        quotes = new CopyOnWriteArrayList<>() {
            {
                add("Не переходи на личности там, где их нет");
                add("Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами");
                add("А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... "
                    + "Ты просто бог идиотизма.");
                add("Чем ниже интеллект, тем громче оскорбления");
            }
        };
    }

    @Override
    public void addQuote(String quote) {
        quotes.add(quote);
    }

    @Override
    public String findFirstByKeyWord(String keyWord) {
        return quotes.stream()
            .filter(
                quote -> quote.toLowerCase().contains(keyWord.toLowerCase())
            )
            .findFirst()
            .orElse("");
    }

    @Override
    public List<String> getAllByKeyWord(String keyWord) {
        return quotes.stream()
            .filter(
                quote -> quote.toLowerCase().contains(keyWord.toLowerCase())
            ).toList();
    }
}
