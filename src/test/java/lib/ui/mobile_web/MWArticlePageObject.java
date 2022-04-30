package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "css:#firstHeading";
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "css:#page-actions li#page-actions-watch a#ca-watch";
        OPTIONS_FONT_AND_THEME = "xpath://*[@text='Font and theme']";
        LIST_ITEM_TITLE = "id:org.wikipedia:id/item_title";
        LIST_ITEM_TITLE_TPL = "xpath://*[@text='Articles']";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:#page-actions li#page-actions-watch a#ca-watch.menu__item--page-actions-watch";

    }

    public MWArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }
}
