package pl.ms.scrabblesolver.application.finder;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.ms.scrabblesolver.application.builder.WordsAllOfBuilder;
import pl.ms.scrabblesolver.application.builder.WordsFixedSizeBuilder;
import pl.ms.scrabblesolver.infrastructure.DictionaryCompleteImpl;
import pl.ms.scrabblesolver.infrastructure.DictionaryMockImpl;

/*
 * Created by Marcin on 2017-03-19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WordFinderTest {

    @Autowired
    private WordsFinder wordsFinder;
    @Autowired
    private WordsFixedSizeBuilder wordsFixedSizeBuilder;
    @Autowired
    private WordsAllOfBuilder wordsAllOfBuilder;

//    @Before
//    public void before() {
//        DictionaryCompleteImpl dictionary = DictionaryMockImpl.build(
//                "aa", "ab", "ac", "ba", "bb", "bc",
//                "aaa", "aba", "aca");
//        this.wordsFinder = new WordsFinderImpl(dictionary, wordsFixedSizeBuilder, wordsAllOfBuilder);
//    }

    @Test
    public void shouldReturnEmptyListForNull() {
        Assert.assertTrue(CollectionUtils.isEmpty(wordsFinder.findWords(null)));
    }

    @Test
    public void shouldReturnEmptyListForEmpty() {
        Assert.assertTrue(CollectionUtils.isEmpty(wordsFinder.findWords("")));
    }

    @Test
    public void shouldReturnEmptyListForInvalidCharacter() {
        Assert.assertTrue(CollectionUtils.isEmpty(wordsFinder.findWords("z")));
    }

//    @Test
//    public void shouldReturn2WordsListForAAAA() {
//        List<Word> list = wordsFinder.findWords("aaaa");
//        Arrays.toString(list.toArray());
//
//        Assert.assertTrue(wordsFinder.findWords("aaaa").size() == 2);
//    }

    @Test
    public void shouldReturn1WordForAA() {
        Assert.assertTrue(wordsFinder.findWords("aa").size() == 1);
    }

    @Test
    public void shouldReturn4WordsForAQm() {
        Assert.assertTrue(wordsFinder.findWords("a?").size() == 4);
    }

    @Test
    public void shouldReturn4WordsForQmB() {
        Assert.assertTrue(wordsFinder.findWords("?b").size() == 4);
    }

    @Test
    public void shouldReturn2WordsForQmC() {
        Assert.assertTrue(wordsFinder.findWords("c?").size() == 2);
    }

    @Test
    public void shouldReturn6WordsForQmQm() {
        Assert.assertTrue(wordsFinder.findWords("??").size() == 6);
    }

    @Test
    public void shouldReturn9WordsForQmQmQm() {
        Assert.assertTrue(wordsFinder.findWords("???").size() == 9);
    }

    @Test
    public void shouldReturn9WordsForQmQmQmQm() {
        Assert.assertTrue(wordsFinder.findWords("????").size() == 9);
    }


}
