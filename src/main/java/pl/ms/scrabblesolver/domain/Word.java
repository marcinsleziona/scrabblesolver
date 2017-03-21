package pl.ms.scrabblesolver.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.*;

import java.io.Serializable;
import java.util.Arrays;

/*
 * Created by Marcin on 2017-03-19.
 */
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE)
public class Word implements Serializable, Comparable<Word> {

    private byte[] text;

    public static Word of(String text) {
        Word w = new Word();
        String trimmed = StringUtils.trim(text);
        w.text = trimmed == null ? null : trimmed.getBytes();
        return w;
    }

    public boolean isEmpty() {
        return ArrayUtils.isEmpty(text);
    }

    public String getText() {
        if(text == null) {
            return null;
        }
        return new String(text);
    }

    public int length() {
        if (ArrayUtils.isEmpty(text)) {
            return 0;
        }
        return text.length;
    }

    public Character charAt(int idx) {
        if (isEmpty()) {
            return null;
        }
        if (idx >= this.length()) {
            return null;
        }
        return getText().charAt(idx);
    }

    public String getCharactersSorted() {
        if (text == null) {
            return null;
        }
        char[] ctab = getText().toCharArray();
        Arrays.sort(ctab);
        return String.valueOf(ctab);
    }

    public boolean startsWith(String prefix) {
        return StringUtils.startsWith(getText(), prefix);
    }

    @Override
    public String toString() {
        return getText();
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int compareTo(Word o) {
        return CompareToBuilder.reflectionCompare(this, o);
    }


}
