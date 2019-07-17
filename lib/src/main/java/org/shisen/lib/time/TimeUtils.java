package org.shisen.lib.time;

import java.util.Calendar;
import java.util.Date;

/**
 * <pre>
 * Description
 * </pre>
 * @author shishis
 */
public class TimeUtils {

	/**
	 * 时间差值
	 * @param publishTime
	 * @param now
	 * @return
	 */
	public static String recursive(Date publishTime, Date now) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(publishTime);

		int year = calendar.get(Calendar.YEAR),
				month = calendar.get(Calendar.MONTH) + 1,
				day = calendar.get(Calendar.DAY_OF_MONTH),
				hour = calendar.get(Calendar.HOUR_OF_DAY),
				minute = calendar.get(Calendar.MINUTE);

		long d = (now.getTime() - publishTime.getTime()) / 1000;
		int d_days = (int) (d / 86400);
		int d_hours = (int) (d / 3600);
		long d_minutes = (d / 60);
		long d_seconds = d;

		if (d_days > 0 && d_days < 3) {
			return d_days + "天前";
		} else if (d_days <= 0 && d_hours > 0) {
			return d_hours + "小时前";
		} else if (d_hours <= 0 && d_minutes > 0) {
			return d_minutes + "分钟前";
		} else if (d_seconds < 60) {
			if (d_seconds <= 0) {
				return "刚刚";
			} else {
				return d_seconds + "秒前";
			}
		} else if (d_days >= 3 && d_days < 30) {
			return month + "-" + day + ' ' + hour + ':' + minute;
		} else if (d_days >= 30) {
			return year + "-" + month + '-' + day + ' ' + hour + ':' + minute;
		}
		return "";
	}
}
