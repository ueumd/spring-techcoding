package obj;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

import static java.time.format.DateTimeFormatter.ofPattern;

/**
 * 日期工具类
 */
public class LocalDateUtils {

    /**
     * yyyy-MM-dd
     */
    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * yyyy-MM-dd HH
     */
    public static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH";

    /**
     * yyyy-MM-dd HH:mm
     */
    public static final String YYYY_MM_DD_HH_MI = "yyyy-MM-dd HH:mm";
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String YYYY_MM_DD_HH_MI_SS = "yyyy-MM-dd HH:mm:ss";
    /**
     * yyyyMMddHHmmss
     */
    public static final String YYYYMMDDHHMISS = "yyyyMMddHHmmss";
    public static final String YYYYMMDDHHMISSMS = "yyyyMMddHHmmssSSS";


    private static final DateTimeFormatter DATE_TIME_FORMATTER_DAY = ofPattern(YYYY_MM_DD);
    private static final DateTimeFormatter DATE_TIME_FORMATTER_HOUR = ofPattern(YYYY_MM_DD_HH);
    private static final DateTimeFormatter DATE_TIME_FORMATTER_MIN = ofPattern(YYYY_MM_DD_HH_MI);
    private static final DateTimeFormatter DATE_TIME_FORMATTER_SEC = ofPattern(YYYY_MM_DD_HH_MI_SS);
    private static final DateTimeFormatter DATE_TIME_FORMATTER_TIMESTAMP_SEC = ofPattern(YYYYMMDDHHMISS);
    private static final DateTimeFormatter DATE_TIME_FORMATTER_TIMESTAMP_MS = ofPattern(YYYYMMDDHHMISSMS);

    /**
     * data 转 LocalDate
     *
     * @param date date
     * @return LocalDate
     */
    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * data 转 LocalDateTime
     *
     * @param date date
     * @return LocalDateTime
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * localDateTime 转 Date
     *
     * @param localDateTime localDateTime
     * @return Date
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * localDate 转 Date
     *
     * @param localDate localDate
     * @return Date
     */
    public static Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 按照指定的format格式化，LocalDateUtils本身已经定义了一些常用格式，可直接使用，也可自定义
     *
     * @param format 时间格式
     * @return
     */
    public static String formatNow(String format) {
        return LocalDateTime.now().format(ofPattern(format));
    }

