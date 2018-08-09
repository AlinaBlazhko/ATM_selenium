package ATM8_task.util;

import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.WebDriverRunner.CHROME;

public abstract class SelenideExtension {
    public SelenideExtension(){
        Configuration.browser = CHROME;
        Configuration.startMaximized = true;
    }

}
