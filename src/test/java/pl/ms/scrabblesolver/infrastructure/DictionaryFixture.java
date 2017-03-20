package pl.ms.scrabblesolver.infrastructure;

/*
 * Created by Marcin on 2017-03-19.
 */
public class DictionaryFixture {

    public static Dictionary build(String... texts) {
        Dictionary dictionary = new Dictionary();
        for (String text : texts) {
            dictionary.add(text);
        }
        return dictionary;
    }

}
