package pl.ms.scrabblesolver.application.builder;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * Created by Marcin on 2017-03-19.
 */
public class WordsFixedSizeBuilderTest {

    private Collection<Character> characters;

    private WordsFixedSizeBuilder wordsFixedSizeBuilder;

    @Before
    public void before() {
        this.characters = Stream.of('a', 'b', 'c').collect(Collectors.toList());
        this.wordsFixedSizeBuilder = new WordsFixedSizeBuilder();
    }

    @Test
    public void shouldReturnEmptyListForNull() {
        Assert.assertTrue(CollectionUtils.isEmpty(wordsFixedSizeBuilder.build(characters, null)));
    }

    @Test
    public void shouldReturnEmptyListForEmpty() {
        Assert.assertTrue(CollectionUtils.isEmpty(wordsFixedSizeBuilder.build(characters, "")));
    }

    @Test
    public void shouldReturn1WordForZ() {
        Assert.assertTrue(wordsFixedSizeBuilder.build(characters, "z").size() == 1);
    }

    @Test
    public void shouldReturn1WordForZZ() {
        Assert.assertTrue(wordsFixedSizeBuilder.build(characters, "zz").size() == 1);
    }

    @Test
    public void shouldReturn3WordsForZQm() {
        Assert.assertTrue(wordsFixedSizeBuilder.build(characters, "z?").size() == 3);
    }

    @Test
    public void shouldReturn1WordForAA() {
        Assert.assertTrue(wordsFixedSizeBuilder.build(characters, "aa").size() == 1);
    }

    @Test
    public void shouldReturn3WordsForQm() {
        Assert.assertTrue(wordsFixedSizeBuilder.build(characters, "?").size() == 3);
    }

    @Test
    public void shouldReturn6WordsForQmQm() {
        Assert.assertTrue(wordsFixedSizeBuilder.build(characters, "??").size() == 6);
    }

    @Test
    public void shouldReturn10WordsForQmQmQm() {
        Assert.assertTrue(wordsFixedSizeBuilder.build(characters, "???").size() == 10);
    }

    @Test
    public void shouldReturn15WordsForQmQmQmQm() {
        Assert.assertTrue(wordsFixedSizeBuilder.build(characters, "????").size() == 15);
    }
}
