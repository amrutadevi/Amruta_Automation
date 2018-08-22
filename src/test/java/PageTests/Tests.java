package PageTests;


	import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.testng.Assert;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.Test;

	import Pages.Account;
	import Pages.Cart;
	import Pages.CartSummary;
	import Pages.Clothes;
	import Pages.ShoppingActions;
	import Pages.SignIn;
import TestBase.TestBase;
	

	public class Tests extends TestBase{

		private WebDriver driver;
		private Actions action;

		private Clothes clothes;
		private Cart cart;
		private ShoppingActions shoppingActions;
		private CartSummary summary;
	    private SignIn signin;
		private Account account;
		public Tests() {
			super();
		}

		public void setup() {
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			driver = new ChromeDriver();

			action = new Actions(driver);

			clothes = new Clothes(driver);
			cart = new Cart(driver);
			shoppingActions = new ShoppingActions(driver);
			signin= new SignIn(driver);
			summary = new CartSummary(driver);
			account = new Account(driver);

			String baseUrl = "http://automationpractice.com/index.php";
			driver.manage().window().maximize();
			driver.get(baseUrl);
			
		}
		
		@AfterClass
		public void closeAll() {
			account.getAccountLogout().click();
			driver.quit();
		}

		@Test(priority = 1)
		public void purchaseitems() {
			AssertJUnit.assertTrue(clothes.getDressesBtn().isDisplayed());

			action.moveToElement(clothes.getDressesBtn()).perform();

			AssertJUnit.assertTrue(clothes.getSummerDressesBtn().isDisplayed());
			AssertJUnit.assertTrue(clothes.getCasualDressesBtn().isDisplayed());
			AssertJUnit.assertTrue(clothes.getEveningDressesBtn().isDisplayed());

			action.moveToElement(clothes.getSummerDressesBtn()).perform();
			clothes.getSummerDressesBtn().click();

			AssertJUnit.assertTrue(clothes.getSummerDressProduct(1).isDisplayed());
			AssertJUnit.assertTrue(clothes.getSummerDressProduct(2).isDisplayed());
			AssertJUnit.assertTrue(clothes.getSummerDressProduct(3).isDisplayed());
			AssertJUnit.assertEquals(clothes.getDressesCount().size(), 2);

			action.moveToElement(clothes.getSummerDressProduct(1)).perform();
			action.moveToElement(shoppingActions.getAddToCartBtn()).perform();

			AssertJUnit.assertTrue(shoppingActions.getAddToCartBtn().isDisplayed());

			action.click(shoppingActions.getAddToCartBtn()).build().perform();
			action.click(shoppingActions.getContinueShopingBtn()).build().perform();

			AssertJUnit.assertTrue(shoppingActions.getContinueShopingBtn().isDisplayed());

			action.moveToElement(cart.getCartTab()).perform();

			AssertJUnit.assertEquals(cart.getCartProductsQty().size(), 1);

			action.moveToElement(clothes.getDressesBtn()).perform();

			AssertJUnit.assertTrue(clothes.getCasualDressesBtn().isDisplayed());

			action.moveToElement(clothes.getCasualDressesBtn()).perform();
			clothes.getCasualDressesBtn().click();
			action.moveToElement(clothes.getCasualDressProduct(1)).perform();
			action.moveToElement(shoppingActions.getAddToCartBtn()).perform();
			action.click(shoppingActions.getAddToCartBtn()).build().perform();

			AssertJUnit.assertTrue(shoppingActions.getAddToCartBtn().isDisplayed());

			action.click(shoppingActions.getContinueShopingBtn()).build().perform();
			action.moveToElement(cart.getCartTab()).perform();

			AssertJUnit.assertEquals(cart.getCartProductsQty().size(), 2);
			AssertJUnit.assertEquals(summary.getCartSummBillingAdressName().getText(), "John Doe");
			AssertJUnit.assertEquals(summary.getCartSummBillingAdressOne().getText(), "Centar");
			AssertJUnit.assertEquals(summary.getCartSummBillingAdressCityState().getText(), "Novi Sad, Connecticut 21000");
			AssertJUnit.assertEquals(summary.getCartSummBillingAdressCountry().getText(), "United States");
			AssertJUnit.assertEquals(summary.getCartSummBillingAdressHomePhone().getText(), "056");
			AssertJUnit.assertEquals(summary.getCartSummBillingAdressMobile().getText(), "066");
			summary.getCartProceedBtnTwo().click();
			summary.getCartProceedBtnTwo().click();

			action.moveToElement(summary.getCartSummTermsOfServiceDialog()).perform();

			AssertJUnit.assertTrue(summary.getCartSummTermsOfServiceDialog().isDisplayed());

			action.moveToElement(summary.getCartSummTermsOfServiceDialogClose()).perform();
			action.click(summary.getCartSummTermsOfServiceDialogClose()).build().perform();

			driver.navigate().refresh();

			summary.getCartSummTermsOfServiceCheck().click();
			summary.getCartProceedBtnTwo().click();
		
			summary.getCartSummPayByBankWire().click();

			AssertJUnit.assertEquals(summary.getCartSummPayByBankWireConfirm().getText(), "BANK-WIRE PAYMENT.");

			summary.getCartSummOtherPaymentMethods().click();
			summary.getCartSummPayByCheck().click();

			AssertJUnit.assertEquals(summary.getCartSummPayByCheckConfirm().getText(), "CHECK PAYMENT");
			summary.getCartSummConfirmOrderBtn().click();

			AssertJUnit.assertTrue(summary.getCartSummSuccessMsg().isDisplayed());
			AssertJUnit.assertEquals(summary.getCartSummSuccessMsg().getText(), "Your order on My Store is complete.");
		
		
		}

		@Test(priority = 3)
		public void changethesizeoftheitem() {
			AssertJUnit.assertEquals(summary.getCartSummTotalProductsPrice().getText(), "$130.96");
			AssertJUnit.assertEquals(summary.getCartSummaryTotalPrice().getText(), "$132.96");
			AssertJUnit.assertEquals(summary.getCartSummTotalShipping().getText(), "$2.00");

			summary.getCartSummQtyPlus(1).click();
			driver.navigate().refresh();

			AssertJUnit.assertEquals(summary.getCartSummTotalProductsPrice().getText(), "$159.94");
			AssertJUnit.assertEquals(summary.getCartSummaryTotalPrice().getText(), "$161.94");
			AssertJUnit.assertEquals(summary.getCartSummTotalShipping().getText(), "$2.00");
		}

		

		

		@Test(priority = 4)
		public void Addthatitemtoyourbasket() {
			AssertJUnit.assertEquals(cart.getCartProductsQty().size(), 2);

			action.moveToElement(clothes.getDressesBtn()).perform();
			action.moveToElement(clothes.getEveningDressesBtn()).perform();
			action.moveToElement(clothes.getEveningDressProduct(1)).perform();
			action.moveToElement(shoppingActions.getAddToCartBtn()).perform();
			action.click(shoppingActions.getAddToCartBtn()).build().perform();
			action.click(shoppingActions.getContinueShopingBtn()).build().perform();

			action.moveToElement(cart.getCartTab()).perform();
			action.moveToElement(cart.getCartProductsQty(1)).perform();

			AssertJUnit.assertEquals(cart.getCartProductsQty(1).getText(), "1");

			action.moveToElement(cart.getCartProductsQty(2)).perform();

			AssertJUnit.assertEquals(cart.getCartProductsQty(2).getText(), "2");

			action.moveToElement(cart.getCartShipingCost()).perform();

			AssertJUnit.assertEquals(cart.getCartShipingCost().getText(), "$2.00");

			action.moveToElement(cart.getCartTotalPrice()).perform();

			AssertJUnit.assertEquals(cart.getCartTotalPrice().getText(), "$132.96");
		}

		@Test(priority = 5)
		public void continueShopping() {
			action.moveToElement(cart.getCartTab()).perform();
			action.moveToElement(cart.getCartTabCheckOutBtn()).perform();

			AssertJUnit.assertTrue(cart.getCartTabCheckOutBtn().isDisplayed());

			action.click(cart.getCartTabCheckOutBtn()).build().perform();
			;

			AssertJUnit.assertTrue(summary.getCartSummaryTable().isDisplayed());
			AssertJUnit.assertEquals(summary.getCartSummTotalProductsNum().size(), 2);
			AssertJUnit.assertEquals(summary.getCartSummTotalProductsPrice().getText(), "$130.96");
			AssertJUnit.assertEquals(summary.getCartSummaryTotalPrice().getText(), "$132.96");
			AssertJUnit.assertEquals(summary.getCartSummTotalShipping().getText(), "$2.00");
			AssertJUnit.assertTrue(summary.getCartSummQtyPlus(1).isDisplayed());
			AssertJUnit.assertTrue(summary.getCartSummQtyPlus(1).isDisplayed());
			AssertJUnit.assertTrue(summary.getCartSummQtyMinus(1).isDisplayed());
			AssertJUnit.assertTrue(summary.getCartSummQtyMinus(1).isDisplayed());
			AssertJUnit.assertTrue(summary.getCartSummQtyInput(1).isDisplayed());
			AssertJUnit.assertTrue(summary.getCartSummQtyInput(1).isDisplayed());
			AssertJUnit.assertTrue(summary.getCartSummQtyPlus(2).isDisplayed());
			AssertJUnit.assertTrue(summary.getCartSummQtyPlus(2).isDisplayed());
			AssertJUnit.assertTrue(summary.getCartSummQtyMinus(2).isDisplayed());
			AssertJUnit.assertTrue(summary.getCartSummQtyMinus(2).isDisplayed());
			AssertJUnit.assertTrue(summary.getCartSummQtyInput(2).isDisplayed());
			AssertJUnit.assertTrue(summary.getCartSummQtyInput(2).isDisplayed());
			AssertJUnit.assertTrue(summary.getCartSummDeleteBtn(1).isDisplayed());
			AssertJUnit.assertTrue(summary.getCartSummDeleteBtn(2).isDisplayed());
		}
			

		@Test(priority = 8)
		public void checkIsOrderVisibleInOrderHistorySection() {
			account.getAccountBtn().click();

			AssertJUnit.assertTrue(account.getAccountOrderHistoryBtn().isDisplayed());

			account.getAccountOrderHistoryBtn().click();

			AssertJUnit.assertTrue(account.getAccountOrderListTable().isDisplayed());

			account.getAccountBtn().click();
			account.getAccountOrderHistoryBtn().click();

			AssertJUnit.assertEquals(account.getAccountOrdersLis().size(), 1);
			action.moveToElement(clothes.getSummerDressesBtn()).perform();
			clothes.getSummerDressesBtn().click();

			AssertJUnit.assertTrue(clothes.getSummerDressProduct(1).isDisplayed());
			AssertJUnit.assertTrue(clothes.getSummerDressProduct(2).isDisplayed());
			AssertJUnit.assertTrue(clothes.getSummerDressProduct(3).isDisplayed());
			AssertJUnit.assertEquals(clothes.getDressesCount().size(), 2);

			action.moveToElement(clothes.getSummerDressProduct(1)).perform();
			action.moveToElement(shoppingActions.getAddToCartBtn()).perform();

			AssertJUnit.assertTrue(shoppingActions.getAddToCartBtn().isDisplayed());

			action.click(shoppingActions.getAddToCartBtn()).build().perform();
			action.click(shoppingActions.getContinueShopingBtn()).build().perform();

			AssertJUnit.assertTrue(shoppingActions.getContinueShopingBtn().isDisplayed());

			action.moveToElement(cart.getCartTab()).perform();

			AssertJUnit.assertEquals(cart.getCartProductsQty().size(), 1);

			action.moveToElement(clothes.getDressesBtn()).perform();

			AssertJUnit.assertTrue(clothes.getCasualDressesBtn().isDisplayed());

			action.moveToElement(clothes.getCasualDressesBtn()).perform();
			clothes.getCasualDressesBtn().click();
			action.moveToElement(clothes.getCasualDressProduct(1)).perform();
			action.moveToElement(shoppingActions.getAddToCartBtn()).perform();
			action.click(shoppingActions.getAddToCartBtn()).build().perform();

			AssertJUnit.assertTrue(shoppingActions.getAddToCartBtn().isDisplayed());

			action.click(shoppingActions.getContinueShopingBtn()).build().perform();
		}
}
