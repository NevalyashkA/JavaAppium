package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.FirstTest;
import tests.GetStartedTest;
@RunWith(Suite.class)
@Suite.SuiteClasses({
        FirstTest.class,
        GetStartedTest.class
})
public class TestSuite {
}
