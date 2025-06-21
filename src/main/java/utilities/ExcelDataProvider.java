package utilities;

import java.util.List;
import java.util.Map;

public class ExcelDataProvider {
	
	 public static Object[][] getLoginData() {
	        String path = "src/test/resources/testdata/Automation_testdata.xlsx";
	        String sheet = "cred";
	        List<Map<String, String>> data = ExcelReader.getExcelData(path, sheet);

	        Object[][] result = new Object[data.size()][1];
	        for (int i = 0; i < data.size(); i++) {
	            result[i][0] = data.get(i);
	        }
	        return result;
	    }

}
