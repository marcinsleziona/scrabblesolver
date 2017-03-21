package pl.ms.scrabblesolver.interfaces.view.data;

import java.io.Serializable;

/*
 * Created by Marcin on 2017-03-21.
 */
public class InputWord implements Serializable {

    private static final long serialVersionUID = 7155631827916720672L;

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "InputWord{" +
                "text='" + text + '\'' +
                '}';
    }
}
