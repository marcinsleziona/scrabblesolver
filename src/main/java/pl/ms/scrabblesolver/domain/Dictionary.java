package pl.ms.scrabblesolver.domain;

import java.util.Collection;
import java.util.Set;

/*
 * Created by Marcin on 2017-03-22.
 */
public interface Dictionary {

    Set<Word> getByPrefix(String prefix);

    Set<Word> getByCharacterAndLength(Character c, Integer length);

    Set<Word> getByKey(String key);

    Collection<Character> getCharacters();

}
