package vn.dnict.microservice.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	private static boolean isWorkingDay(Calendar cal) {
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY)
			return false;
		return true;
	}

	public static Date getEndDate(int soNgay, Date startDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		for (int i = 0; i < soNgay; i++)
			do {
				cal.add(Calendar.DAY_OF_MONTH, 1);
			} while (!isWorkingDay(cal));
		Date endDate = cal.getTime();
		return endDate;
	}

	public static long days(Date start, Date end) {
		// Ignore argument check

		Calendar c1 = Calendar.getInstance();
		c1.setTime(start);
		int w1 = c1.get(Calendar.DAY_OF_WEEK);
		c1.add(Calendar.DAY_OF_WEEK, -w1);

		Calendar c2 = Calendar.getInstance();
		c2.setTime(end);
		int w2 = c2.get(Calendar.DAY_OF_WEEK);
		c2.add(Calendar.DAY_OF_WEEK, -w2);

		// end Saturday to start Saturday
		long days = (c2.getTimeInMillis() - c1.getTimeInMillis()) / (1000 * 60 * 60 * 24);
		long daysWithoutWeekendDays = days - (days * 2 / 7);

		// Adjust days to add on (w2) and days to subtract (w1) so that Saturday
		// and Sunday are not included
		if (w1 == Calendar.SUNDAY && w2 != Calendar.SATURDAY) {
			w1 = Calendar.MONDAY;
		} else if (w1 == Calendar.SATURDAY && w2 != Calendar.SUNDAY) {
			w1 = Calendar.FRIDAY;
		}

		if (w2 == Calendar.SUNDAY) {
			w2 = Calendar.MONDAY;
		} else if (w2 == Calendar.SATURDAY) {
			w2 = Calendar.FRIDAY;
		}

		return daysWithoutWeekendDays - w1 + w2 + 1;
	}

	private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();

	static {

		map.put(1000, "M");
		map.put(900, "CM");
		map.put(500, "D");
		map.put(400, "CD");
		map.put(100, "C");
		map.put(90, "XC");
		map.put(50, "L");
		map.put(40, "XL");
		map.put(10, "X");
		map.put(9, "IX");
		map.put(5, "V");
		map.put(4, "IV");
		map.put(1, "I");

	}

	public final static String toRoman(int number) {
		int l = map.floorKey(number);
		if (number == l) {
			return map.get(number);
		}
		return map.get(l) + toRoman(number - l);
	}

	public static <T> void reverseList(List<T> list) {
		// base case: list is empty or only one element is left
		if (list == null || list.size() <= 1)
			return;

		// remove first element
		T value = list.remove(0);

		// recur for remaining items
		reverseList(list);

		// insert the top element back after recusing for remaining items
		list.add(value);
	}

	public String removeDuplicates(String yourText) {
		Set<String> elements = new LinkedHashSet<>(Arrays.asList(yourText.split(", ")));

		Iterator<String> it = elements.iterator();

		StringBuilder sb = new StringBuilder(it.hasNext() ? it.next() : "");
		while (it.hasNext()) {
			sb.append(", ").append(it.next());
		}

		return sb.toString();
	}
}
