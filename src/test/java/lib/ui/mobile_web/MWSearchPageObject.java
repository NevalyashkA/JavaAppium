package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "css:form>input[type='search']";
        SEARCH_CANCEL_BUTTON = "xpath://ul[contains(@class, 'header-cancel')]/li/button[contains(text(), 'Close')]";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://div[contains(@class,'wikidata-description')][contains(text(),'{SUBSTRING}')]";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://a[contains(@class,'title')]/h3/strong[contains(text(),'{TITLE}')]/../../h3[contains(text(),'{SUBTITLE}')]/../div[contains(@class,'wikidata-description')][contains(text(),'{DESCRIPTION}')]";
        SEARCH_RESULT_ELEMENT = "css:ul.page-list>li.page-summary";
        SEARCH_EMPTY_RESULT_ELEMENT = "css:p.without-results";
    }
    public MWSearchPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
