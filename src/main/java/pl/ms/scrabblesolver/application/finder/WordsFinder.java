package pl.ms.scrabblesolver.application.finder;

import pl.ms.scrabblesolver.domain.Word;

import java.util.List;

/*
 * Created by Marcin on 2017-03-19.
 */
public interface WordsFinder {

    /**
     * all words that contains given characters, the '?' replaces one character
     */
    List<Word> findWords(String characters);
}
