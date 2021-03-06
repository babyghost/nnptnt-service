package vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.business.view;

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
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import vn.dnict.microservice.nnptnt.chomeo.chuquanly.dao.model.ChuQuanLy;
import vn.dnict.microservice.nnptnt.chomeo.data.ChuQuanLyData;
import vn.dnict.microservice.nnptnt.chomeo.data.KeHoach2ChoMeoInput;
import vn.dnict.microservice.nnptnt.chomeo.data.ThongTinChoMeoData;
import vn.dnict.microservice.nnptnt.chomeo.data.ThongTinChoMeoOutput;
import vn.dnict.microservice.nnptnt.chomeo.kehoach2chomeo.dao.model.KeHoach2ChoMeo;
import vn.dnict.microservice.nnptnt.chomeo.thongtinchomeo.dao.model.ThongTinChoMeo;

public class MyExcelViewThongKeChoMeo extends AbstractXlsView {

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@SuppressWarnings("unchecked")
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

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

		Sheet sheet = workbook.createSheet("TK_ChoMeo");
		// sheet.setDefaultColumnWidth((short) 15);
		sheet.setColumnWidth(0, 7 * 256);
		sheet.setColumnWidth(1, 30 * 256);
		sheet.setColumnWidth(2, 20 * 256);
		sheet.setColumnWidth(3, 20 * 256);
		sheet.setColumnWidth(4, 20 * 256);
		sheet.setColumnWidth(5, 20 * 256);
		sheet.setColumnWidth(6, 20 * 256);
		sheet.setColumnWidth(7, 20 * 256);
		sheet.setColumnWidth(8, 20 * 256);
		sheet.setColumnWidth(9, 20 * 256);
		sheet.setColumnWidth(10, 20 * 256);

		int currentRow = 2;
		int col0 = 0;
		short currentColumn = 0;

		Row titleRow = sheet.createRow(currentRow);
		Cell cell = titleRow.createCell(col0);
		cell.setCellStyle(styleTitleB14);
		cell.setCellValue("");
		sheet.addMergedRegion(new CellRangeAddress(currentRow, // first row (0-based)
				currentRow, // last row (0-based)
				0, // first column (0-based)
				9 // last column (0-based)
		));
		currentRow++;
		titleRow = sheet.createRow(currentRow);
		cell = titleRow.createCell(col0);
		cell.setCellStyle(styleTitleB14);
		cell.setCellValue("DANH S??CH CH?? M??O TI??M PH??NG D???I");
		sheet.addMergedRegion(new CellRangeAddress(currentRow, // first row (0-based)
				currentRow, // last row (0-based)
				0, // first column (0-based)
				9 // last column (0-based)
		));

		// l???y d??? li???u
		currentRow++;
		Row headerRow = sheet.createRow(currentRow);
		currentColumn = 0;
		Cell cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("STT");
		currentColumn++;

		cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("Ch??? h???");
		currentColumn++;

		cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("?????a ch???");
		currentColumn++;

		cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("Qu???n huy???n");
		currentColumn++;

		cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("Ph?????ng X??");
		currentColumn++;

		cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("??i???n tho???i");
		currentColumn++;

		cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("Lo???i ?????ng v???t");
		currentColumn++;

		cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("Gi???ng");
		currentColumn++;

		cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("T??n con v???t");
		currentColumn++;

		cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("M??u l??ng");
		currentColumn++;

		cell2 = headerRow.createCell(currentColumn);
		cell2.setCellStyle(styleHeaderB);
		cell2.setCellValue("Th???i gian ti??m ph??ng");
		currentColumn++;

