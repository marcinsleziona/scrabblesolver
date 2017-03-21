package pl.ms.scrabblesolver;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.ms.scrabblesolver.application.anagram.WordAnagramService;
import pl.ms.scrabblesolver.application.finder.WordsFinder;
import pl.ms.scrabblesolver.application.matcher.WordMatcher;
import pl.ms.scrabblesolver.application.validator.WordValidator;
import pl.ms.scrabblesolver.domain.Word;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
public class ScrabbleSolverApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScrabbleSolverApplication.class, args);
    }
//
//    @SpringUI(path = "ui")
//    @Theme("valo")
//    class ScrabbleSolverApplicationUI extends UI {
//
//        @Autowired
//        private WordValidator wordValidator;
//        @Autowired
//        private WordMatcher wordMatcher;
//        @Autowired
//        private WordAnagramService wordAnagramService;
//        @Autowired
//        private WordsFinder wordsFinder;
//
//        @Override
//        protected void init(VaadinRequest vaadinRequest) {
//            GridLayout grid = new GridLayout(4, 5);
//            grid.setSpacing(true);
//            grid.setSizeFull();
//
//            // add text
//            TextField textField = new TextField();
//            textField.setWidth("100%");
//            grid.addComponent(textField, 0, 0, 3, 0);
//
//            // add buttons
////            HorizontalLayout hlayout = new HorizontalLayout();
////            hlayout.setSpacing(true);
////            hlayout.setWidth("100%");
//
//            TextArea textArea = new TextArea("");
//
//            Button validate = new Button("Validate");
//            validate.addClickListener(clickEvent -> {
//                        if (wordValidator.isValid(Word.of(textField.getValue()))) {
//                            textArea.setValue("OK");
//                        } else {
//                            textArea.setValue("INVALID");
//                        }
//                    }
//            );
//            grid.addComponent(validate, 0, 1);
//
//            Button match = new Button("Match");
//            match.addClickListener(clickEvent -> {
//                        StringBuffer sb = new StringBuffer();
//                        Map<Integer, List<Word>> map = wordMatcher.findMatchingWords(textField.getValue()).stream().collect(Collectors.groupingBy(Word::length));
//                        map.forEach((integer, strings) -> sb.append(integer).append(System.getProperty("line.separator")).append(strings).append(System.getProperty("line.separator")));
//                        if (StringUtils.isEmpty(sb)) {
//                            textArea.setValue("No results found !");
//                        } else {
//                            textArea.setValue(sb.toString());
//                        }
//                    }
//            );
//            grid.addComponent(match, 1, 1);
//
//            Button findAnagrams = new Button("Find Anagrams");
//            findAnagrams.addClickListener(clickEvent -> {
//                        StringBuffer sb = new StringBuffer();
//                        Map<Integer, List<Word>> map = wordAnagramService.findAnagrams(textField.getValue()).stream().collect(Collectors.groupingBy(Word::length));
//                        map.forEach((integer, strings) -> sb.append(integer).append(System.getProperty("line.separator")).append(strings).append(System.getProperty("line.separator")));
//                        if (StringUtils.isEmpty(sb)) {
//                            textArea.setValue("No results found !");
//                        } else {
//                            textArea.setValue(sb.toString());
//                        }
//                    }
//            );
//            grid.addComponent(findAnagrams, 2, 1);
//
//            Button findAll = new Button("Find All");
//            findAll.addClickListener(clickEvent -> {
//                        StringBuffer sb = new StringBuffer();
//                        Map<Integer, List<Word>> map = wordsFinder.findWords(textField.getValue()).stream().collect(Collectors.groupingBy(Word::length));
//                        map.forEach((integer, strings) -> sb.append(integer).append(System.getProperty("line.separator")).append(strings).append(System.getProperty("line.separator")));
//                        if (StringUtils.isEmpty(sb)) {
//                            textArea.setValue("No results found !");
//                        } else {
//                            textArea.setValue(sb.toString());
//                        }
//                    }
//            );
//            grid.addComponent(findAll, 3, 1);
////            grid.addComponent(hlayout, 0, 1, 3, 1);
//
//            // add result
//            //TextArea textArea = new TextArea("");
//            textArea.setWordWrap(true);
//            textArea.setEnabled(false);
//            textArea.setWidth("100%");
//            textArea.setHeight("100%");
//            grid.addComponent(textArea, 0, 2, 3, 4);
//
//            final Panel mainPanel = new Panel("");
//            mainPanel.setContent(grid);
//            mainPanel.setWidth("800px");
//            mainPanel.setHeight("600px");
//
//            VerticalLayout layout = new VerticalLayout();
//            layout.addComponent(mainPanel);
//            layout.setComponentAlignment(mainPanel, Alignment.MIDDLE_CENTER);
//            layout.setSizeFull();
//            setContent(layout);
//        }
//    }

}
