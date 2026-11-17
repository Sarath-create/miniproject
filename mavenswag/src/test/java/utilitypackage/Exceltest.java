package utilitypackage;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Exceltest {

    public static Object[][] getTestData(String path, String sheetName)
            throws Exception {

        FileInputStream fis = new FileInputStream(path);
        Workbook workbook = WorkbookFactory.create(fis);

        Sheet sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            workbook.close();
            fis.close();
            throw new Exception("Sheet not found: " + sheetName);
        }

        DataFormatter formatter = new DataFormatter();

        List<Object[]> data = new ArrayList<>();

        
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);

            if (row == null) {
                continue;
            }

            String username = formatter.formatCellValue(
                    row.getCell(0)).trim();

            String password = formatter.formatCellValue(
                    row.getCell(1)).trim();

            
            if (username.isEmpty() && password.isEmpty()) {
                continue;
            }

            data.add(new Object[] { username, password });
        }

        workbook.close();
        fis.close();

        return data.toArray(new Object[0][]);
    }
}