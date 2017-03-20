package pl.ms.scrabblesolver.application.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ms.scrabblesolver.domain.Word;
import pl.ms.scrabblesolver.infrastructure.Dictionary;

/*
 * Created by Marcin on 2017-03-19.
 */
@Service
public class WordValidatorImpl implements WordValidator {

    private Dictionary dictionary;

    @Autowired
    public WordValidatorImpl(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public boolean isValid(Word word) {
        return !(word == null || word.isEmpty()) && !dictionary.getByPrefix(word.getText()).isEmpty();
    }
}
