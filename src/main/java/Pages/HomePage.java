package Pages;


	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;

	import TestUtil.TestUtil;

	public class HomePage {
		
		private WebDriver driver;
		
		public HomePage(WebDriver driver) {
			this.driver = driver;
		}
		
		public WebElement getSignInBtn() {
			//return driver.findElement(By.xpath("//a[contains(text(), \"Sign in\")]"));
			return TestUtil.waitToBeClickable(driver, By.xpath("//a[contains(text(), \"Sign in\")]"), 30);
		}
		
		public WebElement getHomePageSlider() {
			return TestUtil.waitForElementPresence(driver, By.id("homepage-slider"), 30);
		}
		
		public WebElement getContainerPopular() {
			return TestUtil.waitForElementPresence(driver, By.id("homefeatured"), 30);
		}
		
		public WebElement getFooterBaners() {
			return TestUtil.waitForElementPresence(driver, By.id("htmlcontent_home"), 30);
		}	
	}

