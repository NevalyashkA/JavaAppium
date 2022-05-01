package tests;

import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import lib.CoreTestCase;

public class FirstTest extends CoreTestCase {

    private MainPageObject mainPageObject;
    private static final String
            login = "Daria.shine",
            password = "44162853";

    protected void setUp() throws Exception{
        super.setUp();
        mainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testSearch(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }
    @Test
    public void testCancelSearch(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();

    }

    @Test
    public void testCompareArticleTitle () throws InterruptedException{
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        Thread.sleep(5000);
        String article_title = articlePageObject.getArticleTitle();

        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );
    }
    @Test
    public void testSwipeArticle() throws InterruptedException{
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        Thread.sleep(5000);
        articlePageObject.waitForTitleElement();
        articlePageObject.swipeToFooter();
    }

    @Test
    public void testAssertElementHasText() {
        mainPageObject.waitForElementAndClick(
                "id:org.wikipedia:id/search_container",
                "Cannot find 'Search Wikipedia' input",
                5
        );
        mainPageObject.assertElementHasText(
                "id:org.wikipedia:id/search_src_text",
                "Search…",
                "Cannot article title",
                15
        );

    }
    @Test
    public void testCancelSearchEx3() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");

        int article_amount = searchPageObject.getAmountOfFoundArticles();
        Assert.assertTrue(
                "One article found",
                article_amount > 0
        );
        searchPageObject.clickCancelSearch();
        searchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testAssertWordsInTheSearchEx4() {
        mainPageObject.waitForElementAndClick(
                "id:org.wikipedia:id/search_container",
                "Cannot find 'Search Wikipedia' input",
                5
        );
        mainPageObject.waitForElementAndSendKeys(
                "id:org.wikipedia:id/search_src_text",
                "Java",
                "Cannot find search input",
                5
        );
        mainPageObject.waitForElementPresent(
                "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']",
                "The item didn't load",
                5
        );
        List<WebElement> webElementList = new ArrayList<WebElement>(
                driver.findElements(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']")));

        for(WebElement element : webElementList) {
            Assert.assertTrue(
                    "We see unexpected title",
                    element.getAttribute("text").contains("Java")
            );
        }

    }


    @Test
    public void testSaveFirstArticleToMyList(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);

        String name_of_folder = "Learning programming";

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.getArticleTitle();

        if(Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyList(name_of_folder);
            articlePageObject.closeArticle();
            navigationUI.clickMyLists();
            myListsPageObject.openFolderByName(name_of_folder);
        }else if(Platform.getInstance().isIOS()){
            articlePageObject.addArticlesToMySaved();
            articlePageObject.closeArticle();
            searchPageObject.clickCancelSearch();
            navigationUI.clickMyLists();
            myListsPageObject.cloceOverflowSyncMenu();
        }else{
            AuthorizationPageObject auth = new AuthorizationPageObject(driver);
            auth.clickAuthButton();
            auth.enterLoginDate(login, password);
            auth.submitForm();

            articlePageObject.waitForTitleElement();
            assertEquals("We are not on the same page after login",
                    article_title, articlePageObject.getArticleTitle());
            articlePageObject.addArticlesToMySaved();
            navigationUI.openNavigation();
            navigationUI.clickMyLists();

        }
        myListsPageObject.swipeByArticleToDelete(article_title);
    }
    @Test
    public void testAmountOfNotEmptySearch(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        String search_line = "Linkin park discography";

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(search_line);
        int amount_of_search_result = searchPageObject.getAmountOfFoundArticles();
        Assert.assertTrue(
                "We found too few results",
                amount_of_search_result > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String search_line = "zxcasdqwe";
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.waitForEmptyResultLabel();
        searchPageObject.assertThereIsNoResultOfSearch();
    }
    @Test
    public void testChangeScreenOrientationOnSearchResults(){
        if(Platform.getInstance().isMW()) return;

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        String title_before_rotation = articlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String title_after_rotation = articlePageObject.getArticleTitle();
        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation
        );
        this.rotateScreenPortrait();
        String title_after_second_rotation = articlePageObject.getArticleTitle();
        Assert.assertEquals(
                 "Article title have been changed after screen rotation",
                 title_before_rotation,
                 title_after_second_rotation
        );
    }

    @Test
    public void testCheckScreenArticleBackground(){
        if(Platform.getInstance().isMW()) return;

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(2);
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testSaveFirstArticleToMyListEX5() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        articlePageObject.waitForTitleElement();

        String article_title = articlePageObject.getArticleTitle();

        String name_of_folder = "Articles";
        if(Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyList(name_of_folder);
        }else{
            articlePageObject.addArticlesToMySaved();
        }
        if(Platform.getInstance().isMW()){
            AuthorizationPageObject auth = new AuthorizationPageObject(driver);
            auth.clickAuthButton();
            auth.enterLoginDate(login, password);
            auth.submitForm();

            articlePageObject.waitForTitleElement();
            assertEquals("We are not on the same page after login",
                    article_title, articlePageObject.getArticleTitle());
            articlePageObject.addArticlesToMySaved();
        }
        articlePageObject.closeArticle();

        String name_article = "Romeo and Juliet";
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(name_article);
        searchPageObject.clickByArticleWithSubstring("Tragedy by William Shakespeare");
        articlePageObject.addNextArticleToMyList(name_of_folder);
        articlePageObject.closeArticle();

        navigationUI.clickMyLists();

        myListsPageObject.openFolderByName(name_of_folder);
        myListsPageObject.swipeByArticleToDelete(article_title);
        myListsPageObject.waitForArticleToDisappearByTitle(article_title);
        myListsPageObject.openArticleByName(name_article);

        String name_title = articlePageObject.getArticleTitle();
        Assert.assertEquals("The titles of the articles are not equal",
                name_title,
                name_article);
    }

    @Test
    public void testAssertElementPresentEX6() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        searchPageObject.assertThereIsResultOfSearch();
    }

    @Test
    public void testAssertElementPresentEX9() {
        String search_string = "Java";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(search_string);
        if(Platform.getInstance().isMW()){
            searchPageObject.waitForElementByTitleAndDescription(search_string,"Java","Island in Southeast Asia");
            searchPageObject.waitForElementByTitleAndDescription(search_string,"JavaScript","High-level programming language");
            searchPageObject.waitForElementByTitleAndDescription(search_string,"Java (programming language)","Object-oriented programming language");
            searchPageObject.waitForElementByTitleAndDescription(search_string,"123","Object-oriented programming language");

        }else {
            searchPageObject.waitForElementByTitleAndDescription("Java", "Island in Southeast Asia");
            searchPageObject.waitForElementByTitleAndDescription("JavaScript", "High-level programming language");
            searchPageObject.waitForElementByTitleAndDescription("Java (programming language)", "Object-oriented programming language");
            searchPageObject.waitForElementByTitleAndDescription("123", "Object-oriented programming language");
        }
    }
}
