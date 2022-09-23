package pageobject;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ShoppingPage {
	
	public WebDriver driver;
	
	By giyimAksesuarKategori = By.xpath("(//*[@title='GİYİM & AKSESUAR'])[1]");
	By dizaltıCorap = By.xpath("//*[text()='Dizaltı Çorap']");
	
	By siyahUrun= By.xpath("//h3[@class='name']");
	
	
	By siyahUrunDogrula= By.xpath("//div[@class='selected-variant-text']/span");
	
	By sepeteEkle= By.className("add-to-basket");
	By sepetiGoruntule = By.linkText("Sepeti Görüntüle");
	By sepetiOnayla = By.className("js-checkout-button");
	By uyeOlmadanIlerle= By.className("js-proceed-to-checkout-btn");
	By email= By.xpath("//input[@type='text' and @name='user_email']");
	By emailDevamEt=By.xpath("/html/body/section/div[1]/div/div[2]/div/div/form/button");
	
	
	By adresOlustur = By.className("js-new-address");
	By adresBaslik = By.xpath("//input[@name='title']");
	By ad = By.xpath("//input[@name='first_name']");
	By soyad= By.xpath("//input[@name='last_name']");
	By telefon = By.xpath("//input[@name='phone_number']");
	By sehir = By.xpath("//select[@name='city']");
	By ilce = By.xpath("//select[@name='township']");
	By mahalle = By.className("js-district");
	By adresTarif = By.xpath("//textarea[@name='line']");
	By kaydetButon = By.xpath("//button[normalize-space()='KAYDET']");
	
	
	By kaydetDevametButon= By.xpath("//*[@class='button block green js-proceed-button']");
	
	By odemeKontrol = By.xpath("//div[@data-type='masterpass' and @data-pk='37']");
	
	
	
	
	public ShoppingPage (WebDriver driver) {
		this.driver=driver;
	}
	
	
	public void GiyimAksesuarUrunBul() {
		
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(giyimAksesuarKategori)).build().perform();
		driver.findElement(dizaltıCorap).click();
	}
	

	
public void SiyahUrun() {
	
	
	List<WebElement>  urunler = driver.findElements(siyahUrun);
	for (int i=0; i<urunler.size();i++) {
		if(urunler.get(i).getText().contains("Siyah")) {
			urunler.get(i).click();
			break;
	}}
	
	Assert.assertEquals(driver.findElement(siyahUrunDogrula).getText(), "SİYAH");

}


public void sepetekleIlerle() {
	
	driver.findElement(sepeteEkle).click();
	driver.findElement(sepetiGoruntule).click();
	driver.findElement(sepetiOnayla).click();
	driver.findElement(uyeOlmadanIlerle).click();
	
	driver.findElement(email).sendKeys("test140@gmail.com");
	driver.findElement(emailDevamEt).click();
}





public void AdresOlustur() throws InterruptedException {
	
	driver.findElement(adresOlustur).click();
	driver.findElement(adresBaslik).sendKeys("Ev");
	driver.findElement(ad).sendKeys("Cıhan");
	driver.findElement(soyad).sendKeys("Mut");
	driver.findElement(telefon).sendKeys("5367234343");
	WebElement city = driver.findElement(sehir);
	WebElement town = driver.findElement(ilce);
	WebElement district = driver.findElement(mahalle);
	Select citydropdown = new Select(city);
	citydropdown.selectByVisibleText("İSTANBUL");
	Select towndropdown = new Select(town);
	towndropdown.selectByVisibleText("AVCILAR");
	Thread.sleep(1000);
	Select districtdropdown = new Select(district);
	districtdropdown.selectByVisibleText("AMBARLI MAH"); 

	Random rand = new Random();
	driver.findElement(adresTarif).sendKeys("Demir sokak no:" + rand.nextInt(1000));
	Thread.sleep(1000);

	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollBy(0,250)", "");
	driver.findElement(kaydetButon).click();
	
}

public void KaydetveDevamEt() {
	
	WebDriverWait wait = new WebDriverWait(driver,30);
	wait.until(ExpectedConditions.visibilityOfElementLocated(kaydetDevametButon)).click();
}


public void OdemeEkranKontrol() {
	
	Assert.assertEquals("Kart ile ödeme", driver.findElement(odemeKontrol).getText());

}


}
