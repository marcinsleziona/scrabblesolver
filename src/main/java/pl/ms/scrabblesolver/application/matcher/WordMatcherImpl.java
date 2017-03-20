package pl.ms.scrabblesolver.application.matcher;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Service;
import pl.ms.scrabblesolver.domain.Word;
import pl.ms.scrabblesolver.infrastructure.Dictionary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * Created by Marcin on 2017-03-19.
 */
@Service
public class WordMatcherImpl implements WordMatcher {

    private Dictionary dictionary;

    @Autowired
    public WordMatcherImpl(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public List<Word> findMatchingWords(String pattern) {
        if (StringUtils.isBlank(pattern)) {
            return new ArrayList<>();
        }
        Character c = pattern.charAt(0);
        Set<Word> lengthliners = dictionary.getByCharacterAndLength(c, pattern.length());

        Set<Word> res = lengthliners.stream().filter(p -> checkWord(p, pattern)).collect(Collectors.toSet());
        List<Word> lres = new ArrayList<>(res);
        Collections.sort(lres);
        return lres;
    }

    private boolean checkWord(Word word, String tofind) {
        for (int idx = 0; idx < tofind.length(); idx++) {
            if (tofind.charAt(idx) == '?') {
                continue;
            }
            if (tofind.charAt(idx) == word.charAt(idx)) {
                continue;
            }
            return false;
        }
        return true;
    }
}
