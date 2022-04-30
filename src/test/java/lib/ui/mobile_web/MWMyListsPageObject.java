package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://ul[contains(@class,'page-summary')]//h3[contains(text(), '{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://ul[contains(@class,'page-summary')]//h3[contains(text(), '{TITLE}')]/../../a[contains(@class, 'mw-ui-icon')]";
        ITEM_OVERFLOW_MENU = "id:swipe action delete";
        CLOSE_OVERFLOW_SYNC_MENU = "id:Close";
    }
    public MWMyListsPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
