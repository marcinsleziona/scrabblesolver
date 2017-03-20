package pl.ms.scrabblesolver.interfaces.rest.finder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.ms.scrabblesolver.application.finder.WordsFinder;
import pl.ms.scrabblesolver.domain.Word;

import java.util.List;

/*
 * Created by Marcin on 2017-03-19.
 */
@RestController
public class WordsFinderRestController {

    private WordsFinder wordsFinder;

    @Autowired
    public WordsFinderRestController(WordsFinder wordsFinder) {
        this.wordsFinder = wordsFinder;
    }

    @GetMapping("/words")
    public List<Word> find(@RequestParam("input") String inputCharacters) {
        return this.wordsFinder.findWords(inputCharacters);
    }
}
