package vn.dnict.microservice.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

public class FunctionUtils {

	private static final Pattern REMOVE_TAGS = Pattern.compile("<.+?>");

	public static <KEY, VALUE> void put(Map<KEY, List<VALUE>> map, KEY key, VALUE value) {
		map.compute(key, (s, strings) -> strings == null ? new ArrayList<>() : strings).add(value);
	}

	public static String removeTags(String string) {
		if (string == null || string.length() == 0) {
			return string;
		}

		Matcher m = REMOVE_TAGS.matcher(string);
		return m.replaceAll("");
	}

	public static Optional<Integer> tryParseInteger(String string) {
		try {
			return Optional.of(Integer.valueOf(string));
		} catch (NumberFormatException e) {
			return Optional.empty();
		}
	}

	public static String getCellValueAsString(Cell cell) {
		String strCellValue = "";
		if (cell != null) {
			switch (cell.getCellTypeEnum()) {
			case STRING:
				strCellValue = cell.toString();
				break;
			case NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					strCellValue = dateFormat.format(cell.getDateCellValue());
				} else {
					Double value = cell.getNumericCellValue();
					Long longValue = value.longValue();
					strCellValue = new String(longValue.toString());
				}
				break;
			case BOOLEAN:
				strCellValue = new String(new Boolean(cell.getBooleanCellValue()).toString());
				break;
			case BLANK:
				strCellValue = "";
				break;
			case ERROR:
				break;
			default:
				break;
			}
		}
		return strCellValue.trim();
	}

}
