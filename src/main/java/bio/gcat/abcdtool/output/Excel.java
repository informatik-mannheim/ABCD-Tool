package bio.gcat.abcdtool.output;

import bio.gcat.abcdtool.analysis.NPletAnalysis;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Write a table into an excel file. The table here is generated in the Output class
 */
public class Excel {
  List<NPletAnalysis> analyses;
  String[][] table;


  public Excel(String[][] table, List<NPletAnalysis> analyses) {
    this.table = table;
    this.analyses = analyses;
  }


  public void writeFile(String excel) {
    File file = new File(excel);
    file.getParentFile().mkdirs();
    HSSFWorkbook workbook = writeTable();
    FileOutputStream fileOut = null;
    try {
      fileOut = new FileOutputStream(excel);
      workbook.write(fileOut);
      fileOut.close();
      System.out.println("Excel file has been created.");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  private HSSFWorkbook writeTable() {
    HSSFWorkbook workbook = new HSSFWorkbook();
    HSSFSheet sheet = workbook.createSheet("FirstSheet");
    HSSFRow rowhead = sheet.createRow(0);
    for (int i = 0; i < analyses.size(); i++) {
      rowhead.createCell(i).setCellValue(analyses.get(i).getnPletSize());
    }

    for (int i = 0; i < table[0].length; i++) {
      HSSFRow row = sheet.createRow(i + 1);
      for (int j = 0; j < table.length; j++) {
        row.createCell(j).setCellValue(table[j][i]);
      }

    }
    return workbook;
  }
}

