package hellocucumber;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadAndWriteExcels {

	public static void main(String[] args) {
		
		try {
			File file = new File("target/ExcelFiles/SampleData.xlsx");
			FileOutputStream fos = new FileOutputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("CheatSheet");
			XSSFRow row = sheet.createRow(0);
			row.createCell(0).setCellValue("ID");
			row.createCell(1).setCellValue("First Name");
			row.createCell(2).setCellValue("Last Name");
			row.createCell(3).setCellValue("Email");
			row.createCell(4).setCellValue("Phone Number");
			
			workbook.write(fos);
			fos.close();
			System.out.println("File Created Successfully");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
