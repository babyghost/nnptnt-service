package vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.business.view;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MauExcelThongTinChoMeoExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	
	public MauExcelThongTinChoMeoExporter() {
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Import thông tin chó mèo");
	}
	private void writeHeaderThongTinChoMeoRow() {
		Row row = sheet.createRow(0);
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(10);
		font.setFontName("Arial");
		style.setFont(font);
		style.setBorderBottom(BorderStyle.THIN); 
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN); 
		style.setBorderLeft(BorderStyle.THIN); 
		style.setAlignment(HorizontalAlignment.CENTER);
		
		Cell cell = row.createCell(0);
	    row.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));
		cell.setCellValue(" Stt ");
		sheet.setColumnWidth(0, 1200);
		cell.setCellStyle(style);
		CellUtil.setCellStyleProperty(cell, CellUtil.VERTICAL_ALIGNMENT, VerticalAlignment.CENTER); 
		
		cell = row.createCell(1);
		sheet.setColumnWidth(1, 6000);
		cell.setCellValue("Tên chủ hộ");
		cell.setCellStyle(style);
		CellUtil.setCellStyleProperty(cell, CellUtil.VERTICAL_ALIGNMENT, VerticalAlignment.CENTER); 
		
		cell = row.createCell(2);
//		sheet.autoSizeColumn(2);
		sheet.setColumnWidth(2, 6000);
		cell.setCellValue("Địa chỉ");
		cell.setCellStyle(style);
		CellUtil.setCellStyleProperty(cell, CellUtil.VERTICAL_ALIGNMENT, VerticalAlignment.CENTER); 
		
		cell = row.createCell(3);
//		sheet.autoSizeColumn(3);
		sheet.setColumnWidth(3, 6000);
		cell.setCellValue("Quận huyện");
		cell.setCellStyle(style);
		CellUtil.setCellStyleProperty(cell, CellUtil.VERTICAL_ALIGNMENT, VerticalAlignment.CENTER); 
		
		cell = row.createCell(4);
		sheet.setColumnWidth(4, 6000);
		cell.setCellValue("Phường xã");
		cell.setCellStyle(style);
		CellUtil.setCellStyleProperty(cell, CellUtil.VERTICAL_ALIGNMENT, VerticalAlignment.CENTER); 
		
		cell = row.createCell(5);
		sheet.setColumnWidth(5, 6000);
		cell.setCellValue("Điện thoại");
		cell.setCellStyle(style);
		CellUtil.setCellStyleProperty(cell, CellUtil.VERTICAL_ALIGNMENT, VerticalAlignment.CENTER); 
		
		cell = row.createCell(6);
		sheet.setColumnWidth(6, 6000);
		cell.setCellValue("Loại động vật");
		cell.setCellStyle(style);
		CellUtil.setCellStyleProperty(cell, CellUtil.VERTICAL_ALIGNMENT, VerticalAlignment.CENTER); 
		
		cell = row.createCell(7);
		sheet.setColumnWidth(7, 6000);
		cell.setCellValue("Giống");
		cell.setCellStyle(style);
		CellUtil.setCellStyleProperty(cell, CellUtil.VERTICAL_ALIGNMENT, VerticalAlignment.CENTER);
		
		cell = row.createCell(8);
		sheet.setColumnWidth(8, 6000);
		cell.setCellValue("Tên con vật");
		cell.setCellStyle(style);
		CellUtil.setCellStyleProperty(cell, CellUtil.VERTICAL_ALIGNMENT, VerticalAlignment.CENTER);
		

		cell = row.createCell(9);
		sheet.setColumnWidth(9, 6000);
		cell.setCellValue("Năm sinh");
		cell.setCellStyle(style);
		CellUtil.setCellStyleProperty(cell, CellUtil.VERTICAL_ALIGNMENT, VerticalAlignment.CENTER);
		
		cell = row.createCell(10);
		sheet.setColumnWidth(10, 6000);
		cell.setCellValue("Màu lông");
		cell.setCellStyle(style);
		CellUtil.setCellStyleProperty(cell, CellUtil.VERTICAL_ALIGNMENT, VerticalAlignment.CENTER);
		
		cell = row.createCell(11);
		sheet.setColumnWidth(11, 6000);
		cell.setCellValue("Tính biệt");
		cell.setCellStyle(style);
		CellUtil.setCellStyleProperty(cell, CellUtil.VERTICAL_ALIGNMENT, VerticalAlignment.CENTER);
		
		cell = row.createCell(12);
		sheet.setColumnWidth(12, 6000);
		cell.setCellValue("Trạng thái");
		cell.setCellStyle(style);
		CellUtil.setCellStyleProperty(cell, CellUtil.VERTICAL_ALIGNMENT, VerticalAlignment.CENTER);
		
		 
	}
	private void writeDataRow() {
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(false);
		font.setFontHeight(8);
		font.setFontName("Arial");
		style.setFont(font);
		style.setBorderBottom(BorderStyle.THIN); 
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);  
		style.setBorderLeft(BorderStyle.THIN);
		style.setAlignment(HorizontalAlignment.CENTER);
		int i;
		int rowCount = 1;
		for(i = 0; i < 3; i++ ) {
			Row row = sheet.createRow(rowCount);
			Cell cell = row.createCell(0);
			cell.setCellValue("");
			cell.setCellStyle(style);
			
			cell = row.createCell(1);
			cell.setCellValue("");
			cell.setCellStyle(style);
			
			cell = row.createCell(2);
			cell.setCellValue("");
			cell.setCellStyle(style);
			
			cell = row.createCell(2);
			cell.setCellValue("");
			cell.setCellStyle(style);
			
			cell = row.createCell(3);
			cell.setCellValue("");
			cell.setCellStyle(style);
			
			cell = row.createCell(4);
			cell.setCellValue("");
			cell.setCellStyle(style);
			
			cell = row.createCell(5);
			cell.setCellValue("");
			cell.setCellStyle(style);
			
			cell = row.createCell(6);
			cell.setCellValue("");
			cell.setCellStyle(style);
			
			cell = row.createCell(7);
			cell.setCellValue("");
			cell.setCellStyle(style);
		
			cell = row.createCell(8);
			cell.setCellValue("");
			cell.setCellStyle(style);
			
			cell = row.createCell(9);
			cell.setCellValue("");
			cell.setCellStyle(style);
			
			cell = row.createCell(10);
			cell.setCellValue("");
			cell.setCellStyle(style);
			
			cell = row.createCell(11);
			cell.setCellValue("");
			cell.setCellStyle(style);
			
			cell = row.createCell(12);
			cell.setCellValue("");
			cell.setCellStyle(style);
			
			
			
			rowCount++;
		}
	}
	public void export(HttpServletResponse response) throws IOException {
		writeHeaderThongTinChoMeoRow();
		writeDataRow();
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
}
