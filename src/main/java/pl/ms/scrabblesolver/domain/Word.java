package pl.ms.scrabblesolver.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
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

    private String text;

    public static Word of(String text) {
        Word w = new Word();
        w.text = StringUtils.trim(text);
        return w;
    }

    public boolean isEmpty() {
        return StringUtils.isEmpty(text);
    }

    public String getText() {
        return text;
    }

    public int length() {
        if (StringUtils.isBlank(text)) {
            return 0;
        }
        return text.length();
    }

    public Character charAt(int idx) {
        if (isEmpty()) {
            return null;
        }
        if (idx >= this.length()) {
            return null;
        }
        return text.charAt(idx);
    }

    public String getCharactersSorted() {
        if (text == null) {
            return null;
        }
        char[] ctab = text.toCharArray();
        Arrays.sort(ctab);
        return String.valueOf(ctab);
    }

    public boolean startsWith(String prefix) {
        return StringUtils.startsWith(text, prefix);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
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
