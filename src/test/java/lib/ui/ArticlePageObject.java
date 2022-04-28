package lib.ui;

import lib.Platform;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

abstract public class ArticlePageObject extends MainPageObject{

    protected static String
            TITLE,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            OPTIONS_FONT_AND_THEME,
            ADD_TO_MY_LIS_OVERLAY,
            MY_LIST_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            LIST_ITEM_TITLE,
            LIST_ITEM_TITLE_TPL;

    public ArticlePageObject(AppiumDriver driver){
        super(driver);
    }

    private static String getListItemTitleElement (String substring){
        return LIST_ITEM_TITLE_TPL.replace("{ITEM}",substring);
    }

    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(
                TITLE,
                "Cannot find article title on page!",
                15
        );
    }
    public String getArticleTitle(){
        WebElement title_element = waitForTitleElement();
        if(Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        }else {
            return title_element.getAttribute("name");
        }
    }
    public void swipeToFooter (){
        if(Platform.getInstance().isAndroid()) {
            this.swipeUpToElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    40

            );
        }else{
            this.swipeUpTillElementAppear(
                    FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    40);
        }
    }
    public void addArticleToMyList (String name_of_folder){
        waitForTitleElement();
        this.waitForElementPresent(
                CLOSE_ARTICLE_BUTTON,
                "Cannot find btn to option article");

        this.waitForElementAndClick(
               OPTIONS_BUTTON,
                "Cannot find btn to option article",
                10
        );
        this.waitForElementPresent(
                OPTIONS_FONT_AND_THEME,
                "Cannot find options list",
                5
        );
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article 'Add to reading list'",
                5
        );
        this.waitForElementAndClick(
                ADD_TO_MY_LIS_OVERLAY,
                "Cannot find 'Got it' tip overlay",
                5
        );
        this.waitForElementAndClear(
                MY_LIST_INPUT,
                "Cannot find input to set name of articles folder",
                5
        );
        this.waitForElementAndSendKeys(
                MY_LIST_INPUT,
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );
        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press ok btn",
                5
        );
    }
    public void addNextArticleToMyList (String name_of_folder){
        waitForTitleElement();
        this.waitForElementPresent(
                CLOSE_ARTICLE_BUTTON,
                "Cannot find btn to option article");

        this.waitForElementAndClick(
               OPTIONS_BUTTON,
                "Cannot find btn to option article",
                5
        );
        this.waitForElementPresent(
                OPTIONS_FONT_AND_THEME,
                "Cannot find options list",
                5
        );
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article 'Add to reading list'",
                5
        );
        this.waitForElementAndClick(
                getListItemTitleElement(name_of_folder),
                "Cannot find created list articles",
                15
        );

    }
    public void addArticlesToMySaved(){
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );
    }
    public void closeArticle(){
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article, cannot find X link",
                5
        );
    }
}
