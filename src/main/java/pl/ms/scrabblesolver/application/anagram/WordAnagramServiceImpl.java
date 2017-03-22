package pl.ms.scrabblesolver.application.anagram;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ms.scrabblesolver.application.builder.WordsFixedSizeBuilder;
import pl.ms.scrabblesolver.domain.Dictionary;
import pl.ms.scrabblesolver.domain.Word;

import java.util.*;

/*
 * Created by Marcin on 2017-03-19.
 */
@Service
public class WordAnagramServiceImpl implements WordAnagramService {

    private Dictionary dictionary;
    private WordsFixedSizeBuilder wordsFixedSizeBuilder;

    @Autowired
    public WordAnagramServiceImpl(Dictionary dictionary, WordsFixedSizeBuilder wordsFixedSizeBuilder) {
        this.dictionary = dictionary;
        this.wordsFixedSizeBuilder = wordsFixedSizeBuilder;
    }

    @Override
    public List<Word> findAnagrams(String inputCharacters) {
        if (StringUtils.isBlank(inputCharacters)) {
            return new ArrayList<>();
        }
        Set<Word> res = new HashSet<>();
        Set<String> keys = wordsFixedSizeBuilder.build(dictionary.getCharacters(), inputCharacters);

        keys.parallelStream().forEach(p -> dictionary.getByKey(p).forEach(res::add));
        List<Word> lres = new ArrayList<>(res);
        Collections.sort(lres);
        return lres;
    }
}
