package pages;

import com.codeborne.selenide.ElementsContainer;

import java.util.List;
import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.*;

public class ArtLebedevPage {
    Logger log = Logger.getLogger(ArtLebedevPage.class.getName());
    //public ideaMatrix blockIdeas;
    //public Menu mainMenu;

    public ArtLebedevPage(){
        open("https://www.artlebedev.ru/");
    }

    //public class Menu extends ElementsContainer{
        public ideaMatrix Inventar(){
            $("a[href=\"/tools/\"]").click();
            return new ideaMatrix(); //blockIdeas;
        }
    //}

    public class ideaMatrix /* extends ElementsContainer */ {
        //public ideaResults blockIdeaResults;

        String currentIdea = "";
        public ideaMatrix(){
            $("a#item-matrix div.descr div.title").click();
        }

        public ideaResults findIdea(String idea){
            log.info("Start find idea about :" + idea);
            String editFieldForFindIdea = "input.input";
            $(editFieldForFindIdea).clear();
            $(editFieldForFindIdea).setValue(idea);
            $(editFieldForFindIdea).pressEnter();
            return new ideaResults(); //blockIdeaResults;
        }

        public class ideaResults /* extends ElementsContainer */{
            private List<String> listIdeaResults = $$("div.col-self-tablet-12").texts();

            public ideaResults get(){
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
