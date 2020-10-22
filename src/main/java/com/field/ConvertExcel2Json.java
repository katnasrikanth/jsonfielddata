package com.field;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ConvertExcel2Json {
	public static void main(String[] args) {
		
		String input_param="Address";
				


		List<Customer> customers = readExcelFile("customers-1.xlsx", input_param);

		String jsonString = convertObjects2JsonString(input_param,customers);

		System.out.println("Customers-->"+ jsonString);
	}
	

	private static List<Customer> readExcelFile(String filePath, String input_param){
		try {
			FileInputStream excelFile = new FileInputStream(new File(filePath));
			Workbook workbook = new XSSFWorkbook(excelFile);

			Sheet sheet = workbook.getSheet("Customers");
			Row rowz=sheet.getRow(0);
			List<Customer> lstCustomers = new ArrayList<Customer>();	
			short lastcolumnused = rowz.getLastCellNum();
			int colnum = 0;
			for (int i = 0; i < lastcolumnused; i++) {
				if (rowz.getCell(i).getStringCellValue().equalsIgnoreCase(input_param)) {
					colnum = i;
					break;
				}
			}

			for (int j=1; j< sheet.getLastRowNum()+1; j++) { //excluding first row(column headings)
				Customer cc= new Customer();
				Row row = sheet.getRow(j);
				Cell cell = row.getCell(colnum);
				//  System.out.println(rowz.getCell(colnum).getStringCellValue()); // getting column header
				switch(cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					cc.setStrValue(cell.getStringCellValue());
					break;
				case Cell.CELL_TYPE_NUMERIC:
					cc.setIntValue((int) cell.getNumericCellValue());
					break;
				}


				lstCustomers.add(cc);

			}
			return lstCustomers;
		} catch (IOException e) {
			throw new RuntimeException("FAIL! -> message = " + e.getMessage());
		}
	}

	private static String convertObjects2JsonString(String input_param, List<Customer> customers) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = "" ;
		try {
			jsonString = mapper.writeValueAsString(customers);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return jsonString; 
	}


}
