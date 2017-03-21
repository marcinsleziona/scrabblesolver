package pl.ms.scrabblesolver.interfaces.view;

import com.vaadin.annotations.Theme;
import com.vaadin.data.Binder;
import com.vaadin.data.BinderValidationStatus;
import com.vaadin.data.ValidationResult;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import pl.ms.scrabblesolver.application.anagram.WordAnagramService;
import pl.ms.scrabblesolver.application.finder.WordsFinder;
import pl.ms.scrabblesolver.application.matcher.WordMatcher;
import pl.ms.scrabblesolver.application.validator.WordValidator;
import pl.ms.scrabblesolver.domain.Word;
import pl.ms.scrabblesolver.interfaces.view.data.InputWord;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * Created by Marcin on 2017-03-21.
 */
@Theme("valo")
@SpringUI(path = "ui")
public class MainView extends UI {

    @Autowired
    private WordValidator wordValidator;
    @Autowired
    private WordMatcher wordMatcher;
    @Autowired
    private WordAnagramService wordAnagramService;
    @Autowired
    private WordsFinder wordsFinder;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout content = new VerticalLayout();
        setContent(content);

        HorizontalLayout titleBar = new HorizontalLayout();
        titleBar.setWidth("100%");
        content.addComponent(titleBar);

        Label title = new Label("Scrabble Solver");
        titleBar.addComponent(title);
        titleBar.setComponentAlignment(title, Alignment.MIDDLE_CENTER);
        titleBar.setExpandRatio(title, 1.0f);

        // add text
        TextField inputWordField = new TextField();
        inputWordField.setWidth("100%");
        inputWordField.setComponentError(new UserError("Only characters are valid !"));
        inputWordField.setResponsive(true);
        inputWordField.setPlaceholder("Please enter the text, '?' replaces one character ...");
        Binder<InputWord> inputWordBinder = new Binder<>();
        inputWordBinder
                .forField(inputWordField)
                .asRequired("Please enter the text !")
                .withValidator(new RegexpValidator("Only characters (up to 10) are valid !", "[a-z?]{1,10}"))
                .bind(InputWord::getText, InputWord::setText);
        content.addComponent(inputWordField);

        TextArea textArea = new TextArea("");

        HorizontalLayout buttonBar = new HorizontalLayout();
        buttonBar.setWidth("100%");
        content.addComponent(buttonBar);

        Button validate = new Button("Validate");
        validate.setIcon(VaadinIcons.SEARCH);
        validate.addClickListener(clickEvent -> {
                    BinderValidationStatus<InputWord> status = inputWordBinder.validate();
                    if (status.hasErrors()) {
                        Notification.show("Validation errors: " +
                                StringUtils.join(
                                        status.getValidationErrors()
                                                .stream()
                                                .map(ValidationResult::getErrorMessage)
                                                .collect(Collectors.toList()), ","), Notification.Type.ERROR_MESSAGE);
                        return;
                    }
                    if (wordValidator.isValid(Word.of(inputWordField.getValue()))) {
                        textArea.setValue("OK");
                    } else {
                        textArea.setValue("INVALID");
                    }
                }
        );
        buttonBar.addComponent(validate);

        Button match = new Button("Match");
        match.setIcon(VaadinIcons.SEARCH);
        match.addClickListener(clickEvent -> {
                    BinderValidationStatus<InputWord> status = inputWordBinder.validate();
                    if (status.hasErrors()) {
                        Notification.show("Validation errors: " +
                                StringUtils.join(
                                        status.getValidationErrors()
                                                .stream()
                                                .map(ValidationResult::getErrorMessage)
                                                .collect(Collectors.toList()), ","), Notification.Type.ERROR_MESSAGE);
                        return;
                    }
                    StringBuffer sb = new StringBuffer();
                    Map<Integer, List<Word>> map = wordMatcher.findMatchingWords(inputWordField.getValue()).stream().collect(Collectors.groupingBy(Word::length));
                    map.forEach((integer, strings) -> sb.append(integer).append(System.getProperty("line.separator")).append(strings).append(System.getProperty("line.separator")));
                    if (StringUtils.isEmpty(sb)) {
                        textArea.setValue("No results found !");
                    } else {
                        textArea.setValue(sb.toString());
                    }
                }
        );
        buttonBar.addComponent(match);

        Button findAnagrams = new Button("Find Anagrams");
        findAnagrams.setIcon(VaadinIcons.SEARCH);
        findAnagrams.addClickListener(clickEvent -> {
                    BinderValidationStatus<InputWord> status = inputWordBinder.validate();
                    if (status.hasErrors()) {
                        Notification.show("Validation errors: " +
                                StringUtils.join(
                                        status.getValidationErrors()
                                                .stream()
                                                .map(ValidationResult::getErrorMessage)
                                                .collect(Collectors.toList()), ","), Notification.Type.ERROR_MESSAGE);
                        return;
                    }
                    StringBuffer sb = new StringBuffer();
                    Map<Integer, List<Word>> map = wordAnagramService.findAnagrams(inputWordField.getValue()).stream().collect(Collectors.groupingBy(Word::length));
                    map.forEach((integer, strings) -> sb.append(integer).append(System.getProperty("line.separator")).append(strings).append(System.getProperty("line.separator")));
                    if (StringUtils.isEmpty(sb)) {
                        textArea.setValue("No results found !");
                    } else {
                        textArea.setValue(sb.toString());
                    }
                }
        );
        buttonBar.addComponent(findAnagrams);

        Button findAll = new Button("Find All");
        findAll.setIcon(VaadinIcons.SEARCH);
        findAll.addClickListener(clickEvent -> {
                    BinderValidationStatus<InputWord> status = inputWordBinder.validate();
                    if (status.hasErrors()) {
                        Notification.show("Validation errors: " +
                                StringUtils.join(
                                        status.getValidationErrors()
                                                .stream()
                                                .map(ValidationResult::getErrorMessage)
                                                .collect(Collectors.toList()), ","), Notification.Type.ERROR_MESSAGE);
                        return;
                    }
                    StringBuffer sb = new StringBuffer();
                    Map<Integer, List<Word>> map = wordsFinder.findWords(inputWordField.getValue()).stream().collect(Collectors.groupingBy(Word::length));
                    map.forEach((integer, strings) -> sb.append(integer).append(System.getProperty("line.separator")).append(strings).append(System.getProperty("line.separator")));
                    if (StringUtils.isEmpty(sb)) {
                        textArea.setValue("No results found !");
                    } else {
                        textArea.setValue(sb.toString());
                    }
                }
        );
        buttonBar.addComponent(findAll);

        // add result
        textArea.setWordWrap(true);
        textArea.setEnabled(false);
        textArea.setWidth("100%");
//        textArea.setHeight("100%");
        textArea.setRows(20);
        content.addComponent(textArea);
    }
}
