package pl.ms.scrabblesolver.interfaces.rest.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.ms.scrabblesolver.application.builder.WordsAllOfBuilder;
import pl.ms.scrabblesolver.domain.Word;

import java.util.List;

/*
 * Created by Marcin on 2017-03-20.
 */
@RestController
@RequestMapping("/words")
public class WordsAllOfBuilderRestController {

    private WordsAllOfBuilder wordsAllOfBuilder;

    @Autowired
    public WordsAllOfBuilderRestController(WordsAllOfBuilder wordsAllOfBuilder) {
        this.wordsAllOfBuilder = wordsAllOfBuilder;
    }

    @GetMapping("/build")
    public List<Word> find(@RequestParam("input") String inputCharacters) {
        return this.wordsAllOfBuilder.build(inputCharacters);
    }

}
