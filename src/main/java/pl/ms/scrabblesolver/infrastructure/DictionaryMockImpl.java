package pl.ms.scrabblesolver.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/*
 * Created by Marcin on 2017-03-19.
 */
@Component
@ConditionalOnNoDictionary
public class DictionaryMockImpl extends DictionaryImpl {

    private final static Logger LOG = LoggerFactory.getLogger(DictionaryCompleteImpl.class);

    @PostConstruct
    public void init() {
        String[] texts = new String[]{"aa", "ab", "ac", "ba", "bb", "bc", "aaa", "aba", "aca"};
        for (String text : texts) {
            this.add(text);
        }
    }
}
