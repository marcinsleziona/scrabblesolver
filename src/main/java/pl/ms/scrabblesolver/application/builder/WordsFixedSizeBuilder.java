package pl.ms.scrabblesolver.application.builder;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pl.ms.scrabblesolver.domain.Word;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * Created by Marcin on 2017-03-19.
 */
@Service
public class WordsFixedSizeBuilder {

    /**
     * build word from the input, '?' is replaced by the all possible characters
     */
    public Set<String> build(Collection<Character> possible, String input) {
        Set<String> words = new HashSet<>();
        if (StringUtils.isBlank(input)) {
            return words;
        }

        int length = input.length();
        words.add(input);

        for (int idx = 0; idx < length; idx++) {
            Set<String> words_ = new HashSet<>();
            for (String word_ : words) {
                if (word_.charAt(idx) == '?') {
                    for (Character c : possible) {
                        char[] chartab = word_.toCharArray();
                        chartab[idx] = c;
                        words_.add(String.valueOf(chartab));
                    }
                } else {
                    words_.add(word_);
                }
            }
            words = words_;
        }
        return words.stream().map(s -> Word.of(s).getCharactersSorted()).collect(Collectors.toSet());
    }
}