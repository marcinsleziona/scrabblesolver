package pl.ms.scrabblesolver.infrastructure;

/*
 * Created by Marcin on 2017-03-22.
 */
public class DictionaryFixture {

    public static DictionaryImpl build(String... texts) {
        DictionaryImpl mock = new DictionaryImpl();
        for (String text : texts) {
            mock.add(text);
        }
        return mock;
    }
}
