package pl.ms.scrabblesolver.application.builder;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pl.ms.scrabblesolver.domain.Word;

import java.util.*;
import java.util.stream.Collectors;

/*
 * Created by Marcin on 2017-03-19.
 */
@Service
public class WordsAllOfBuilder {

    /**
     * build all possible words >= 2characters from the inputCharacters
     */
    public List<Word> build(String inputCharacters) {
        if (StringUtils.isBlank(inputCharacters)) {
            return new ArrayList<>();
        }
        Set<String> res = new HashSet<>();
        findCombinations(res, "", inputCharacters);

        List<Word> lres = res.parallelStream().map(Word::of).collect(Collectors.toList());
        Collections.sort(lres);
        return lres;
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