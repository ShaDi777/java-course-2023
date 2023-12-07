package edu.hw8.Task1.storage;

import java.util.List;

public interface QuoteStorage {
    void addQuote(String quote);

    String findFirstByKeyWord(String keyWord);

    List<String> getAllByKeyWord(String keyWord);
}
