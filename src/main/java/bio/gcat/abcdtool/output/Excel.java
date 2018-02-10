package bio.gcat.abcdtool.output;

import bio.gcat.abcdtool.Analysis;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Excel {
    List<Analysis> analyses;
    String[][] table;


    public Excel(String[][] table, List<Analysis> analyses) {
        this.table = table;
        this.analyses = analyses;
    }


    public void writeFile(String excel) {
        File file = new File(excel);
        file.getParentFile().mkdirs();
        HSSFWorkbook workbook= writeTable();
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(excel);
            workbook.write(fileOut);
            fileOut.close();
            System.out.println("Your excel file has been generated!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private HSSFWorkbook writeTable() {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("FirstSheet");
        HSSFRow rowhead = sheet.createRow((short) 0);
        for (int i = 0; i < analyses.size(); i++) {
            rowhead.createCell(i).setCellValue(analyses.get(i).getTupel());
        }

        for (int i = 0; i < table[0].length; i++) {
            HSSFRow row = sheet.createRow((short) i + 1);
            for (int j = 0; j < table.length; j++) {
                row.createCell(j).setCellValue(table[j][i]);
            }

        }
return workbook;

    }
}

