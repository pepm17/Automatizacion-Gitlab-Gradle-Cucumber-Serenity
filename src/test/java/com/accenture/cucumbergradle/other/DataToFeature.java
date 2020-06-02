package com.accenture.cucumbergradle.other;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 * <<<<<<< HEAD Ingresa los datos obtenidos del archivo de Excel al archivo
 * feature del cual se está llamando. ======= Ingresa los datos obtenidos del
 * archivo de Excel al archivo feature del cual se est� llamando. >>>>>>>
 * 0bd089af367c4b2217b8ad796e6d959ac2949f45
 */
public final class DataToFeature {

  private DataToFeature() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Ingresa los datos obtenidos de un excel al archivo .feature del cual se está
   * llamando, hace que se genere la tabla en el escenario. Outline como Data
   * Table
   * 
   * @param featureFile Nombre del archivo .feature el cual se modificará, debe
   *                    tener la ruta del archivo y la hoja a ser usada.
   * @return
   * @throws InvalidFormatException
   * @throws IOException
   */

  private static List<String> setExcelDataToFeature2(File featureFile) throws InvalidFormatException, IOException {
    List<String> fileData = new ArrayList<>();
    try (BufferedReader buffReader = new BufferedReader(
      new InputStreamReader(new BufferedInputStream(new FileInputStream(featureFile)), StandardCharsets.UTF_8))) {
      String data;
      List<Map<String, String>> excelData = null;
      boolean foundHashTag = false;
      boolean featureData = false;
      while ((data = buffReader.readLine()) != null) {
        String[] dataVector = null;
        String sheetName = null;
        String excelFilePath = null;
        int filaSeleccionada = 0;
        System.out.println("Holi");
        if (data.trim().contains("##@externaldata")) {
        	System.out.println("Holi2");
          dataVector = data.trim().split("@");
          excelFilePath = dataVector[Constants.DATA_2];
          sheetName = dataVector[Constants.DATA_3];
          foundHashTag = true;
          fileData.add(data);
        }
        if (foundHashTag) {
          excelData = new LectorExcel().getData(excelFilePath, sheetName);

          for (int rowNumber = filaSeleccionada; rowNumber < excelData.size() - 1; rowNumber++) {
            StringBuilder cellData = new StringBuilder("");
            for (Entry<String, String> mapData : excelData.get(rowNumber).entrySet()) {
              cellData.append("   |");
              cellData.append(mapData.getValue());
            }
            String cellDataStr = cellData.toString();
            fileData.add(cellDataStr + "|");
            rowNumber = excelData.size();
          }
          foundHashTag = false;
          featureData = true;
          continue;
        }
        if (data.startsWith("|") || data.endsWith("|")) {
          if (featureData) {
            continue;
          }
          else {
            fileData.add(data);
            continue;
          }
        }
        else {
          featureData = false;
        }
        fileData.add(data);
      }
    }
    return fileData;
  }

  /**
   * Lista de todos los features con sus respectivos archivo de excel que se
   * usarán en la prueba.
   * 
   * @param folder Carpeta donde estarán los archivo .feature
   */
  private static List<File> listOfFeatureFiles(File folder) {
    List<File> featureFiles = new ArrayList<>();
    if (folder.getName().endsWith(".feature")) {
      featureFiles.add(folder);
    }
    else {

      for (File fileEntry : folder.listFiles()) {
        if (fileEntry.isDirectory()) {
          featureFiles.addAll(listOfFeatureFiles(fileEntry));
        }
        else {
          if (fileEntry.isFile() && fileEntry.getName().endsWith(".feature")) {
            featureFiles.add(fileEntry);
          }
        }
      }
    }
    return featureFiles;
  }

  /**
   * Hace una lista con todos los features dependiendo de la ruta asignada
   * 
   * @param featuresDirectoryPath Ruta donde se encuentran los features que
   *                              tendrán las tablas
   * @throws IOException
   * @throws InvalidFormatException
   */
  public static void overrideFeatureFiles(String featuresDirectoryPath)
    // public void overrideFeatureFiles(String featuresDirectoryPath)
    throws IOException, InvalidFormatException {
	  System.out.println("Holi3");
    List<File> listOfFeatureFiles = listOfFeatureFiles(new File(featuresDirectoryPath));
    for (File featureFile : listOfFeatureFiles) {
      List<String> featureWithExcelData = setExcelDataToFeature2(featureFile);
      try (BufferedWriter writer = new BufferedWriter(
        new OutputStreamWriter(new FileOutputStream(featureFile), StandardCharsets.UTF_8));) {
        for (String string : featureWithExcelData) {
          writer.write(string);
          writer.write("\n");
        }
      }
    }
  }
}
