package pl.ms.scrabblesolver.interfaces.rest.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ms.scrabblesolver.application.builder.WordsFixedSizeBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/*
 * Created by Marcin on 2017-03-20.
 */
@RestController
@RequestMapping("/words")
public class WordsFixedSizeBuilderRestController {

    private WordsFixedSizeBuilder wordsFixedSizeBuilder;

    @Autowired
    public WordsFixedSizeBuilderRestController(WordsFixedSizeBuilder wordsFixedSizeBuilder) {
        this.wordsFixedSizeBuilder = wordsFixedSizeBuilder;
    }

    @PostMapping("/buildWithAlphabet")
    public ResponseEntity<Set<String>> find(
            @RequestPart("alphabet") String alphabet,
            @RequestPart("input") String input) {
        List<Character> lalphabet = new ArrayList<>();
        for (char c : alphabet.toCharArray()) {
            lalphabet.add(c);
        }
        return ResponseEntity.ok(this.wordsFixedSizeBuilder.build(
                lalphabet,
                input));
    }

}