    /**
     * 格式化当前时间【年月日时分秒】
     *
     * @return 20000101000000000
     */
    public static String formatNowOfTimestampMS() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER_TIMESTAMP_MS);
    }

    /**
     * 格式化当前时间【年月日时分秒】
     *
     * @return 20000101000000
     */
    public static String formatNowOfTimestampSec() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER_TIMESTAMP_SEC);
    }

    /**
     * 格式化当前时间【年-月-日】
     *
     * @return 2000-01-01
     */
    public static String formatNowOfDay() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER_DAY);
    }

    /**
     * 格式化当前时间【年-月-日 小时】
     *
     * @return 2000-01-01 08
     */
    public static String formatNowOfHour() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER_HOUR);
    }

    /**
     * 格式化当前时间【年-月-日 小时:分钟】
     *
     * @return 2000-01-01 08:00
     */
    public static String formatNowOfMin() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER_MIN);
    }

    /**
     * 格式化当前时间【年-月-日 小时:分钟:秒】
     *
     * @return 2000-01-01 08:00:00
     */
    public static String formatNowOfSec() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER_SEC);
    }

    /**
     * 格式化时间为yyyy-MM-dd HH:mm:ss，然后进行比较
     *
     * @param date1 比较时间1
     * @param date2 比较时间2
     * @return -1、0、1
     */
    public static int compare(String date1, String date2) {
        LocalDateTime d1 = LocalDateTime.parse(date1, DATE_TIME_FORMATTER_SEC);
        LocalDateTime d2 = LocalDateTime.parse(date2, DATE_TIME_FORMATTER_SEC);
        return d1.compareTo(d2);
    }

    /**
     * 计算两个时间相差的毫秒数
     *
     * @param start 【年-月-日 小时:分钟:秒】2000-01-01 08:00:00
     * @param end   【年-月-日 小时:分钟:秒】2000-01-01 08:00:02
     * @return 相差的毫秒数：2000
     */
    public static long diffMillis(String start, String end) {
        LocalDateTime startTime = LocalDateTime.parse(start, DATE_TIME_FORMATTER_SEC);
        LocalDateTime endTime = LocalDateTime.parse(end, DATE_TIME_FORMATTER_SEC);
        return Duration.between(startTime, endTime).toMillis();
    }

    /**
     * 计算两个时间相差的秒数
     *
     * @param start 【年-月-日 小时:分钟:秒】2000-01-01 08:00:00
     * @param end   【年-月-日 小时:分钟:秒】2000-01-01 08:00:02
     * @return 相差的秒数：2
     */
    public static long diffSec(String start, String end) {
        LocalDateTime startTime = LocalDateTime.parse(start, DATE_TIME_FORMATTER_SEC);
        LocalDateTime endTime = LocalDateTime.parse(end, DATE_TIME_FORMATTER_SEC);
        return Duration.between(startTime, endTime).toMillis() / 1000;
    }

    /**
     * 计算两个时间相差的分钟数
     *
     * @param start 【年-月-日 小时:分钟:秒】2000-01-01 08:00:00
     * @param end   【年-月-日 小时:分钟:秒】2000-01-01 08:01:00
     * @return 相差的分钟数：1
     */
    public static long diffMin(String start, String end) {
        LocalDateTime startTime = LocalDateTime.parse(start, DATE_TIME_FORMATTER_SEC);
        LocalDateTime endTime = LocalDateTime.parse(end, DATE_TIME_FORMATTER_SEC);
        return Duration.between(startTime, endTime).toMinutes();
    }

    /**
     * 计算两个时间相差的小时数
     *
     * @param start 【年-月-日 小时:分钟:秒】2000-01-01 08:00:00
     * @param end   【年-月-日 小时:分钟:秒】2000-01-01 09:00:00
     * @return 相差的小时数：1
     */
    public static long diffHour(String start, String end) {
        LocalDateTime startTime = LocalDateTime.parse(start, DATE_TIME_FORMATTER_SEC);
        LocalDateTime endTime = LocalDateTime.parse(end, DATE_TIME_FORMATTER_SEC);
        return Duration.between(startTime, endTime).toHours();
    }

    /**
     * 计算两个时间相差的天数
     *
     * @param start 【年-月-日 小时:分钟:秒】2000-01-01 08:00:00
     * @param end   【年-月-日 小时:分钟:秒】2000-01-03 08:00:00
     * @return 相差的天数：2
     */
    public static long diffDay(String start, String end) {
        LocalDateTime startTime = LocalDateTime.parse(start, DATE_TIME_FORMATTER_SEC);
        LocalDateTime endTime = LocalDateTime.parse(end, DATE_TIME_FORMATTER_SEC);
        return Duration.between(startTime, endTime).toDays();
    }

    /**
     * 让time时间减去指定的年数
     *
     * @param time  【年-月-日 小时:分钟:秒】2000-01-01 08:00:00
     * @param years 减去指定的年数
     * @return 减去后的时间
     */
    public static String minusYears(String time, int years) {
        return LocalDateTime.parse(time, DATE_TIME_FORMATTER_SEC).minusYears(years).format(DATE_TIME_FORMATTER_SEC);
    }

    /**
     * 让time时间减去指定的月数
     *
     * @param time   【年-月-日 小时:分钟:秒】2000-01-01 08:00:00
     * @param months 减去指定的月数
     * @return 减去后的时间
     */
    public static String minusMonths(String time, int months) {
        return LocalDateTime.parse(time, DATE_TIME_FORMATTER_SEC).minusMonths(months).format(DATE_TIME_FORMATTER_SEC);
    }

    /**
     * 让time时间减去指定的天数
     *
     * @param time 【年-月-日 小时:分钟:秒】2000-01-01 08:00:00
     * @param days 要减去的天数，当传入负数时，等于加上指定的天数
     * @return 减去后的时间
     */
    public static String minusDay(String time, int days) {
        return LocalDateTime.parse(time, DATE_TIME_FORMATTER_SEC).minusDays(days).format(DATE_TIME_FORMATTER_SEC);
    }

    /**
     * 让time时间减去指定的小时数
     *
     * @param time  【年-月-日 小时:分钟:秒】2000-01-01 08:00:00
     * @param hours 要减去的小时数
     * @return 减去后的时间
     */
    public static String minusHours(String time, int hours) {
        return LocalDateTime.parse(time, DATE_TIME_FORMATTER_SEC).minusHours(hours).format(DATE_TIME_FORMATTER_SEC);
    }

    /**
     * 让time时间减去指定的分钟数
     *
     * @param time    【年-月-日 小时:分钟:秒】2000-01-01 08:00:00
     * @param minutes 要减去的分钟数
     * @return 减去后的时间
     */
    public static String minusMin(String time, int minutes) {
        return LocalDateTime.parse(time, DATE_TIME_FORMATTER_SEC).minusMinutes(minutes).format(DATE_TIME_FORMATTER_SEC);
    }

    /**
     * 让time时间减去指定的秒数
     *
     * @param time    【年-月-日 小时:分钟:秒】2000-01-01 08:00:00
     * @param seconds 要减去的秒数
     * @return 减去后的时间
     */
    public static String minusSeconds(String time, int seconds) {
        return LocalDateTime.parse(time, DATE_TIME_FORMATTER_SEC).minusSeconds(seconds).format(DATE_TIME_FORMATTER_SEC);
    }

    /**
     * 判断当前时间是否为当月的最后一天
     *
     * @return true/false
     */
    public static boolean isLastDayOfMonth() {
        LocalDate today = LocalDate.now();
        LocalDate lastDayOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        return Period.between(today, lastDayOfMonth).getDays() == 0;
    }

    /**
     * 判断当前时间是否为当月的第一天
     *
     * @return true/false
     */
    public static boolean isFirstDayOfMonth() {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        return Period.between(today, firstDayOfMonth).getDays() == 0;
    }

    /**
     * 获取当月的最后一天
     *
     * @return yyyy-MM-dd 字符串
     */
    public static String getLastDayOfMonth() {
        LocalDate today = LocalDate.now();
        LocalDate lastDayOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        return lastDayOfMonth.format(DATE_TIME_FORMATTER_DAY);
    }

    /**
     * 获取当月的第一天
     *
     * @return yyyy-MM-dd 字符串
     */
    public static String getFirstDayOfMonth() {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        return firstDayOfMonth.format(DATE_TIME_FORMATTER_DAY);
    }

    /**
     * string转date
     *
     * @param date           字符串
     * @param originalFormat 当前字符串的日期格式
     * @return
     */
    public static Date parseStrToDate(String date, String originalFormat) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(originalFormat);
        LocalDateTime time = LocalDateTime.from(LocalDate.parse(date, formatter).atStartOfDay());
        return LocalDateUtils.localDateTimeToDate(time);
    }

    /**
     * 把date按照指定格式转成字符串
     *
     * @param date   date
     * @param format 格式
     * @return
     */
    public static String formatDate(Date date, String format) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return localDateTime.format(ofPattern(format));
    }

    /**
     * 把原时间格式，转换成另一种时间格式
     *
     * @param date           string 时间
     * @param originalFormat 原格式
     * @param newFormat      转换后的格式
     * @return
     */
    public static String formatString(String date, String originalFormat, String newFormat) {
        return LocalDateUtils.formatDate(parseStrToDate(date, originalFormat), newFormat);
    }

    /**
     * 获取一天中的开始时间
     *
     * @return 2020-01-01 00:00:00
     */
    public static String dayOfStart() {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        return localDateTime.format(DATE_TIME_FORMATTER_SEC);
    }

    /**
     * 获取一天中的结束时间
     *
     * @return 2020-01-01 23:59:59
     */
    public static String dayOfEnd() {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        return localDateTime.format(DATE_TIME_FORMATTER_SEC);
    }

}
