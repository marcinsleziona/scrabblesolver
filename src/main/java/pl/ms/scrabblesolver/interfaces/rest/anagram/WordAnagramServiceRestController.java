package pl.ms.scrabblesolver.interfaces.rest.anagram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.ms.scrabblesolver.application.anagram.WordAnagramService;
import pl.ms.scrabblesolver.domain.Word;

import java.util.List;

/*
 * Created by Marcin on 2017-03-20.
 */
@RestController
@RequestMapping("/words")
public class WordAnagramServiceRestController {

    private WordAnagramService wordAnagramService;

    @Autowired
    public WordAnagramServiceRestController(WordAnagramService wordAnagramService) {
        this.wordAnagramService = wordAnagramService;
    }

    @GetMapping("/findAnagrams")
    public List<Word> find(@RequestParam("input") String inputCharacters) {
        return this.wordAnagramService.findAnagrams(inputCharacters);
    }

}
