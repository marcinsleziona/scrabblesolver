package pl.ms.scrabblesolver.domain;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/*
 * Created by Marcin on 2017-03-19.
 */
@RunWith(JUnitParamsRunner.class)
public class WordTest {

    @Test
    @Parameters
    public void toString(String text, String result) {
        Assert.assertEquals(result, Word.of(text).toString());
    }

    public Object[] parametersForToString() {
        return new Object[]{
                new Object[]{null, null},
                new Object[]{"", ""},
                new Object[]{"  ", ""},
                new Object[]{"test", "test"}
        };
    }


    @Test
    @Parameters
    public void isEmpty(String text, boolean result) {
        Assert.assertEquals(result, Word.of(text).isEmpty());
    }

    public Object[] parametersForIsEmpty() {
        return new Object[]{
                new Object[]{null, true},
                new Object[]{"", true},
                new Object[]{"  ", true},
                new Object[]{"test", false}
        };
    }

    @Test
    @Parameters
    public void charAt(String text, int idx, Character result) {
        Assert.assertEquals(result, Word.of(text).charAt(idx));
    }

    public Object[] parametersForCharAt() {
        return new Object[]{
                new Object[]{null, 1, null},
                new Object[]{"", 1, null},
                new Object[]{"  ", 1, null},
                new Object[]{"a", 0, 'a'},
                new Object[]{"a", 1, null}
        };
    }

    @Test
    @Parameters
    public void getText(String text, String result) {
        Assert.assertEquals(result, Word.of(text).getText());
    }

    public Object[] parametersForGetText() {
        return new Object[]{
                new Object[]{null, null},
                new Object[]{"", ""},
                new Object[]{"  ", ""},
                new Object[]{"test", "test"},
                new Object[]{"test test", "test test"},
                new Object[]{" test ", "test"}
        };
    }

    @Test
    @Parameters
    public void length(String text, int result) {
        Assert.assertEquals(result, Word.of(text).length());
    }

    public Object[] parametersForLength() {
        return new Object[]{
                new Object[]{null, 0},
                new Object[]{"", 0},
                new Object[]{"  ", 0},
                new Object[]{"test", 4},
                new Object[]{"test test", 9},
                new Object[]{" test ", 4}
        };
    }

    @Test
    @Parameters
    public void getCharactersSorted(String text, String result) {
        Assert.assertEquals(result, Word.of(text).getCharactersSorted());
    }

    public Object[] parametersForGetCharactersSorted() {
        return new Object[]{
                new Object[]{null, null},
                new Object[]{"", ""},
                new Object[]{"  ", ""},
                new Object[]{"test", "estt"},
                new Object[]{"test test", " eesstttt"},
                new Object[]{" test ", "estt"},
                new Object[]{"zasoiia", "aaiiosz"}
        };
    }

    @Test
    @Parameters
    public void startsWith(String text, String prefix, boolean result) {
        Assert.assertEquals(result, Word.of(text).startsWith(prefix));
    }

    public Object[] parametersForStartsWith() {
        return new Object[]{
                new Object[]{null, null, true},
                new Object[]{"", "", true},
                new Object[]{"  ", "", true},
                new Object[]{"test", null, false},
                new Object[]{"abc", "a", true},
                new Object[]{"abc", "A", false},
                new Object[]{"abc ", "abcd", false}
        };
    }
}
