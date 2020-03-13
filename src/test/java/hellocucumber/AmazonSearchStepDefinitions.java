package hellocucumber;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static hellocucumber.Hooks.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class AmazonSearchStepDefinitions {

	public static int id = 1;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static File file;

	@Given("I'm on the amazon.com homepage")
	public void i_m_on_the_amazon_com_homepage() {
		driver.get("https://www.amazon.com");
	}

	@When("I search for an {string}")
	public void i_search_for_an(String itemToSearch) {
		WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
		searchBox.clear();
		searchBox.sendKeys(itemToSearch);
		searchBox.submit();
		
		// Opening the file, and creating a temp workbook
		// and create a sheet with the product name
		// and create column name
		try {
			file = new File("target/ExcelFiles/AmazonResearch.xlsx");
			FileInputStream fis = new FileInputStream(file);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.createSheet(itemToSearch + Keywords.getCurrentTimeStamp());
			XSSFRow row = sheet.createRow(0);
			row.createCell(0).setCellValue("ID");
			row.createCell(1).setCellValue("Price");
			row.createCell(2).setCellValue("Title");
			fis.close();
			fis = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}

	@When("I get some results related to my search")
	public void i_get_some_results_related_to_my_search() {
		id = 1;
		while (true) {
			int numberOfResults = driver.findElements(By.xpath("//div[@class='a-section a-spacing-medium']")).size();
			
			for (int i = 1; i <= numberOfResults; i++) {
				String parentXpath = "(//div[@class='a-section a-spacing-medium'])[" + i + "]";
				String labelXpath = parentXpath + "//span[@class='a-size-base-plus a-color-base a-text-normal']";
				String priceXpath = parentXpath + "//span[@class='a-price']/span[2]";
				
				String label;
				String price;
				double price_double;
				try {
					label = driver.findElement(By.xpath(labelXpath)).getText();
					price = driver.findElement(By.xpath(priceXpath)).getText();
					price = price.replace("\n", ".");
					price = price.replace("$", "");
					price_double = Double.valueOf(price);
				} catch (NoSuchElementException e) {
					continue;
				}
				
				// Write a new line in the sheet
				XSSFRow currentRow = sheet.createRow(id);
				currentRow.createCell(0).setCellValue(id);
				currentRow.createCell(1).setCellValue("$" + price_double);
				currentRow.createCell(2).setCellValue(label);
				
				id++;
				if (id > 10) {
					break;
				}
			}	
			
			if (id > 10) {
				break;
			} else {
				driver.findElement(By.xpath("//a[text()='Next']")).click();
			}
		}
		
		try {
			FileOutputStream fos = new FileOutputStream(file);
			workbook.write(fos);
			fos.close();
			fos = null;
			workbook.close();
			workbook = null;
			sheet = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Then("I will be able to find out the highest price, lowest price and average price")
	public void i_will_be_able_to_find_out_the_highest_price_lowest_price_and_average_price() {
		
	}

	@Given("I'm logged in to my account")
	public void i_m_logged_in_to_my_account(io.cucumber.datatable.DataTable dataTable) {
		List<LogInData> data = dataTable.asList(LogInData.class);
		System.out.println("Username: " + data.get(0).username + " Password: " + data.get(0).password);
	}

	@Given("^.*navigateToURL$")
	public void navigateToURL(DataTable dataTable) {
		String url = dataTable.asList().get(0);
		driver.navigate().to(url);
	}
	
}
