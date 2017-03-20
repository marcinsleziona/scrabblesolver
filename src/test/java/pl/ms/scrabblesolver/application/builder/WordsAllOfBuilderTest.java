package pl.ms.scrabblesolver.application.builder;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/*
 * Created by Marcin on 2017-03-19.
 */
public class WordsAllOfBuilderTest {

    private WordsAllOfBuilder wordsAllOfBuilder;

    @Before
    public void before() {
        this.wordsAllOfBuilder = new WordsAllOfBuilder();
    }

    @Test
    public void shouldReturnEmptyListForNull() {
        Assert.assertTrue(CollectionUtils.isEmpty(wordsAllOfBuilder.build(null)));
    }

    @Test
    public void shouldReturnEmptyListForEmpty() {
        Assert.assertTrue(CollectionUtils.isEmpty(wordsAllOfBuilder.build("")));
    }

    @Test
    public void shouldReturnEmptyListFor1Character() {
        Assert.assertTrue(CollectionUtils.isEmpty(wordsAllOfBuilder.build("")));
    }

    @Test
    public void shouldReturn1WordFor2Characters() {
        Assert.assertTrue(wordsAllOfBuilder.build("ab").size() == 1);
    }

    @Test
    public void shouldReturn2WordsForAAA() {
        Assert.assertTrue(wordsAllOfBuilder.build("aaa").size() == 2);
    }

    @Test
    public void shouldReturn3WordsForABA() {
        Assert.assertTrue(wordsAllOfBuilder.build("aba").size() == 3);
    }

    @Test
    public void shouldReturn4WordsForABC() {
        Assert.assertTrue(wordsAllOfBuilder.build("abc").size() == 4);
    }

    @Test
    public void shouldReturn11WordsForABCD() {
        Assert.assertTrue(wordsAllOfBuilder.build("abcd").size() == 11);
    }
}
