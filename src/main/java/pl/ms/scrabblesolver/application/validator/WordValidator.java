package pl.ms.scrabblesolver.application.validator;

import pl.ms.scrabblesolver.domain.Word;

/*
 * Created by Marcin on 2017-03-19.
 */
public interface WordValidator {

    /**
     * check whether the word is valid
     */
    boolean isValid(Word word);
}
