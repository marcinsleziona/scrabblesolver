package pl.ms.scrabblesolver.infrastructure;

import gnu.trove.map.hash.THashMap;
import gnu.trove.set.hash.THashSet;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.ms.scrabblesolver.domain.Dictionary;
import pl.ms.scrabblesolver.domain.Word;

import java.util.*;

/*
 * Created by Marcin on 2017-03-22.
 */
public class DictionaryImpl implements Dictionary {

    private final static Logger LOG = LoggerFactory.getLogger(DictionaryCompleteImpl.class);

    /**
     * builder - 1st character
     * value - map of
     */
    private Map<Character, Map<Integer, Set<Word>>> words = new THashMap<>();

    // Create a builder that is the word's letters sorted alphabetically (and forced to one case)
    // Add the word to the list of wordsfinder accessed by the hash builder in H
    private Map<Integer, Map<Word, List<Word>>> wordKeys = new THashMap<>();

    private Set<Character> characters = new THashSet<>();


    public void add(String text) {
        if (StringUtils.isBlank(text)) {
            return;
        }

        Word word = Word.of(text);
        Character firstTextCharacter = text.charAt(0);
        Integer textLength = text.length();

        // add to words map -> used for simple search
        Map<Integer, Set<Word>> lwords = words.get(firstTextCharacter);
        if (lwords == null) {
            lwords = new THashMap<>();
        }
        Set<Word> lcwords = lwords.get(textLength);
        if (lcwords == null) {
            lcwords = new THashSet<>();
        }
        lcwords.add(word);
        lwords.put(textLength, lcwords);
        words.put(firstTextCharacter, lwords);

        // add to words map -> used for anagrams
        Map<Word, List<Word>> lwkwords = wordKeys.get(textLength);
        if (lwkwords == null) {
            lwkwords = new THashMap<>();
        }
        String key = word.getCharactersSorted();
        List<Word> klwkwords = lwkwords.get(Word.of(key));
        if (klwkwords == null) {
            klwkwords = new ArrayList<>();
        }
        klwkwords.add(word);
        lwkwords.put(Word.of(key), klwkwords);
        wordKeys.put(textLength, lwkwords);

        // add all characters that are used
        for (char ch : word.getText().toCharArray()) {
            characters.add(ch);
        }
    }

    @Override
    public Set<Word> getByPrefix(String prefix) {
        Set<Word> ret = new THashSet<>();
        if (StringUtils.isBlank(prefix)) {
            return ret;
        }

        Map<Integer, Set<Word>> lwords = words.get(prefix.charAt(0));
        if (lwords == null) {
            return ret;
        }
        for (Map.Entry<Integer, Set<Word>> entry : lwords.entrySet()) {
            if (entry.getKey() < prefix.length()) {
                continue;
            }
            entry.getValue().stream().filter(e -> e.startsWith(prefix)).forEachOrdered(s -> ret.add(s));
        }
        return ret;
    }

    @Override
    public Set<Word> getByCharacterAndLength(Character c, Integer length) {
        Set<Word> ret = new THashSet<>();
        if (c == null || length == null || length <= 0) {
            return ret;
        }

        Set<Word> lengthliners = new THashSet<>();
        if (c == '?') {
            for (Map<Integer, Set<Word>> entry : words.values()) {
                Set<Word> hs = entry.get(length);
                if (hs != null) {
                    lengthliners.addAll(hs);
                }
            }
        } else {
            Map<Integer, Set<Word>> lwords = words.get(c);
            if (lwords == null) {
                return ret;
            }
            Set<Word> hs = lwords.get(length);
            if (hs != null) {
                lengthliners.addAll(hs);
            }
        }
        //return lengthliners.parallelStream().map(Word::of).collect(Collectors.toSet());
        return lengthliners;
    }

    @Override
    public Set<Word> getByKey(String key) {
        Set<Word> res = new THashSet<>();
        Map<Word, List<Word>> lkwords = wordKeys.get(key.length());
        if (lkwords == null) {
            return res;
        }
        List<Word> klkwords = lkwords.get(Word.of(key));
        if (klkwords == null) {
            return res;
        }

        return new THashSet<>(klkwords);
    }

    public Collection<Character> getCharacters() {
        return Collections.unmodifiableCollection(characters);
    }
}
