package pl.ms.scrabblesolver;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScrabbleSolverApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScrabbleSolverApplication.class, args);
    }

    @SpringUI(path = "ui")
    @Theme("valo")
    class ScrabbleSolverApplicationUI extends UI {
        @Override
        protected void init(VaadinRequest vaadinRequest) {
            //setContent();
        }
    }

}
