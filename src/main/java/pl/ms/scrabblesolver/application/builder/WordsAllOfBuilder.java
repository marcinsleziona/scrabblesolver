package pl.ms.scrabblesolver.application.builder;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pl.ms.scrabblesolver.domain.Word;

import java.util.HashSet;
import java.util.Set;

/*
 * Created by Marcin on 2017-03-19.
 */
@Service
public class WordsAllOfBuilder {

    /**
     * build all possible words >= 2characters from the inputCharacters, '?' is replaced by the all possible characters
     */
    public Set<String> build(String inputCharacters) {
        Set<String> ret = new HashSet<>();
        if (StringUtils.isBlank(inputCharacters)) {
            return ret;
        }
        findCombinations(ret, "", inputCharacters);
        return ret;
    }

    private void findCombinations(Set<String> ret, String prefix, String str) {
        int n = str.length();
        if (prefix.length() >= 2) {
            ret.add(Word.of(prefix).getCharactersSorted());
        }
        if (n != 0) {
            for (int i = 0; i < n; i++)
                findCombinations(ret, prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
        }
    }
}