package pl.ms.scrabblesolver.application.anagram;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.ms.scrabblesolver.application.builder.WordsFixedSizeBuilder;
import pl.ms.scrabblesolver.infrastructure.DictionaryCompleteImpl;
import pl.ms.scrabblesolver.infrastructure.DictionaryMockImpl;

/*
 * Created by Marcin on 2017-03-19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WordAnagramServiceTest {

    @Autowired
    private WordAnagramService wordAnagramService;

//    @Before
//    public void before() {
//        DictionaryCompleteImpl dictionary = DictionaryMockImpl.build(
//                "aa", "ab", "ac", "ba", "bb", "bc",
//                "aaa", "aba", "aca");
//        this.wordAnagramService = new WordAnagramServiceImpl(dictionary, wordsFixedSizeBuilder);
//    }

    @Test
    public void shouldReturnEmptyListForNull() {
        Assert.assertTrue(CollectionUtils.isEmpty(wordAnagramService.findAnagrams(null)));
    }

    @Test
    public void shouldReturnEmptyListForEmpty() {
        Assert.assertTrue(CollectionUtils.isEmpty(wordAnagramService.findAnagrams("")));
    }

    @Test
    public void shouldReturnEmptyListForInvalidCharacters() {
        Assert.assertTrue(CollectionUtils.isEmpty(wordAnagramService.findAnagrams("z")));
    }

    @Test
    public void shouldReturnEmptyListForAAAA() {
        Assert.assertTrue(CollectionUtils.isEmpty(wordAnagramService.findAnagrams("aaaa")));
    }

    @Test
    public void shouldReturn1AnagramForAA() {
        Assert.assertTrue(wordAnagramService.findAnagrams("aa").size() == 1);
    }

    @Test
    public void shouldReturn4AnagramsForAQm() {
        Assert.assertTrue(wordAnagramService.findAnagrams("a?").size() == 4);
    }

    @Test
    public void shouldReturn4AnagramsForQmB() {
        Assert.assertTrue(wordAnagramService.findAnagrams("?b").size() == 4);
    }

    @Test
    public void shouldReturn2AnagramsForQmC() {
        Assert.assertTrue(wordAnagramService.findAnagrams("c?").size() == 2);
    }

    @Test
    public void shouldReturn6AnagramsForQmQm() {
        Assert.assertTrue(wordAnagramService.findAnagrams("??").size() == 6);
    }

    @Test
    public void shouldReturnEmptyListQmQmQmQm() {
        Assert.assertTrue(CollectionUtils.isEmpty(wordAnagramService.findAnagrams("????")));
    }

}
