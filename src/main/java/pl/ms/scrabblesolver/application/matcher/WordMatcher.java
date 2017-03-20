package pl.ms.scrabblesolver.application.matcher;

import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.ms.scrabblesolver.domain.Word;

import java.util.List;

/*
 * Created by Marcin on 2017-03-19.
 */
public interface WordMatcher {

    /**
     * words that matches the pattern, '?' replaces one character
     */
    List<Word> findMatchingWords(String pattern);
}
