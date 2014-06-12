import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.HTMLUNIT;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import play.libs.F.Callback;
import play.test.TestBrowser;
import repository.OfferRepository;

public class IntegrationTest {

    /**
     * add your integration test here
     * in this example we just check if the welcome page is being shown
     */
//    @Test
//    public void test() {
//        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
//            public void invoke(TestBrowser browser) {
//                browser.goTo("http://localhost:3333");
//                assertThat(browser.pageSource()).contains("Your new application is ready.");
//            }
//        });
//    }

	
	
	
	
//	  WebDriver wd = new FirefoxDriver();
//	  TestBrowser browser2 = new TestBrowser(wd, "");
//  @Test
//  public void test() {
//	  
//	  
//      running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
//          public void invoke(TestBrowser browser) {
//              browser.goTo("http://localhost:3333");
//              assertThat(browser.pageSource()).contains("Pricing");
//          }
//      });
//  }
	
//	@Test
//	public void test() {
//		assertThat(OfferRepository.test()==1);
//	}
}
