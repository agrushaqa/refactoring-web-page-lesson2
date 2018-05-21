package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.logging.Logger;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class ArtLebedevPage {
    private Logger log = Logger.getLogger(ArtLebedevPage.class.getName());
    private String currentUrl;
    //public ideaMatrix blockIdeas;
    //public Menu mainMenu;

    public ArtLebedevPage(){
        open("https://www.artlebedev.ru/");
        currentUrl = WebDriverRunner.url();
    }

    //public class Menu extends ElementsContainer{
        public ideaMatrix Inventar(){
            $("a[href=\"/tools/\"]").click();
            currentUrl = WebDriverRunner.url();
            return new ideaMatrix(); //blockIdeas;
        }
    //}

    public class ideaMatrix /* extends ElementsContainer */ {
        //public ideaResults blockIdeaResults;

        public Condition url(final String checkUrl){
            //@TODO не используется и похоже не работает
            return new Condition("url") {
                @Override
                public boolean apply(WebElement element) {
                    return WebDriverRunner.url().equalsIgnoreCase(checkUrl);
                }

                @Override
                public String actualValue(WebElement element) {
                    return WebDriverRunner.url();
                }
            };
        }

        public ideaMatrix(){
            $("a#item-matrix div.descr div.title").click();
        }

        public ideaResults findIdea(String idea){
            log.info("Start find idea about :" + idea);
            String editFieldForFindIdea = "input.input";
            $(editFieldForFindIdea).clear();
            $(editFieldForFindIdea).setValue(idea);
            $(editFieldForFindIdea).pressEnter();//.shouldNot(url(currentUrl));
            $("div.col-self-tablet-12").shouldHave(text(idea));
            return new ideaResults(); //blockIdeaResults;
        }

        public class ideaResults /* extends ElementsContainer */{
            private List<String> listIdeaResults;

            public ideaResults get(){
                listIdeaResults = $$("div.col-self-tablet-12").texts();
                return this;
            }
            public int count(){
                return listIdeaResults.size();
            }

            public List<String> text(){
                for (String idea: listIdeaResults) {
                    log.info(idea);
                }
                return listIdeaResults;
            }
        }
    }

}