		List<ChuQuanLyData> chuQuanLyDatas = (List<ChuQuanLyData>) model
				.get("chuQuanLyData");
		int stt = 1;
		if (Objects.nonNull(chuQuanLyDatas) && !chuQuanLyDatas.isEmpty()) {

			for (ChuQuanLyData chuQuanLyData : chuQuanLyDatas) {
				List<ThongTinChoMeoData> thongTinChoMeoDatas = chuQuanLyData.getThongTinChoMeoDatas();
				if(Objects.nonNull(thongTinChoMeoDatas) && !thongTinChoMeoDatas.isEmpty()) {
					for (ThongTinChoMeoData thongTinChoMeoData : thongTinChoMeoDatas) {
				
				currentRow++;
				currentColumn = 0;
				System.out.println(thongTinChoMeoData);
				headerRow = sheet.createRow(currentRow);
				cell2 = headerRow.createCell(currentColumn);
				cell2.setCellStyle(styleCellCenter);
				cell2.setCellValue(stt);
				currentColumn++;

				cell2 = headerRow.createCell(currentColumn);
				cell2.setCellStyle(styleCellLeft);
				cell2.setCellValue(
						chuQuanLyData.getChuHo() != null ? chuQuanLyData.getChuHo() : "");
				currentColumn++;

				cell2 = headerRow.createCell(currentColumn);
				cell2.setCellStyle(styleCellCenter);
				cell2.setCellValue(
						chuQuanLyData.getDiaChi() != null ? chuQuanLyData.getDiaChi() : "");
				currentColumn++;

				cell2 = headerRow.createCell(currentColumn);
				cell2.setCellStyle(styleCellCenter);
				cell2.setCellValue(
						chuQuanLyData.getQuanHuyen_Ten() != null ? chuQuanLyData.getQuanHuyen_Ten() : "");
				currentColumn++;

				cell2 = headerRow.createCell(currentColumn);
				cell2.setCellStyle(styleCellCenter);
				cell2.setCellValue(
						chuQuanLyData.getPhuongXa_Ten() != null ? chuQuanLyData.getPhuongXa_Ten() : "");
				currentColumn++;

				cell2 = headerRow.createCell(currentColumn);
				cell2.setCellStyle(styleCellCenter);
				cell2.setCellValue(chuQuanLyData.getDienThoai() != null ?chuQuanLyData.getDienThoai() : "");
				currentColumn++;
				cell2 = headerRow.createCell(currentColumn);
				cell2.setCellStyle(styleCellCenter);
				cell2.setCellValue(
						thongTinChoMeoData.getLoaiDongVat() != null ? thongTinChoMeoData.getLoaiDongVat() : "");
				currentColumn++;

				cell2 = headerRow.createCell(currentColumn);
				cell2.setCellStyle(styleCellCenter);
				cell2.setCellValue(thongTinChoMeoData.getGiong() != null ? thongTinChoMeoData.getGiong() : "");
				currentColumn++;

				cell2 = headerRow.createCell(currentColumn);
				cell2.setCellStyle(styleCellCenter);
				cell2.setCellValue(thongTinChoMeoData.getTenConVat() != null ? thongTinChoMeoData.getTenConVat() : "");
				currentColumn++;

				cell2 = headerRow.createCell(currentColumn);
				cell2.setCellStyle(styleCellCenter);
				cell2.setCellValue(thongTinChoMeoData.getMauLong() != null ? thongTinChoMeoData.getMauLong() : "");
				currentColumn++;
				String thoiGianTiemPhong = "";
				System.out.println(thongTinChoMeoData.getNgayTiemPhong());
				if (Objects.nonNull(thongTinChoMeoData.getNgayTiemPhong())) {
					thoiGianTiemPhong = formatter.format(thongTinChoMeoData.getNgayTiemPhong());

				}
				cell2 = headerRow.createCell(currentColumn);
				cell2.setCellStyle(styleCellLeft);
				cell2.setCellValue(thoiGianTiemPhong);
				currentColumn++;
//								String thoiGianTiemPhong = "";
//								System.out.println(thongTinChoMeoOutput.getNgayTiemPhong());
//								if (Objects.nonNull(thongTinChoMeoOutput.getNgayTiemPhong())) {
//									thoiGianTiemPhong =formatter.format(thongTinChoMeoOutput
//															.getNgayTiemPhong())
//													;
//									
//								}
//								cell2 = headerRow.createCell(currentColumn);
//								cell2.setCellStyle(styleCellLeft);
//								cell2.setCellValue(thoiGianTiemPhong);
//								currentColumn++;

					}
					if (thongTinChoMeoDatas.size() > 1) {
						sheet.addMergedRegion(new CellRangeAddress(
								currentRow - thongTinChoMeoDatas.size() + 1, currentRow, 0, 0));
						sheet.addMergedRegion(new CellRangeAddress(
								currentRow - thongTinChoMeoDatas.size() + 1, currentRow, 1, 1));
						sheet.addMergedRegion(new CellRangeAddress(
								currentRow - thongTinChoMeoDatas.size() + 1, currentRow, 2, 2));
						sheet.addMergedRegion(new CellRangeAddress(
								currentRow - thongTinChoMeoDatas.size() + 1, currentRow, 3, 3));
						sheet.addMergedRegion(new CellRangeAddress(
								currentRow - thongTinChoMeoDatas.size() + 1, currentRow, 4, 4));
						sheet.addMergedRegion(new CellRangeAddress(
								currentRow - thongTinChoMeoDatas.size() + 1, currentRow, 5, 5));
					}

				} else {
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
							chuQuanLyData.getChuHo() != null ? chuQuanLyData.getChuHo() : "");
					currentColumn++;

					cell2 = headerRow.createCell(currentColumn);
					cell2.setCellStyle(styleCellCenter);
					cell2.setCellValue(
							chuQuanLyData.getDiaChi() != null ? chuQuanLyData.getDiaChi() : "");
					currentColumn++;

					cell2 = headerRow.createCell(currentColumn);
					cell2.setCellStyle(styleCellCenter);
					cell2.setCellValue(
							chuQuanLyData.getQuanHuyen_Ten() != null ? chuQuanLyData.getQuanHuyen_Ten() : "");
					currentColumn++;

					cell2 = headerRow.createCell(currentColumn);
					cell2.setCellStyle(styleCellCenter);
					cell2.setCellValue(
							chuQuanLyData.getPhuongXa_Ten() != null ? chuQuanLyData.getPhuongXa_Ten() : "");
					currentColumn++;

					cell2 = headerRow.createCell(currentColumn);
					cell2.setCellStyle(styleCellCenter);
					cell2.setCellValue(chuQuanLyData.getDienThoai() != null ?chuQuanLyData.getDienThoai() : "");
					currentColumn++;
					cell2 = headerRow.createCell(currentColumn);
					cell2.setCellStyle(styleCellLeft);
					cell2.setCellValue("");
					currentColumn++;

					cell2 = headerRow.createCell(currentColumn);
					cell2.setCellStyle(styleCellLeft);
					cell2.setCellValue("");
					currentColumn++;

					cell2 = headerRow.createCell(currentColumn);
					cell2.setCellStyle(styleCellLeft);
					cell2.setCellValue("");
					currentColumn++;

					cell2 = headerRow.createCell(currentColumn);
					cell2.setCellStyle(styleCellLeft);
					cell2.setCellValue("");
					currentColumn++;

					cell2 = headerRow.createCell(currentColumn);
					cell2.setCellStyle(styleCellLeft);
					cell2.setCellValue("");
					currentColumn++;
				}
				stt++;

			}

		}

	}

}