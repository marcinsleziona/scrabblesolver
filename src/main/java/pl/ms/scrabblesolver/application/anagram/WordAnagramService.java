package pl.ms.scrabblesolver.application.anagram;

import pl.ms.scrabblesolver.domain.Word;

import java.util.List;

/*
 * Created by Marcin on 2017-03-19.
 */
public interface WordAnagramService {

    /**
     * all words that can be build with given characters, the '?' replaces one character
     */
    List<Word> findAnagrams(String inputCharacters);

}
