package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	 public static List<Map<String, String>> getExcelData(String filePath, String sheetName) {
	        List<Map<String, String>> data = new ArrayList<>();
	        try (FileInputStream fis = new FileInputStream(filePath); 
	             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
	            Sheet sheet = workbook.getSheet(sheetName);
	            Row headerRow = sheet.getRow(0);

	            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	                Row row = sheet.getRow(i);
	                Map<String, String> rowData = new HashMap<>();
	                for (int j = 0; j < row.getLastCellNum(); j++) {
	                    rowData.put(headerRow.getCell(j).getStringCellValue(),
	                                row.getCell(j).toString());
	                }
	                data.add(rowData);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return data;
	    }
}
