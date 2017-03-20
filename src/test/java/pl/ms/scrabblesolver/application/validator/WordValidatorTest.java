package pl.ms.scrabblesolver.application.validator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.ms.scrabblesolver.domain.Word;
import pl.ms.scrabblesolver.infrastructure.Dictionary;
import pl.ms.scrabblesolver.infrastructure.DictionaryFixture;

/*
 * Created by Marcin on 2017-03-19.
 */
public class WordValidatorTest {

    private WordValidator wordValidator;

    @Before
    public void before() {
        Dictionary dictionary = DictionaryFixture.build("africa", "asia", "europe", "australia");
        this.wordValidator = new WordValidatorImpl(dictionary);
    }

    @Test
    public void shouldReturnFalseForNull() {
        Assert.assertEquals(false, wordValidator.isValid(Word.of(null)));
    }

    @Test
    public void shouldReturnFalseForEmpty() {
        Assert.assertEquals(false, wordValidator.isValid(Word.of("")));
    }

    @Test
    public void shouldReturnFalseForInvalidWord() {
        Assert.assertEquals(false, wordValidator.isValid(Word.of("illegal")));
    }

    @Test
    public void shouldReturnTrueForValidWord() {
        Assert.assertEquals(true, wordValidator.isValid(Word.of("asia")));
    }

}
