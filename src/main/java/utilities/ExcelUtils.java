package utilities;

import java.io.FileInputStream;

//import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

public class ExcelUtils {
	
	 private static Workbook workbook;

	    public static String getCellValue(String sheetName, int rowNum, int cellNum) {
	        try {
	            FileInputStream fis = new FileInputStream("src/test/resources/testdata.xlsx");
	            workbook = new XSSFWorkbook(fis);
	            Sheet sheet = workbook.getSheet(sheetName);
	            Row row = sheet.getRow(rowNum);
	            Cell cell = row.getCell(cellNum);

	            DataFormatter formatter = new DataFormatter();
	            return formatter.formatCellValue(cell);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }

}}
