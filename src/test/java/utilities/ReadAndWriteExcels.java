package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.github.javafaker.Faker;

public class ReadAndWriteExcels {

	public static void main(String[] args) {

		try {
			File file = new File("target/ExcelFiles/TestData.xlsx");
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			System.out.println("File Opened Successfully!");

			XSSFSheet sheet = workbook.getSheet("CheatSheet");
			int lastRowNum = sheet.getLastRowNum();
			
			// Adding a new record at the end
//			System.out.println("Last Row Num: " + lastRowNum);
//			System.out.println("Number of rows available: " + (lastRowNum + 1));
//			
//			XSSFRow newRow = sheet.createRow(lastRowNum + 1);
//			newRow.createCell(0).setCellValue(1011);
//			newRow.createCell(1).setCellValue("Murad");
//			newRow.createCell(2).setCellValue("Erkin");
//			newRow.createCell(3).setCellValue("murad.erkin@test.com");
//			newRow.createCell(4).setCellValue("202-202-2000");
			
			// TASK: Locate a guy with first name Nolan
			// Update the last name of the guy to Cucumber
			// Also update the email address accordingly
			String firstNameToLookFor = "Murad";
			String lastNameToReplaceWith = "Aiden";
			for (int i = 1; i <= lastRowNum; i++) {
				XSSFRow currentRow = sheet.getRow(i);
				XSSFCell firstNameCell = currentRow.getCell(1);
				String currentFirstName = firstNameCell.getStringCellValue();
				System.out.println(currentFirstName);
				if (currentFirstName.equalsIgnoreCase(firstNameToLookFor)) {
					System.out.println(firstNameToLookFor + " Found!");
					XSSFCell lastNameCell = currentRow.getCell(2);
					System.out.println(firstNameToLookFor + "'s Current Lastname is " + lastNameCell.getStringCellValue());
					lastNameCell.setCellValue(lastNameToReplaceWith);
					currentRow.getCell(3).setCellValue(firstNameToLookFor + "." + lastNameToReplaceWith + "@gmail.com");
					break;
				}
			}

			FileOutputStream fos = new FileOutputStream(file);
			workbook.write(fos);
			
			fis.close();
			fos.close();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static String generateTestDataToExcel() {
		Faker faker = new Faker();
		String timeStamp = Keywords.getCurrentTimeStamp();
		String fileName = "TestData" + timeStamp + ".xlsx";

		try {
			File file = new File("target/ExcelFiles/" + fileName);
			FileOutputStream fos = new FileOutputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("CheatSheet");
			XSSFRow row = sheet.createRow(0);
			row.createCell(0).setCellValue("ID");
			row.createCell(1).setCellValue("First Name");
			row.createCell(2).setCellValue("Last Name");
			row.createCell(3).setCellValue("Email");
			row.createCell(4).setCellValue("Phone Number");

			for (int i = 1; i <= 10; i++) {
				String firstName = faker.name().firstName();
				String lastName = faker.name().lastName();
				String email = firstName + "." + lastName + "@gmail.com";
				XSSFRow currentRow = sheet.createRow(i);
				currentRow.createCell(0).setCellValue(1000 + i);
				currentRow.createCell(1).setCellValue(firstName);
				currentRow.createCell(2).setCellValue(lastName);
				currentRow.createCell(3).setCellValue(email);
				currentRow.createCell(4).setCellValue(faker.phoneNumber().cellPhone());
			}

			workbook.write(fos);
			fos.close();
			System.out.println("File Created Successfully");
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
