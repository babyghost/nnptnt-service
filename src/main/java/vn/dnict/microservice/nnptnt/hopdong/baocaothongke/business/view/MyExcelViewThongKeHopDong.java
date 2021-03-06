package vn.dnict.microservice.nnptnt.hopdong.baocaothongke.business.view;

import java.time.LocalDate;
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

import vn.dnict.microservice.nnptnt.hopdong.baocaothongke.dao.model.BaoCaoThongKe;
import vn.dnict.microservice.nnptnt.ocop.data.DoanhNghiepData;

public class MyExcelViewThongKeHopDong  extends AbstractXlsView{

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
		sheet.setColumnWidth(1, 30 * 256);
		sheet.setColumnWidth(2, 30 * 256);
		sheet.setColumnWidth(3, 20 * 256);
		sheet.setColumnWidth(4, 20 * 256);
		sheet.setColumnWidth(5, 30 * 256);
		sheet.setColumnWidth(6, 30 * 256);
		sheet.setColumnWidth(7, 30 * 256);
		sheet.setColumnWidth(8, 30 * 256);
		sheet.setColumnWidth(9, 20 * 256);
		sheet.setColumnWidth(10, 20 * 256);
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
		LocalDate localDate = LocalDate.now();// For reference
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String formattedString = localDate.format(formatter);
		currentRow++;
		titleRow = sheet.createRow(currentRow);
		cell = titleRow.createCell(col0);
		cell.setCellStyle(styleTitleB14);
		cell.setCellValue("B??O C??O TH??NG TIN H???P ?????NG DOANH NGHI???P NG??Y " + formattedString);
		sheet.addMergedRegion(new CellRangeAddress(currentRow, // first row (0-based)
				currentRow, // last row (0-based)
				0, // first column (0-based)
				10 // last column (0-based)
		));

		// l???y d??? li???u
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
		cell2.setCellValue("T??n H???p ?????ng");
		currentColumn++;

		cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("Lo???i H???p ?????ng" );
		currentColumn++;

		cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("?????t Thanh To??n");
		currentColumn++;

		cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("Ng??y Thanh To??n ");
		currentColumn++;

		cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("????n V??? Th???c Hi???n ");
		currentColumn++;


		cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("C?? Nh??n Th???c Hi???n ");
		currentColumn++;

		cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("Tr???ng Th??i ");
		currentColumn++;
		List<BaoCaoThongKe> baoCaoThongKeDatas = (List<BaoCaoThongKe>) model
				.get("BaoCaoThongKeDatas");
		int stt = 1;
		if (Objects.nonNull(baoCaoThongKeDatas) && !baoCaoThongKeDatas.isEmpty()) {

			for (BaoCaoThongKe baoCaoThongKe : baoCaoThongKeDatas) {
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
						baoCaoThongKe.getTenHopDong() != null ? baoCaoThongKe.getTenHopDong() : "");
				currentColumn++;

				cell2 = headerRow.createCell(currentColumn);
				cell2.setCellStyle(styleCellCenter);
				cell2.setCellValue(
						baoCaoThongKe.getTenLoaiHopDong() != null ? baoCaoThongKe.getTenLoaiHopDong() : "");
				currentColumn++;

				cell2 = headerRow.createCell(currentColumn);
				cell2.setCellStyle(styleCellCenter);
				cell2.setCellValue(
						baoCaoThongKe.getThanhToanDot());
				currentColumn++;

				String ngayThanhToan = "";

				if (Objects.nonNull(baoCaoThongKe.getThanhToanNgay())) {
					ngayThanhToan = formatter.format(baoCaoThongKe.getThanhToanNgay());

				}
				cell2 = headerRow.createCell(currentColumn);
				cell2.setCellStyle(styleCellLeft);
				cell2.setCellValue(ngayThanhToan);
				currentColumn++;

				cell2 = headerRow.createCell(currentColumn);
				cell2.setCellStyle(styleCellCenter);
				cell2.setCellValue(baoCaoThongKe.getDvthTen() != null ? baoCaoThongKe.getDvthTen()  : "");
				currentColumn++;
				cell2 = headerRow.createCell(currentColumn);
				cell2.setCellStyle(styleCellCenter);
				cell2.setCellValue(
						baoCaoThongKe.getCnthTen()  != null ?baoCaoThongKe.getCnthTen()   : "");
				currentColumn++;

				cell2 = headerRow.createCell(currentColumn);
				cell2.setCellStyle(styleCellCenter);
				cell2.setCellValue(baoCaoThongKe.getTenTrangThai()  != null ? baoCaoThongKe.getTenTrangThai()  : "");
				currentColumn++;
	
				stt++;
				
			}
		}
	}
}
