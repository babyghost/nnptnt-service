package vn.dnict.microservice.nnptnt.kehoach.nhiemvunam.business.view;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import vn.dnict.microservice.nnptnt.kehoach.data.ThongKeKeHoachNamData;

public class MyExcelViewThongKeKeHoachNam extends AbstractXlsView {
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	@SuppressWarnings("unchecked")
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) {

		final Font font10 = workbook.createFont();
		font10.setFontHeightInPoints((short) 10);
		font10.setFontName("Times New Roman");

		final Font font10B = workbook.createFont();
		font10B.setFontHeightInPoints((short) 10);
		font10B.setBold(true);
		font10B.setFontName("Times New Roman");

		final Font font12 = workbook.createFont();
		font12.setFontHeightInPoints((short) 12);
		font12.setFontName("Times New Roman");

		final Font font12B = workbook.createFont();
		font12B.setFontHeightInPoints((short) 12);
		font12B.setBold(true);
		font12B.setFontName("Times New Roman");

		final Font font12I = workbook.createFont();
		font12I.setFontHeightInPoints((short) 12);
		font12I.setItalic(true);
		font12I.setFontName("Times New Roman");

		final Font font13 = workbook.createFont();
		font13.setFontHeightInPoints((short) 13);
		font13.setFontName("Times New Roman");

		final Font fontI13 = workbook.createFont();
		fontI13.setFontHeightInPoints((short) 13);
		fontI13.setItalic(true);
		fontI13.setFontName("Times New Roman");

		final Font fontB13 = workbook.createFont();
		fontB13.setFontHeightInPoints((short) 13);
		fontB13.setBold(true);
		fontB13.setFontName("Times New Roman");

		final Font fontB14 = workbook.createFont();
		fontB14.setFontHeightInPoints((short) 14);
		fontB14.setBold(true);
		fontB14.setFontName("Times New Roman");

		final Font fontBI13 = workbook.createFont();
		fontBI13.setFontHeightInPoints((short) 13);
		fontBI13.setBold(true);
		fontBI13.setItalic(true);
		fontBI13.setFontName("Times New Roman");

		/* define a cell style */

		final CellStyle styleTitle10 = workbook.createCellStyle();
		styleTitle10.setFont(font10);
		styleTitle10.setAlignment(HorizontalAlignment.CENTER);
		styleTitle10.setVerticalAlignment(VerticalAlignment.CENTER);

		final CellStyle styleTitle10B = workbook.createCellStyle();
		styleTitle10B.setBorderBottom(BorderStyle.THIN);
		styleTitle10B.setBorderTop(BorderStyle.THIN);
		styleTitle10B.setBorderRight(BorderStyle.THIN);
		styleTitle10B.setBorderLeft(BorderStyle.THIN);
		styleTitle10B.setAlignment(HorizontalAlignment.CENTER);
		styleTitle10B.setVerticalAlignment(VerticalAlignment.CENTER);
		styleTitle10B.setFont(font10B);
		styleTitle10B.setWrapText(true);

		final CellStyle styleTitle12 = workbook.createCellStyle();
		styleTitle12.setFont(font12);
		styleTitle12.setAlignment(HorizontalAlignment.CENTER);
		styleTitle12.setVerticalAlignment(VerticalAlignment.CENTER);

		final CellStyle styleTitle13 = workbook.createCellStyle();
		styleTitle13.setFont(font13);
		styleTitle13.setAlignment(HorizontalAlignment.CENTER);
		styleTitle13.setVerticalAlignment(VerticalAlignment.CENTER);

		final CellStyle styleTitleB13 = workbook.createCellStyle();
		styleTitleB13.setFont(fontB13);
		styleTitleB13.setAlignment(HorizontalAlignment.CENTER);
		styleTitleB13.setVerticalAlignment(VerticalAlignment.CENTER);

		final CellStyle styleTitleI13 = workbook.createCellStyle();
		styleTitleI13.setFont(fontI13);
		styleTitleI13.setAlignment(HorizontalAlignment.CENTER);
		styleTitleI13.setVerticalAlignment(VerticalAlignment.CENTER);

		final CellStyle styleTitleBI13 = workbook.createCellStyle();
		styleTitleBI13.setFont(fontBI13);
		styleTitleBI13.setAlignment(HorizontalAlignment.CENTER);
		styleTitleBI13.setVerticalAlignment(VerticalAlignment.CENTER);

		final CellStyle styleTitleB14 = workbook.createCellStyle();
		styleTitleB14.setFont(fontB14);
		styleTitleB14.setAlignment(HorizontalAlignment.CENTER);
		styleTitleB14.setVerticalAlignment(VerticalAlignment.CENTER);

		final CellStyle styleHeader = workbook.createCellStyle();
		styleHeader.setBorderBottom(BorderStyle.THIN);
		styleHeader.setBorderTop(BorderStyle.THIN);
		styleHeader.setBorderRight(BorderStyle.THIN);
		styleHeader.setBorderLeft(BorderStyle.THIN);
		styleHeader.setAlignment(HorizontalAlignment.CENTER);
		styleHeader.setVerticalAlignment(VerticalAlignment.CENTER);
		styleHeader.setFont(font12);
		styleHeader.setWrapText(true);

		final CellStyle styleHeaderB = workbook.createCellStyle();
		styleHeaderB.setBorderBottom(BorderStyle.THIN);
		styleHeaderB.setBorderTop(BorderStyle.THIN);
		styleHeaderB.setBorderRight(BorderStyle.THIN);
		styleHeaderB.setBorderLeft(BorderStyle.THIN);
		styleHeaderB.setAlignment(HorizontalAlignment.CENTER);
		styleHeaderB.setVerticalAlignment(VerticalAlignment.CENTER);
		styleHeaderB.setFont(font12B);
		styleHeaderB.setWrapText(true);

		final CellStyle styleCellCenter = workbook.createCellStyle();
		styleCellCenter.setBorderBottom(BorderStyle.THIN);
		styleCellCenter.setBorderTop(BorderStyle.THIN);
		styleCellCenter.setBorderRight(BorderStyle.THIN);
		styleCellCenter.setBorderLeft(BorderStyle.THIN);
		styleCellCenter.setAlignment(HorizontalAlignment.CENTER);
		styleCellCenter.setVerticalAlignment(VerticalAlignment.CENTER);
		styleCellCenter.setFont(font12);
		styleCellCenter.setWrapText(true);

		final CellStyle styleCellLeft = workbook.createCellStyle();
		styleCellLeft.setBorderBottom(BorderStyle.THIN);
		styleCellLeft.setBorderTop(BorderStyle.THIN);
		styleCellLeft.setBorderRight(BorderStyle.THIN);
		styleCellLeft.setBorderLeft(BorderStyle.THIN);
		styleCellLeft.setAlignment(HorizontalAlignment.LEFT);
		styleCellLeft.setVerticalAlignment(VerticalAlignment.CENTER);
		styleCellLeft.setFont(font12);
		styleCellLeft.setWrapText(true);

		final CellStyle styleCellLeftB = workbook.createCellStyle();
		styleCellLeftB.setBorderBottom(BorderStyle.THIN);
		styleCellLeftB.setBorderTop(BorderStyle.THIN);
		styleCellLeftB.setBorderRight(BorderStyle.THIN);
		styleCellLeftB.setBorderLeft(BorderStyle.THIN);
		styleCellLeftB.setAlignment(HorizontalAlignment.LEFT);
		styleCellLeftB.setVerticalAlignment(VerticalAlignment.CENTER);
		styleCellLeftB.setFont(font12B);
		styleCellLeftB.setWrapText(true);

		final CellStyle styleCellLeftI = workbook.createCellStyle();
		styleCellLeftI.setBorderBottom(BorderStyle.THIN);
		styleCellLeftI.setBorderTop(BorderStyle.THIN);
		styleCellLeftI.setBorderRight(BorderStyle.THIN);
		styleCellLeftI.setBorderLeft(BorderStyle.THIN);
		styleCellLeftI.setAlignment(HorizontalAlignment.LEFT);
		styleCellLeftI.setVerticalAlignment(VerticalAlignment.CENTER);
		styleCellLeftI.setFont(font12I);
		styleCellLeftI.setWrapText(true);

		final CellStyle styleCellRight = workbook.createCellStyle();
		styleCellRight.setBorderBottom(BorderStyle.THIN);
		styleCellRight.setBorderTop(BorderStyle.THIN);
		styleCellRight.setBorderRight(BorderStyle.THIN);
		styleCellRight.setBorderLeft(BorderStyle.THIN);
		styleCellRight.setAlignment(HorizontalAlignment.RIGHT);
		styleCellRight.setVerticalAlignment(VerticalAlignment.CENTER);
		styleCellRight.setFont(font12);
		styleCellRight.setWrapText(true);

		final CellStyle styleCellCenterNoBor = workbook.createCellStyle();
		styleCellCenterNoBor.setAlignment(HorizontalAlignment.CENTER);
		styleCellCenterNoBor.setVerticalAlignment(VerticalAlignment.CENTER);
		styleCellCenterNoBor.setFont(font12);
		styleCellCenterNoBor.setWrapText(true);

		final CellStyle styleCellLeftNoBor = workbook.createCellStyle();
		styleCellLeftNoBor.setAlignment(HorizontalAlignment.LEFT);
		styleCellLeftNoBor.setVerticalAlignment(VerticalAlignment.CENTER);
		styleCellLeftNoBor.setFont(font12);
		styleCellLeftNoBor.setWrapText(true);

		final CellStyle styleCellRightNoBor = workbook.createCellStyle();
		styleCellRightNoBor.setAlignment(HorizontalAlignment.RIGHT);
		styleCellRightNoBor.setVerticalAlignment(VerticalAlignment.CENTER);
		styleCellRightNoBor.setFont(font12);
		styleCellRightNoBor.setWrapText(true);

		final CellStyle styleCellCenterNoBorB = workbook.createCellStyle();
		styleCellCenterNoBorB.setAlignment(HorizontalAlignment.CENTER);
		styleCellCenterNoBorB.setVerticalAlignment(VerticalAlignment.CENTER);
		styleCellCenterNoBorB.setFont(font12B);
		styleCellCenterNoBorB.setWrapText(true);

		final CellStyle styleCellLeftNoBorB = workbook.createCellStyle();
		styleCellLeftNoBorB.setAlignment(HorizontalAlignment.LEFT);
		styleCellLeftNoBorB.setVerticalAlignment(VerticalAlignment.CENTER);
		styleCellLeftNoBorB.setFont(font12B);
		styleCellLeftNoBorB.setWrapText(true);

		final CellStyle styleCellRightNoBorB = workbook.createCellStyle();
		styleCellRightNoBorB.setAlignment(HorizontalAlignment.RIGHT);
		styleCellRightNoBorB.setVerticalAlignment(VerticalAlignment.CENTER);
		styleCellRightNoBorB.setFont(font12B);
		styleCellRightNoBorB.setWrapText(true);

		final Sheet sheet = workbook.createSheet("BaoCao");
		// sheet.setDefaultColumnWidth((short) 15);
		sheet.setColumnWidth(0, 10 * 256);
		sheet.setColumnWidth(1, 60 * 256);
		sheet.setColumnWidth(2, 10 * 256);
		sheet.setColumnWidth(3, 20 * 256);
		sheet.setColumnWidth(4, 20 * 256);
		sheet.setColumnWidth(5, 20 * 256);

		// POPULATE Titles COLUMNS
		int currentRow = 2;
		final int col0 = 0;
		short currentColumn;

		Row titleRow = sheet.createRow(currentRow);
		Cell cell = titleRow.createCell(col0);
		cell.setCellStyle(styleTitleB14);
		cell.setCellValue("");
		sheet.addMergedRegion(new CellRangeAddress(currentRow, // first row (0-based)
				currentRow, // last row (0-based)
				0, // first column (0-based)
				5 // last column (0-based)
		));

		currentRow++;
		titleRow = sheet.createRow(currentRow);
		cell = titleRow.createCell(col0);
		cell.setCellStyle(styleTitleB14);
		cell.setCellValue("BÁO CÁO TÌNH HÌNH THỰC HIỆN KẾ HOẠCH NĂM ");
		sheet.addMergedRegion(new CellRangeAddress(currentRow, // first row (0-based)
				currentRow, // last row (0-based)
				0, // first column (0-based)
				5 // last column (0-based)
		));
		List<ThongKeKeHoachNamData> thongKeKeHoachDatas = (List<ThongKeKeHoachNamData>) model
				.get("thongKeKeHoachDatas");

		// lấy dữ liệu
		currentRow++;
		currentRow++;
		Row headerRow = sheet.createRow(currentRow);
		currentColumn = 0;
		Cell cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("STT");
		currentColumn++;

		cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("Nội dung");
		currentColumn++;

		cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("Đơn vị phối hợp");
		currentColumn++;

		cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("Thời gian thực hiện");
		currentColumn++;
		
		cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("Thời gian thực hiện");
	//	sheet.addMergedRegion(new CellRangeAddress(currentRow, currentRow, currentColumn-1 , currentColumn));
		currentColumn++;
		
		cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("Ghi chú");
		currentColumn++;

		cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("Tình trạng thực hiện");
		currentColumn++;

		cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("Mức độ hoàn thành");
		currentColumn++;

		cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("Kết quả thực hiện");
		//currentColumn++;

		currentRow++;
		headerRow = sheet.createRow(currentRow);
		currentColumn = 0;
		cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("STT");
		sheet.addMergedRegion(new CellRangeAddress(currentRow - 1, currentRow, currentColumn, currentColumn));
		currentColumn++;

		cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("Nội dung");
		sheet.addMergedRegion(new CellRangeAddress(currentRow - 1, currentRow, currentColumn, currentColumn));
		currentColumn++;

		cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("Đơn vị phối hợp");
		sheet.addMergedRegion(new CellRangeAddress(currentRow - 1, currentRow, currentColumn, currentColumn));
		currentColumn++;

		cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("Từ ngày");
		currentColumn++;

		cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("Đến ngày");
		currentColumn++;

		cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("Ghi chú");
		sheet.addMergedRegion(new CellRangeAddress(currentRow - 1, currentRow, currentColumn, currentColumn));
		currentColumn++;

		cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("Tình trạng thực hiện");
		sheet.addMergedRegion(new CellRangeAddress(currentRow - 1, currentRow, currentColumn, currentColumn));
		currentColumn++;

		cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("Mức độ hoàn thành");
		sheet.addMergedRegion(new CellRangeAddress(currentRow - 1, currentRow, currentColumn, currentColumn));
		currentColumn++;

		cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("Kết quả thực hiện");
		sheet.addMergedRegion(new CellRangeAddress(currentRow - 1, currentRow, currentColumn, currentColumn));
		currentColumn++;
	
		int stt = 1;
		if (Objects.nonNull(thongKeKeHoachDatas) && !thongKeKeHoachDatas.isEmpty()) {

			for (ThongKeKeHoachNamData thongKeKeHoachData : thongKeKeHoachDatas) {
				currentRow++;
				currentColumn = 0;
				
				headerRow = sheet.createRow(currentRow);
				cell2 = headerRow.createCell(currentColumn);
				cell2.setCellStyle(styleCellCenter);
				cell2.setCellValue(stt);
				currentColumn++;

				cell2 = headerRow.createCell(currentColumn);
				cell2.setCellStyle(styleCellLeft);
				cell2.setCellValue(
						thongKeKeHoachData.getTenNhiemVu() != null ? thongKeKeHoachData.getTenNhiemVu() : "");
				currentColumn++;

				cell2 = headerRow.createCell(currentColumn);
				cell2.setCellStyle(styleCellCenter);
				cell2.setCellValue(
						thongKeKeHoachData.getDonViPhoiHop() != null ? thongKeKeHoachData.getDonViPhoiHop() : "");
				currentColumn++;

				String thoigianTuNgay = "";
				if(Objects.nonNull(thongKeKeHoachData.getTuNgay())) {
					thoigianTuNgay = formatter.format(thongKeKeHoachData.getTuNgay());
				}
				cell2 = headerRow.createCell(currentColumn);
				cell2.setCellStyle(styleCellCenter);
				cell2.setCellValue(thoigianTuNgay);
				currentColumn++;

				String thoigianDenNgay = "";
				if(Objects.nonNull(thongKeKeHoachData.getTuNgay())) {
					thoigianDenNgay = formatter.format(thongKeKeHoachData.getDenNgay());
				}
				cell2 = headerRow.createCell(currentColumn);
				cell2.setCellStyle(styleCellCenter);
				cell2.setCellValue(thoigianDenNgay);
				currentColumn++;

				cell2 = headerRow.createCell(currentColumn);
				cell2.setCellStyle(styleCellCenter);
				cell2.setCellValue(thongKeKeHoachData.getGhiChu());
				currentColumn++;
				String tinhTrangTen="";
				if(thongKeKeHoachData.isTinhTrang()==true) {
					tinhTrangTen="Đã Hoàn Thành" ;
				}else if(thongKeKeHoachData.isTinhTrang()==false) {
					tinhTrangTen="Đang Thực Hiện";
					
				}else {
					tinhTrangTen="Chưa Thực Hiện";
				}
				cell2 = headerRow.createCell(currentColumn);
				cell2.setCellStyle(styleCellCenter);
				cell2.setCellValue(tinhTrangTen);
				currentColumn++;

				cell2 = headerRow.createCell(currentColumn);
				cell2.setCellStyle(styleCellCenter);
				cell2.setCellValue(thongKeKeHoachData.getMucDoHoanThanh());
				currentColumn++;
				
				cell2 = headerRow.createCell(currentColumn);
				cell2.setCellStyle(styleCellCenter);
				cell2.setCellValue(thongKeKeHoachData.getKetQua());
				currentColumn++;
			
				stt++;
			}
			
		}
	}
}
