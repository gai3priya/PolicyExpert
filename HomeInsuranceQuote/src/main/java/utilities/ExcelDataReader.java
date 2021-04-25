package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataReader {

	public String[][] getTestData() throws IOException {

		FileInputStream fis = new FileInputStream("./src/main/resources/testdata/HomeInsuranceQuote.xlsx");

		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("sheet1");

		int row = sheet.getLastRowNum();
		int column = sheet.getRow(0).getLastCellNum();

		String[][] data = new String[row][column];

		for (int i = 1; i <= row; i++) {
			for (int j = 0; j < column; j++) {

				String cellvalue = wb.getSheet("sheet1").getRow(i).getCell(j).getStringCellValue();
				data[i - 1][j] = cellvalue;
			}

		}
		wb.close();
		return data;
	}

}
