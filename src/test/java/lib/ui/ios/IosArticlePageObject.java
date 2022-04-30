package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IosArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "id:Java (programming language)";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://XCUIElementTypeButton[@name='Save for later']";
        OPTIONS_FONT_AND_THEME = "xpath://*[@text='Font and theme']";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        LIST_ITEM_TITLE = "id:org.wikipedia:id/item_title";
        LIST_ITEM_TITLE_TPL = "xpath://*[@text='Articles']";
    }

    public IosArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }
}
