package vn.dnict.microservice.nnptnt.kehoach.kehoachkhac.business.view;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
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

import lombok.extern.slf4j.Slf4j;
import vn.dnict.microservice.danhmuc.data.DmNguonKinhPhiInput;
import vn.dnict.microservice.nnptnt.kehoach.data.ExcelColumnsData;
import vn.dnict.microservice.nnptnt.kehoach.data.KeHoach2NhiemVuData;
import vn.dnict.microservice.nnptnt.kehoach.data.KeHoachKhacData;
import vn.dnict.microservice.nnptnt.kehoach.data.KinhPhi2ThanhToanKhacData;
import vn.dnict.microservice.nnptnt.kehoach.data.NhiemVu2GiaHanData;
import vn.dnict.microservice.nnptnt.kehoach.data.NhiemVu2KinhPhiData;
import vn.dnict.microservice.nnptnt.kehoach.data.NhiemVu2ThongTinThucHienData;
import vn.dnict.microservice.utils.FunctionUtils;
@Slf4j
public class MyExcelViewKhKeHoachKhacThucHien extends AbstractXlsView {
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static String font = "Times New Roman";
	@Override
	@SuppressWarnings("unchecked")
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) {

		Font font10 = workbook.createFont();
		font10.setFontHeightInPoints((short) 10);
		font10.setFontName(font);

		Font font10B = workbook.createFont();
		font10B.setFontHeightInPoints((short) 10);
		font10B.setBold(true);
		font10B.setFontName(font);

		Font font12 = workbook.createFont();
		font12.setFontHeightInPoints((short) 12);
		font12.setFontName(font);

		Font font12B = workbook.createFont();
		font12B.setFontHeightInPoints((short) 12);
		font12B.setBold(true);
		font12B.setFontName(font);

		Font font13 = workbook.createFont();
		font13.setFontHeightInPoints((short) 13);
		font13.setFontName(font);

		Font fontB13 = workbook.createFont();
		fontB13.setFontHeightInPoints((short) 13);
		fontB13.setBold(true);
		fontB13.setFontName(font);

		Font fontB14 = workbook.createFont();
		fontB14.setFontHeightInPoints((short) 14);
		fontB14.setBold(true);
		fontB14.setFontName(font);

		Font fontBI13 = workbook.createFont();
		fontBI13.setFontHeightInPoints((short) 13);
		fontBI13.setBold(true);
		fontBI13.setItalic(true);
		fontBI13.setFontName(font);

		/* define a cell style */

		CellStyle styleTitle10 = workbook.createCellStyle();
		styleTitle10.setFont(font10);
		styleTitle10.setAlignment(HorizontalAlignment.CENTER);
		styleTitle10.setVerticalAlignment(VerticalAlignment.CENTER);

		CellStyle styleTitle10B = workbook.createCellStyle();
		styleTitle10B.setBorderBottom(BorderStyle.THIN);
		styleTitle10B.setBorderTop(BorderStyle.THIN);
		styleTitle10B.setBorderRight(BorderStyle.THIN);
		styleTitle10B.setBorderLeft(BorderStyle.THIN);
		styleTitle10B.setAlignment(HorizontalAlignment.CENTER);
		styleTitle10B.setVerticalAlignment(VerticalAlignment.CENTER);
		styleTitle10B.setFont(font10B);
		styleTitle10B.setWrapText(true);

		CellStyle styleTitle12 = workbook.createCellStyle();
		styleTitle12.setFont(font12);
		styleTitle12.setAlignment(HorizontalAlignment.CENTER);
		styleTitle12.setVerticalAlignment(VerticalAlignment.CENTER);

		CellStyle styleTitle13 = workbook.createCellStyle();
		styleTitle13.setFont(font13);
		styleTitle13.setAlignment(HorizontalAlignment.CENTER);
		styleTitle13.setVerticalAlignment(VerticalAlignment.CENTER);

		CellStyle styleTitleB13 = workbook.createCellStyle();
		styleTitleB13.setFont(fontB13);
		styleTitleB13.setAlignment(HorizontalAlignment.CENTER);
		styleTitleB13.setVerticalAlignment(VerticalAlignment.CENTER);

		CellStyle styleTitleBI13 = workbook.createCellStyle();
		styleTitleBI13.setFont(fontBI13);
		styleTitleBI13.setAlignment(HorizontalAlignment.CENTER);
		styleTitleBI13.setVerticalAlignment(VerticalAlignment.CENTER);

		CellStyle styleTitleB14 = workbook.createCellStyle();
		styleTitleB14.setFont(fontB14);
		styleTitleB14.setAlignment(HorizontalAlignment.CENTER);
		styleTitleB14.setVerticalAlignment(VerticalAlignment.CENTER);

		CellStyle styleHeader = workbook.createCellStyle();
		styleHeader.setBorderBottom(BorderStyle.THIN);
		styleHeader.setBorderTop(BorderStyle.THIN);
		styleHeader.setBorderRight(BorderStyle.THIN);
		styleHeader.setBorderLeft(BorderStyle.THIN);
		styleHeader.setAlignment(HorizontalAlignment.CENTER);
		styleHeader.setVerticalAlignment(VerticalAlignment.CENTER);
		styleHeader.setFont(font12);
		styleHeader.setWrapText(true);

		CellStyle styleHeaderB = workbook.createCellStyle();
		styleHeaderB.setBorderBottom(BorderStyle.THIN);
		styleHeaderB.setBorderTop(BorderStyle.THIN);
		styleHeaderB.setBorderRight(BorderStyle.THIN);
		styleHeaderB.setBorderLeft(BorderStyle.THIN);
		styleHeaderB.setAlignment(HorizontalAlignment.CENTER);
		styleHeaderB.setVerticalAlignment(VerticalAlignment.CENTER);
		styleHeaderB.setFont(font12B);
		styleHeaderB.setWrapText(true);

		CellStyle styleCellCenter = workbook.createCellStyle();
		styleCellCenter.setBorderBottom(BorderStyle.THIN);
		styleCellCenter.setBorderTop(BorderStyle.THIN);
		styleCellCenter.setBorderRight(BorderStyle.THIN);
		styleCellCenter.setBorderLeft(BorderStyle.THIN);
		styleCellCenter.setAlignment(HorizontalAlignment.CENTER);
		styleCellCenter.setVerticalAlignment(VerticalAlignment.CENTER);
		styleCellCenter.setFont(font12);
		styleCellCenter.setWrapText(true);

		CellStyle styleCellLeft = workbook.createCellStyle();
		styleCellLeft.setBorderBottom(BorderStyle.THIN);
		styleCellLeft.setBorderTop(BorderStyle.THIN);
		styleCellLeft.setBorderRight(BorderStyle.THIN);
		styleCellLeft.setBorderLeft(BorderStyle.THIN);
		styleCellLeft.setAlignment(HorizontalAlignment.LEFT);
		styleCellLeft.setVerticalAlignment(VerticalAlignment.CENTER);
		styleCellLeft.setFont(font12);
		styleCellLeft.setWrapText(true);

		CellStyle styleCellLeftB = workbook.createCellStyle();
		styleCellLeftB.setBorderBottom(BorderStyle.THIN);
		styleCellLeftB.setBorderTop(BorderStyle.THIN);
		styleCellLeftB.setBorderRight(BorderStyle.THIN);
		styleCellLeftB.setBorderLeft(BorderStyle.THIN);
		styleCellLeftB.setAlignment(HorizontalAlignment.LEFT);
		styleCellLeftB.setVerticalAlignment(VerticalAlignment.CENTER);
		styleCellLeftB.setFont(font12B);
		styleCellLeftB.setWrapText(true);

		CellStyle styleCellRight = workbook.createCellStyle();
		styleCellRight.setAlignment(HorizontalAlignment.RIGHT);
		styleCellRight.setVerticalAlignment(VerticalAlignment.CENTER);
		styleCellRight.setFont(font12);
		styleCellRight.setWrapText(true);

		CellStyle styleCellCenterNoBor = workbook.createCellStyle();
		styleCellCenterNoBor.setAlignment(HorizontalAlignment.CENTER);
		styleCellCenterNoBor.setVerticalAlignment(VerticalAlignment.CENTER);
		styleCellCenterNoBor.setFont(font12);
		styleCellCenterNoBor.setWrapText(true);

		CellStyle styleCellLeftNoBor = workbook.createCellStyle();
		styleCellLeftNoBor.setAlignment(HorizontalAlignment.LEFT);
		styleCellLeftNoBor.setVerticalAlignment(VerticalAlignment.CENTER);
		styleCellLeftNoBor.setFont(font12);
		styleCellLeftNoBor.setWrapText(true);

		CellStyle styleCellRightNoBor = workbook.createCellStyle();
		styleCellRightNoBor.setAlignment(HorizontalAlignment.RIGHT);
		styleCellRightNoBor.setVerticalAlignment(VerticalAlignment.CENTER);
		styleCellRightNoBor.setFont(font12);
		styleCellRightNoBor.setWrapText(true);

		CellStyle styleCellCenterNoBorB = workbook.createCellStyle();
		styleCellCenterNoBorB.setAlignment(HorizontalAlignment.CENTER);
		styleCellCenterNoBorB.setVerticalAlignment(VerticalAlignment.CENTER);
		styleCellCenterNoBorB.setFont(font12B);
		styleCellCenterNoBorB.setWrapText(true);

		CellStyle styleCellLeftNoBorB = workbook.createCellStyle();
		styleCellLeftNoBorB.setAlignment(HorizontalAlignment.LEFT);
		styleCellLeftNoBorB.setVerticalAlignment(VerticalAlignment.CENTER);
		styleCellLeftNoBorB.setFont(font12B);
		styleCellLeftNoBorB.setWrapText(true);

		CellStyle styleCellRightNoBorB = workbook.createCellStyle();
		styleCellRightNoBorB.setAlignment(HorizontalAlignment.RIGHT);
		styleCellRightNoBorB.setVerticalAlignment(VerticalAlignment.CENTER);
		styleCellRightNoBorB.setFont(font12B);
		styleCellRightNoBorB.setWrapText(true);

		Sheet sheet = workbook.createSheet("KeHoachThucHien");

		sheet.setColumnWidth(0, 7 * 256);
		sheet.setColumnWidth(1, 60 * 256);
		sheet.setColumnWidth(2, 20 * 256);
		sheet.setColumnWidth(3, 20 * 256);
		sheet.setColumnWidth(4, 20 * 256);
		sheet.setColumnWidth(5, 20 * 256);
		sheet.setColumnWidth(6, 20 * 256);
		sheet.setColumnWidth(7, 20 * 256);
		sheet.setColumnWidth(8, 20 * 256);
		sheet.setColumnWidth(9, 20 * 256);
		sheet.setColumnWidth(10, 20 * 256);
		sheet.setColumnWidth(11, 20 * 256);
		sheet.setColumnWidth(12, 20 * 256);
		sheet.setColumnWidth(13, 20 * 256);
		sheet.setColumnWidth(14, 20 * 256);
		sheet.setColumnWidth(15, 20 * 256);
		sheet.setColumnWidth(16, 20 * 256);
		sheet.setColumnWidth(17, 20 * 256);
		sheet.setColumnWidth(18, 20 * 256);
		sheet.setColumnWidth(19, 20 * 256);
		sheet.setColumnWidth(20, 20 * 256);

		// POPULATE Titles COLUMNS
		int currentRow = 2;
		int col0 = 0;
		short currentColumn;

		KeHoachKhacData keHoachKhacData = (KeHoachKhacData) model.get("keHoachKhacData");
		List<DmNguonKinhPhiInput> dmNguonKinhPhiInputs = (List<DmNguonKinhPhiInput>) model.get("dmNguonKinhPhiInputs");
		int sizeNguonKinhPhi = 2;
		if (dmNguonKinhPhiInputs.size() > 2) {
			sizeNguonKinhPhi = dmNguonKinhPhiInputs.size();
		}
		List<ExcelColumnsData> excelColumnsDatas = (List<ExcelColumnsData>) model.get("excelColumnsDatas");
		List<ExcelColumnsData> filteredList = excelColumnsDatas.stream().filter(item -> "nguonKinhPhi".equals(item.getColumnCode())
				|| "giaTriThanhToan".equals(item.getColumnCode()) || "giaTriConLai".equals(item.getColumnCode())).collect(Collectors.toList());
		Row titleRow = sheet.createRow(currentRow);
		Cell cell = titleRow.createCell(col0);
		cell.setCellStyle(styleTitleB14);
		cell.setCellValue(keHoachKhacData.getTenKeHoach() != null ? keHoachKhacData.getTenKeHoach().toUpperCase() : "");

		sheet.addMergedRegion(
				new CellRangeAddress(currentRow, currentRow, 0, excelColumnsDatas.size() - 1 + (sizeNguonKinhPhi - 1) * filteredList.size()));
		currentRow++;
		titleRow = sheet.createRow(currentRow);
		cell = titleRow.createCell(col0);
		cell.setCellStyle(styleTitleB14);
		String donVi = "";
		String donViThucHienX = (String) model.get("donViThucHien");
		if (Objects.nonNull(donViThucHienX) && !donViThucHienX.isEmpty()) {
			donVi = donViThucHienX;
		}
		cell.setCellValue("Đơn vị: " + donVi.toUpperCase());

		sheet.addMergedRegion(
				new CellRangeAddress(currentRow, currentRow, 0, excelColumnsDatas.size() - 1 + (sizeNguonKinhPhi - 1) * filteredList.size()));
		currentRow++;
		titleRow = sheet.createRow(currentRow);
		cell = titleRow.createCell(col0);
		cell.setCellStyle(styleTitle13);
		cell.setCellValue("(Đính kèm Quyết định số        /QĐ-SCT ngày     /    /       ) của Sở Công Thương thành phố Đà Nẵng");

		sheet.addMergedRegion(
				new CellRangeAddress(currentRow, currentRow, 0, excelColumnsDatas.size() - 1 + (sizeNguonKinhPhi - 1) * filteredList.size()));
		currentRow++;
		titleRow = sheet.createRow(currentRow);
		cell = titleRow.createCell(col0);
		cell.setCellStyle(styleCellRight);
		cell.setCellValue("Đơn vị: Đồng");
		sheet.addMergedRegion(
				new CellRangeAddress(currentRow, currentRow, 0, excelColumnsDatas.size() - 1 + (sizeNguonKinhPhi - 1) * filteredList.size()));

		// lấy dữ liệu
		currentRow++;
		Row headerRow = sheet.createRow(currentRow);
		currentColumn = 0;

		if (Objects.nonNull(excelColumnsDatas) && !excelColumnsDatas.isEmpty()) {
			for (ExcelColumnsData excelColumnsData : excelColumnsDatas) {
				if ("nguonKinhPhi".equals(excelColumnsData.getColumnCode()) || "giaTriThanhToan".equals(excelColumnsData.getColumnCode())
						|| "giaTriConLai".equals(excelColumnsData.getColumnCode())) {
					for (DmNguonKinhPhiInput dmNguonKinhPhiInput : dmNguonKinhPhiInputs) {
						Cell cell2 = headerRow.createCell(currentColumn);
						cell2.setCellStyle(styleHeaderB);
						cell2.setCellValue(excelColumnsData.getColumnName());
						currentColumn++;
					}
					if (dmNguonKinhPhiInputs.size() > 1) {
						sheet.addMergedRegion(
								new CellRangeAddress(currentRow, currentRow, currentColumn - dmNguonKinhPhiInputs.size(), currentColumn - 1));
					}
				} else {
					Cell cell2 = headerRow.createCell(currentColumn);
					cell2.setCellStyle(styleHeaderB);
					cell2.setCellValue(excelColumnsData.getColumnName());
					currentColumn++;
				}
			}

			if (Objects.nonNull(filteredList) && !filteredList.isEmpty()) {
				currentRow++;
				headerRow = sheet.createRow(currentRow);
				currentColumn = 0;
				for (ExcelColumnsData excelColumnsData : excelColumnsDatas) {
					if ("nguonKinhPhi".equals(excelColumnsData.getColumnCode()) || "giaTriThanhToan".equals(excelColumnsData.getColumnCode())
							|| "giaTriConLai".equals(excelColumnsData.getColumnCode())) {
						for (DmNguonKinhPhiInput dmNguonKinhPhiInput : dmNguonKinhPhiInputs) {
							Cell cell2 = headerRow.createCell(currentColumn);
							cell2.setCellStyle(styleHeaderB);
							cell2.setCellValue(dmNguonKinhPhiInput.getTen());
							currentColumn++;
						}
					} else {
						Cell cell2 = headerRow.createCell(currentColumn);
						cell2.setCellStyle(styleHeaderB);
						cell2.setCellValue(excelColumnsData.getColumnName());
						sheet.addMergedRegion(new CellRangeAddress(currentRow - 1, currentRow, currentColumn, currentColumn));
						currentColumn++;
					}
				}
			}
		}

		List<KeHoach2NhiemVuData> keHoach2NhiemVuDatas = keHoachKhacData.getKeHoach2NhiemVuDatas();
		Long loaiNhiemVuId = 0L;
		int stt = 0;
		if (keHoach2NhiemVuDatas != null && !keHoach2NhiemVuDatas.isEmpty()) {
			for (KeHoach2NhiemVuData keHoach2NhiemVuData : keHoach2NhiemVuDatas) {
				List<NhiemVu2KinhPhiData> nhiemVu2KinhPhiDatas = keHoach2NhiemVuData.getNhiemVu2KinhPhiDatas();
				if (Objects.nonNull(keHoach2NhiemVuData.getDmLoaiNhiemVuId())
						&& !loaiNhiemVuId.equals(keHoach2NhiemVuData.getDmLoaiNhiemVuId())) {
					loaiNhiemVuId = keHoach2NhiemVuData.getDmLoaiNhiemVuId();
					stt++;
					currentRow++;
					currentColumn = 0;
					headerRow = sheet.createRow(currentRow);

					if (Objects.nonNull(excelColumnsDatas) && !excelColumnsDatas.isEmpty()) {
						for (ExcelColumnsData excelColumnsData : excelColumnsDatas) {
							if ("nguonKinhPhi".equals(excelColumnsData.getColumnCode())
									|| "giaTriThanhToan".equals(excelColumnsData.getColumnCode())
									|| "giaTriConLai".equals(excelColumnsData.getColumnCode())) {
								for (DmNguonKinhPhiInput dmNguonKinhPhiInput : dmNguonKinhPhiInputs) {
									Cell cell2 = headerRow.createCell(currentColumn);
									cell2.setCellStyle(styleCellCenter);
									cell2.setCellValue("");
									currentColumn++;
								}
							} else {
								Cell cell2 = headerRow.createCell(currentColumn);
								switch (excelColumnsData.getColumnCode()) {
									case "stt" :
										cell2.setCellStyle(styleTitleB13);
										cell2.setCellValue(FunctionUtils.toRoman(stt));
										break;
									case "noiDung" :
										cell2.setCellStyle(styleCellLeftB);
										cell2.setCellValue(keHoach2NhiemVuData.getDmLoaiNhiemVuTen());
										break;
									default :
										cell2.setCellStyle(styleCellCenter);
										cell2.setCellValue("");
										break;
								}
								currentColumn++;
							}
						}
					}
				}

				if (Objects.nonNull(nhiemVu2KinhPhiDatas) && !nhiemVu2KinhPhiDatas.isEmpty()) {
					for (NhiemVu2KinhPhiData element : nhiemVu2KinhPhiDatas) {
						currentRow++;
						currentColumn = 0;
						headerRow = sheet.createRow(currentRow);

						if (Objects.nonNull(excelColumnsDatas) && !excelColumnsDatas.isEmpty()) {
							for (ExcelColumnsData excelColumnsData : excelColumnsDatas) {
								if ("nguonKinhPhi".equals(excelColumnsData.getColumnCode())
										|| "giaTriThanhToan".equals(excelColumnsData.getColumnCode())
										|| "giaTriConLai".equals(excelColumnsData.getColumnCode())) {
									switch (excelColumnsData.getColumnCode()) {
										case "nguonKinhPhi" :
											for (DmNguonKinhPhiInput dmNguonKinhPhiInput : dmNguonKinhPhiInputs) {
												Cell cellContent = headerRow.createCell(currentColumn);
												cellContent.setCellStyle(styleCellCenter);
												Double kinhPhi = nhiemVu2KinhPhiDatas.stream()
														.filter(o -> dmNguonKinhPhiInput.getId().equals(o.getDmNguonKinhPhiId()))
														.filter(o -> o.getSoTien() != null).mapToDouble(o -> Double.parseDouble(o.getSoTien())).sum();
												cellContent.setCellValue(currencyFormat(kinhPhi));
												currentColumn++;
											}
											break;
										case "giaTriThanhToan" :
											for (DmNguonKinhPhiInput dmNguonKinhPhiInput : dmNguonKinhPhiInputs) {
												Cell cellContent = headerRow.createCell(currentColumn);
												cellContent.setCellStyle(styleCellCenter);
												List<NhiemVu2KinhPhiData> kinhPhiDatas = nhiemVu2KinhPhiDatas.stream()
														.filter(o -> dmNguonKinhPhiInput.getId().equals(o.getDmNguonKinhPhiId()))
														.collect(Collectors.toList());
												Double thanhToan = 0D;
												if (Objects.nonNull(kinhPhiDatas) && !kinhPhiDatas.isEmpty()) {
													for (NhiemVu2KinhPhiData kinhPhiData : nhiemVu2KinhPhiDatas) {
														List<KinhPhi2ThanhToanKhacData> kinhPhi2ThanhToanKhacDatas = kinhPhiData
																.getKinhPhi2ThanhToanKhacDatas();
														if (Objects.nonNull(kinhPhi2ThanhToanKhacDatas) && !kinhPhi2ThanhToanKhacDatas.isEmpty()) {
															for (KinhPhi2ThanhToanKhacData kinhPhi2ThanhToanKhacData : kinhPhi2ThanhToanKhacDatas) {
																if (Objects.nonNull(kinhPhi2ThanhToanKhacData.getSoTienThanhToan())
																		&& !kinhPhi2ThanhToanKhacData.getSoTienThanhToan().isEmpty()) {
																	thanhToan += Double.parseDouble(kinhPhi2ThanhToanKhacData.getSoTienThanhToan());
																}
															}
														}
													}
												}
												cellContent.setCellValue(currencyFormat(thanhToan));
												currentColumn++;
											}
											break;
										case "giaTriConLai" :
											for (DmNguonKinhPhiInput dmNguonKinhPhiInput : dmNguonKinhPhiInputs) {
												Cell cellContent = headerRow.createCell(currentColumn);
												cellContent.setCellStyle(styleCellCenter);
												Double kinhPhi = nhiemVu2KinhPhiDatas.stream()
														.filter(o -> dmNguonKinhPhiInput.getId().equals(o.getDmNguonKinhPhiId()))
														.filter(o -> o.getSoTien() != null).mapToDouble(o -> Double.parseDouble(o.getSoTien())).sum();
												double thanhToan = 0D;

												List<NhiemVu2KinhPhiData> kinhPhiDatas = nhiemVu2KinhPhiDatas.stream()
														.filter(o -> dmNguonKinhPhiInput.getId().equals(o.getDmNguonKinhPhiId()))
														.collect(Collectors.toList());
												if (Objects.nonNull(kinhPhiDatas) && !kinhPhiDatas.isEmpty()) {
													for (NhiemVu2KinhPhiData kinhPhiData : nhiemVu2KinhPhiDatas) {
														List<KinhPhi2ThanhToanKhacData> kinhPhi2ThanhToanKhacDatas = kinhPhiData
																.getKinhPhi2ThanhToanKhacDatas();
														if (Objects.nonNull(kinhPhi2ThanhToanKhacDatas) && !kinhPhi2ThanhToanKhacDatas.isEmpty()) {
															for (KinhPhi2ThanhToanKhacData kinhPhi2ThanhToanKhacData : kinhPhi2ThanhToanKhacDatas) {
																if (Objects.nonNull(kinhPhi2ThanhToanKhacData.getSoTienThanhToan())
																		&& !kinhPhi2ThanhToanKhacData.getSoTienThanhToan().isEmpty()) {
																	thanhToan += Double.parseDouble(kinhPhi2ThanhToanKhacData.getSoTienThanhToan());
																}
															}
														}
													}
												}
												Double conLai = kinhPhi - thanhToan;
												if (Boolean.FALSE.equals(keHoach2NhiemVuData.getIsThemMoiThucHien())) {
													cellContent.setCellValue(currencyFormat(conLai));
												}
												currentColumn++;
											}
											break;
										default :
											break;
									}
								} else {
									Cell cellContent = headerRow.createCell(currentColumn);
									switch (excelColumnsData.getColumnCode()) {
										case "stt" :
											cellContent.setCellStyle(styleCellCenter);
											cellContent.setCellValue(keHoach2NhiemVuData.getStt());
											break;
										case "noiDung" :
											cellContent.setCellStyle(styleCellLeft);
											cellContent.setCellValue(keHoach2NhiemVuData.getNoiDung());
											break;
										case "donViThucHien" :
											String donViThucHien = "";
											if (Objects.nonNull(keHoach2NhiemVuData.getDonViThucHienTen())
													&& !keHoach2NhiemVuData.getDonViThucHienTen().isEmpty()) {
												donViThucHien = keHoach2NhiemVuData.getDonViThucHienTen();
											}
											if (Objects.nonNull(keHoach2NhiemVuData.getPhongBanThucHienTen())
													&& !keHoach2NhiemVuData.getPhongBanThucHienTen().isEmpty()) {
												donViThucHien = keHoach2NhiemVuData.getPhongBanThucHienTen();
											}
											cellContent.setCellStyle(styleCellCenter);
											cellContent.setCellValue(donViThucHien);
											break;
										case "donViPhoiHop" :
											StringBuilder donViPhoiHop = new StringBuilder();
											if (Objects.nonNull(keHoach2NhiemVuData.getDonViPhoiHopTens())
													&& !keHoach2NhiemVuData.getDonViPhoiHopTens().isEmpty()) {
												int i = 0;
												for (String donViPhoiHopTen : keHoach2NhiemVuData.getDonViPhoiHopTens()) {
													i++;
													donViPhoiHop.append(donViPhoiHopTen);
													if (i < keHoach2NhiemVuData.getDonViPhoiHopTens().size()) {
														donViPhoiHop.append(", ");
													}
												}
											}
											if (Objects.nonNull(keHoach2NhiemVuData.getPhongBanPhoiHopTens())
													&& !keHoach2NhiemVuData.getPhongBanPhoiHopTens().isEmpty()) {
												int i = 0;
												for (String phongBanPhoiHopTen : keHoach2NhiemVuData.getPhongBanPhoiHopTens()) {
													i++;
													donViPhoiHop.append(phongBanPhoiHopTen);
													if (i < keHoach2NhiemVuData.getPhongBanPhoiHopTens().size()) {
														donViPhoiHop.append(", ");
													}
												}
											}
											cellContent.setCellStyle(styleCellCenter);
											cellContent.setCellValue(donViPhoiHop.toString());
											break;
										case "thoiGianThucHien" :
											String thoiGianThucHien = "";
											if (Objects.nonNull(keHoach2NhiemVuData.getThoiGianThucHienTuNgay())) {
												thoiGianThucHien = keHoach2NhiemVuData.getThoiGianThucHienTuNgay().format(formatter);
											}
											if (Objects.nonNull(keHoach2NhiemVuData.getThoiGianThucHienDenNgay())) {
												if (thoiGianThucHien.isEmpty()) {
													thoiGianThucHien = keHoach2NhiemVuData.getThoiGianThucHienDenNgay().format(formatter);
												} else {
													thoiGianThucHien += " - "
															+ keHoach2NhiemVuData.getThoiGianThucHienDenNgay().format(formatter);
												}

											}
											cellContent.setCellStyle(styleCellCenter);
											cellContent.setCellValue(thoiGianThucHien);
											break;
										case "thoiHanThanhToan" :
											String thoiGianThanhToan = "";
											if (Objects.nonNull(keHoach2NhiemVuData.getThoiGianThanhToan())) {
												thoiGianThanhToan = keHoach2NhiemVuData.getThoiGianThanhToan().format(formatter);
											}
											cellContent.setCellStyle(styleCellCenter);
											cellContent.setCellValue(thoiGianThanhToan);
											break;
										case "ghiChu" :
											cellContent.setCellStyle(styleCellLeft);
											cellContent.setCellValue(keHoach2NhiemVuData.getGhiChu());
											break;
										case "thoiHanHoanThanh" :
											String thoiHanHoanThanh = "";
											if (Objects.nonNull(keHoach2NhiemVuData.getThoiGianThucHienDenNgay())) {
												thoiHanHoanThanh = keHoach2NhiemVuData.getThoiGianThucHienDenNgay().format(formatter);
											}
											cellContent.setCellStyle(styleCellCenter);
											cellContent.setCellValue(thoiHanHoanThanh);
											break;
										case "tienDo" :
											String tienDo = "";
											if (Objects.nonNull(keHoach2NhiemVuData.getNhiemVu2ThongTinThucHienDatas())
													&& !keHoach2NhiemVuData.getNhiemVu2ThongTinThucHienDatas().isEmpty()) {
												tienDo = String.valueOf(keHoach2NhiemVuData.getNhiemVu2ThongTinThucHienDatas()
														.get(keHoach2NhiemVuData.getNhiemVu2ThongTinThucHienDatas().size() - 1).getTienDo());
											}
											cellContent.setCellStyle(styleCellCenter);
											cellContent.setCellValue(tienDo);
											break;
										case "ketQua" :
											String ketQua = "";
											if (Objects.nonNull(keHoach2NhiemVuData.getNhiemVu2ThongTinThucHienDatas())
													&& !keHoach2NhiemVuData.getNhiemVu2ThongTinThucHienDatas().isEmpty()) {
												ketQua = keHoach2NhiemVuData.getNhiemVu2ThongTinThucHienDatas().stream()
														.map(NhiemVu2ThongTinThucHienData::getKetQuaThucHien).collect(Collectors.joining("; "));
											}
											cellContent.setCellStyle(styleCellCenter);
											cellContent.setCellValue(ketQua);
											break;
										case "giaHan" :
											String giaHan = "";
											List<String> giaHans = new ArrayList<>();
											if (Objects.nonNull(keHoach2NhiemVuData.getNhiemVu2GiaHanDatas())
													&& !keHoach2NhiemVuData.getNhiemVu2GiaHanDatas().isEmpty()) {
												int i = 1;
												for (NhiemVu2GiaHanData nhiemVu2GiaHanData : keHoach2NhiemVuData.getNhiemVu2GiaHanDatas()) {
													giaHans.add(nhiemVu2GiaHanData.getNgayHoanThanhLanDau() != null
															? "Lần gia hạn " + i + ": "
																	+ nhiemVu2GiaHanData.getNgayHoanThanhLanDau().format(formatter)
															: "Lần gia hạn " + i + ": ");
													i++;
												}
												giaHan = giaHans.stream().collect(Collectors.joining("; "));
											}
											cellContent.setCellStyle(styleCellCenter);
											cellContent.setCellValue(giaHan);
											break;
										case "ngayHoanThanh" :
											String ngayHoanThanh = "";
											if (CollectionUtils.isNotEmpty(keHoach2NhiemVuData.getNhiemVu2ThongTinThucHienDatas())) {
												List<LocalDate> ngayHoanThanhs = keHoach2NhiemVuData.getNhiemVu2ThongTinThucHienDatas().stream()
														.filter(e -> Objects.nonNull(e.getNgayHoanThanh()))
														.filter(e -> Objects.nonNull(e.getTienDo())).filter(e -> e.getTienDo() == 100)
														.map(NhiemVu2ThongTinThucHienData::getNgayHoanThanh).collect(Collectors.toList());
												if (CollectionUtils.isNotEmpty(ngayHoanThanhs)) {
													ngayHoanThanh = ngayHoanThanhs.get(0).format(formatter);
												}
											}
											cellContent.setCellStyle(styleCellCenter);
											cellContent.setCellValue(ngayHoanThanh);
											break;
										case "tinhTrang" :
											String tinhTrang = "";
											LocalDate localNgayHoanThanh = null;
											if (CollectionUtils.isNotEmpty(keHoach2NhiemVuData.getNhiemVu2ThongTinThucHienDatas())) {
												Optional<LocalDate> optionalNgayHoanThanh = keHoach2NhiemVuData
														.getNhiemVu2ThongTinThucHienDatas().stream().filter(e -> Objects.nonNull(e.getTienDo()))
														.filter(e -> e.getTienDo() == 100)
														.sorted(Comparator.nullsLast(
																(e1, e2) -> e2.getNgayThucHienCapNhat().compareTo(e1.getNgayThucHienCapNhat())))
														.findFirst().map(NhiemVu2ThongTinThucHienData::getNgayHoanThanh);

												if (optionalNgayHoanThanh.isPresent()) {
													localNgayHoanThanh = optionalNgayHoanThanh.get();
												}
											}
											if (CollectionUtils.isNotEmpty(keHoach2NhiemVuData.getNhiemVu2GiaHanDatas())) {
												if (Objects.nonNull(localNgayHoanThanh)
														&& Objects.nonNull(keHoach2NhiemVuData.getThoiGianThucHienDenNgay())) {
													if (localNgayHoanThanh.isBefore(keHoach2NhiemVuData.getThoiGianThucHienDenNgay())) {
														tinhTrang = "Đúng hạn";
													} else {
														tinhTrang = "Trễ hạn";
													}
												}
											} else if (Objects.nonNull(localNgayHoanThanh)
													&& Objects.nonNull(keHoach2NhiemVuData.getThoiGianThucHienDenNgay())) {
												if (localNgayHoanThanh.isBefore(keHoach2NhiemVuData.getThoiGianThucHienDenNgay())) {
													tinhTrang = "Sớm hạn";
												} else if (localNgayHoanThanh.isEqual(keHoach2NhiemVuData.getThoiGianThucHienDenNgay())) {
													tinhTrang = "Đúng hạn";
												} else {
													tinhTrang = "Trễ hạn";
												}
											}
											cellContent.setCellStyle(styleCellCenter);
											cellContent.setCellValue(tinhTrang);
											break;
										default :
											cellContent.setCellStyle(styleCellCenter);
											cellContent.setCellValue("");
											break;
									}
									currentColumn++;
								}
							}
						}
					}
					if (nhiemVu2KinhPhiDatas.size() > 1) {
						for (int i = 0; i < excelColumnsDatas.size() + (sizeNguonKinhPhi - 1) * filteredList.size(); i++) {
							sheet.addMergedRegion(new CellRangeAddress(currentRow - nhiemVu2KinhPhiDatas.size() + 1, currentRow, i, i));
						}
					}
				}
			}
		}
		currentRow++;
		headerRow = sheet.createRow(currentRow);
		currentColumn = 0;
		if (Objects.nonNull(excelColumnsDatas) && !excelColumnsDatas.isEmpty()) {
			for (ExcelColumnsData excelColumnsData : excelColumnsDatas) {
				if ("nguonKinhPhi".equals(excelColumnsData.getColumnCode()) || "giaTriThanhToan".equals(excelColumnsData.getColumnCode())
						|| "giaTriConLai".equals(excelColumnsData.getColumnCode())) {
					Cell cell2 = headerRow.createCell(currentColumn);
					switch (excelColumnsData.getColumnCode()) {
						case "nguonKinhPhi" :
							for (DmNguonKinhPhiInput dmNguonKinhPhiInput : dmNguonKinhPhiInputs) {
								cell2 = headerRow.createCell(currentColumn);
								cell2.setCellStyle(styleCellCenter);
								// cell2.setCellValue(currencyFormat(dmNguonKinhPhiInput.getTongTien()));
								currentColumn++;
							}
							break;
						case "giaTriThanhToan" :
							for (DmNguonKinhPhiInput dmNguonKinhPhiInput : dmNguonKinhPhiInputs) {
								cell2 = headerRow.createCell(currentColumn);
								cell2.setCellStyle(styleCellCenter);
								// cell2.setCellValue(currencyFormat(dmNguonKinhPhiInput.getTongTien()));
								currentColumn++;
							}
							break;
						case "giaTriConLai" :
							for (DmNguonKinhPhiInput dmNguonKinhPhiInput : dmNguonKinhPhiInputs) {
								cell2 = headerRow.createCell(currentColumn);
								cell2.setCellStyle(styleCellCenter);
								// cell2.setCellValue(currencyFormat(dmNguonKinhPhiInput.getTongTien()));
								currentColumn++;
							}
							break;
						default :
							break;
					}
				} else {
					Cell cell2 = headerRow.createCell(currentColumn);
					switch (excelColumnsData.getColumnCode()) {
						case "noiDung" :
							cell2.setCellStyle(styleCellLeftB);
							cell2.setCellValue("TỔNG CỘNG");
							break;
						case "stt" :
						default :
							cell2.setCellStyle(styleCellCenter);
							cell2.setCellValue("");
							break;
					}
					currentColumn++;
				}
			}
		}
	}

	private String currencyFormat(Double curr) {
		try {
			Locale locale = new Locale("vi", "VN");
			DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(locale);
			DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance(locale);
			dfs.setCurrencySymbol("");
			decimalFormat.setDecimalFormatSymbols(dfs);
			return decimalFormat.format(Math.round(curr));
		} catch (NumberFormatException e) {
			// e.printStackTrace();
		}
		return "";
	}
}