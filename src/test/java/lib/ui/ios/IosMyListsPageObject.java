package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class IosMyListsPageObject extends MyListsPageObject {
    static {
                ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[contains(@name, '{TITLE}')]";
                ITEM_OVERFLOW_MENU = "id:swipe action delete";
                CLOSE_OVERFLOW_SYNC_MENU = "id:Close";
    }
    public IosMyListsPageObject(AppiumDriver driver){
        super(driver);
    }
}
