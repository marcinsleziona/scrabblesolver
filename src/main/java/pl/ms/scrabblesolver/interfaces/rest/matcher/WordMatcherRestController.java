package pl.ms.scrabblesolver.interfaces.rest.matcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.ms.scrabblesolver.application.finder.WordsFinder;
import pl.ms.scrabblesolver.application.matcher.WordMatcher;
import pl.ms.scrabblesolver.domain.Word;

import java.util.List;

/*
 * Created by Marcin on 2017-03-20.
 */
@RestController
@RequestMapping("/words")
public class WordMatcherRestController {

    private WordMatcher wordMatcher;

    @Autowired
    public WordMatcherRestController(WordMatcher wordMatcher) {
        this.wordMatcher = wordMatcher;
    }

    @GetMapping("/match")
    public List<Word> find(@RequestParam("input") String inputCharacters) {
        return this.wordMatcher.findMatchingWords(inputCharacters);
    }
}
