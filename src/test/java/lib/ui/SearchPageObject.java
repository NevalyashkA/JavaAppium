package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
            SEARCH_INIT_ELEMENT ,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT;


    public SearchPageObject(RemoteWebDriver driver){
        super(driver);
    }

    private static String getResultSearchElement (String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}",substring);
    }
    private static String getResultSearchElement (String title, String description){
        return SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
    }
    private static String getResultSearchElement (String search_string, String title, String description){
        String new_title = StringUtils.remove(title, search_string);
        return SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL.replace("{TITLE}", search_string).replace("{SUBTITLE}", new_title).replace("{DESCRIPTION}", description);
    }

    public void initSearchInput (){
        this.waitForElementPresent(SEARCH_INIT_ELEMENT,"Cannot find search input after clicking search init element");
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT,"Cannot find and click search init element",5);
    }
    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON,"Cannot find search cancel button",5);
    }
    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON,"Search cancel button is still present",5);
    }
    public void clickCancelSearch(){
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON,"Cannot find and click search cancel button",5);
    }
    public void typeSearchLine(String search_line){
        this.waitForElementAndSendKeys(SEARCH_INPUT,search_line,"Cannot find and type into search input",5);
    }
    public void waitForSearchResult(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath,"Cannot find search result with substring " + substring,10);
    }
    public void clickByArticleWithSubstring(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath,"Cannot find and click search result with substring " + substring,10);
    }
    public int getAmountOfFoundArticles(){
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request ",
                15
        );
        return this.getAmountOfElement(SEARCH_RESULT_ELEMENT);
    }
    public void waitForEmptyResultLabel(){
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT,"Cannot find empty result element.",15);
    }
    public void  assertThereIsNoResultOfSearch(){
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT,"We supposed not to find any results");
    }
    public void  assertThereIsResultOfSearch(){
        this.assertElementPresent(SEARCH_RESULT_ELEMENT,"Cannot find empty result element");
    }
    public void waitForElementByTitleAndDescription(String title, String description){
        String search_result_xpath = getResultSearchElement(title, description);
        this.waitForElementPresent(
               search_result_xpath,
                "Cannot find search result with title: '" + title + "' and description: '" + description + "'.");

    }
    public void waitForElementByTitleAndDescription(String search_string, String title, String description){
        String search_result_xpath = getResultSearchElement(search_string,title, description);
        this.waitForElementPresent(
                search_result_xpath,
                "Cannot find search result '" + search_string + "' with title: '" + title + "' and description: '" + description + "'.");

    }
}
