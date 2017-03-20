package pl.ms.scrabblesolver.application.matcher;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.ms.scrabblesolver.infrastructure.Dictionary;
import pl.ms.scrabblesolver.infrastructure.DictionaryFixture;

/*
 * Created by Marcin on 2017-03-19.
 */
public class WordMatcherTest {

    private WordMatcher wordMatcher;

    @Before
    public void before() {
        Dictionary dictionary = DictionaryFixture.build(
                "aa", "ab", "ac", "ba", "bb", "bc",
                "aaa", "aba", "aca");
        this.wordMatcher = new WordMatcherImpl(dictionary);
    }

    @Test
    public void shouldReturnEmptyListForNull() {
        Assert.assertTrue(CollectionUtils.isEmpty(wordMatcher.findMatchingWords(null)));
    }

    @Test
    public void shouldReturnEmptyListForEmpty() {
        Assert.assertTrue(CollectionUtils.isEmpty(wordMatcher.findMatchingWords("")));
    }

    @Test
    public void shouldReturnEmptyListForInvalidWord() {
        Assert.assertTrue(CollectionUtils.isEmpty(wordMatcher.findMatchingWords("illegal")));
    }

    @Test
    public void shouldReturn1WordForAA() {
        Assert.assertTrue(wordMatcher.findMatchingWords("aa").size() == 1);
    }

    @Test
    public void shouldReturn3WordsForAQm() {
        Assert.assertTrue(wordMatcher.findMatchingWords("a?").size() == 3);
    }

    @Test
    public void shouldReturn2WordsForQmB() {
        Assert.assertTrue(wordMatcher.findMatchingWords("?b").size() == 2);
    }

    @Test
    public void shouldReturn6WordsForQmQm() {
        Assert.assertTrue(wordMatcher.findMatchingWords("??").size() == 6);
    }

    @Test
    public void shouldReturnEmptyListQmQmQmQm() {
        Assert.assertTrue(CollectionUtils.isEmpty(wordMatcher.findMatchingWords("????")));
    }

}
