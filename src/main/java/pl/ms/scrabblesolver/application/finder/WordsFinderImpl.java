package pl.ms.scrabblesolver.application.finder;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ms.scrabblesolver.application.builder.WordsAllOfBuilder;
import pl.ms.scrabblesolver.application.builder.WordsFixedSizeBuilder;
import pl.ms.scrabblesolver.domain.Word;
import pl.ms.scrabblesolver.infrastructure.Dictionary;

import java.util.*;

/*
 * Created by Marcin on 2017-03-19.
 */
@Service
public class WordsFinderImpl implements WordsFinder {

    private Dictionary dictionary;

    private WordsFixedSizeBuilder wordsFixedSizeBuilder;
    private WordsAllOfBuilder wordsAllOfBuilder;

    @Autowired
    public WordsFinderImpl(Dictionary dictionary,
                           WordsFixedSizeBuilder wordsFixedSizeBuilder,
                           WordsAllOfBuilder wordsAllOfBuilder) {
        this.dictionary = dictionary;
        this.wordsFixedSizeBuilder = wordsFixedSizeBuilder;
        this.wordsAllOfBuilder = wordsAllOfBuilder;
    }

    @Override
    public List<Word> findWords(String inputCharacters) {
        if (StringUtils.isBlank(inputCharacters)) {
            return new ArrayList<>();
        }

        Set<Word> res = new HashSet<>();
        Set<String> keys = new HashSet<>();
        wordsAllOfBuilder.build(inputCharacters)
                .parallelStream()
                .forEach(p -> wordsFixedSizeBuilder.build(dictionary.getCharacters(), p.getText()).forEach(keys::add));

        keys.parallelStream().forEach(p -> dictionary.getByKey(p).stream().forEachOrdered(res::add));
        List<Word> lres = new ArrayList<>(res);
        Collections.sort(lres);
        return lres;
    }
}
