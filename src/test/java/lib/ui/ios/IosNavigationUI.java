package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class IosNavigationUI extends NavigationUI {
    static {
        MY_LISTS_LINK = "id:Saved";
    }

    public IosNavigationUI(AppiumDriver driver){
        super(driver);
    }
}
