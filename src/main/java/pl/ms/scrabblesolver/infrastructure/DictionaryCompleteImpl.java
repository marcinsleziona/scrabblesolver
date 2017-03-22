package pl.ms.scrabblesolver.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/*
 * Created by Marcin on 2017-03-19.
 */
@Component
@ConditionalOnDictionary
public class DictionaryCompleteImpl extends DictionaryImpl {

    private final static Logger LOG = LoggerFactory.getLogger(DictionaryCompleteImpl.class);

    @PostConstruct
    public void init() {
        try {
            try (InputStream resource = DictionaryCompleteImpl.class.getResourceAsStream("/sjp-20150906.zip")) {
                ZipInputStream stream = new ZipInputStream(resource);
                ZipEntry ze = stream.getNextEntry();
                if (ze.getName().equals("slowa-win.txt")) {
                    new BufferedReader(new InputStreamReader(stream, Charset.forName("windows-1250"))).lines().forEach(this::add);
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

}
