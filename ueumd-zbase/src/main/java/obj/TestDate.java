package obj;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;

public class TestDate {
    public static void main(String[] args) {
        // testDate();
//        testCalendar();
//        dateToCalender();
        // dateDiff();

        testDateTimeFormatter();
    }

    // Date 类
    public static void testDate() {
        Date d = new Date();
        System.out.println(d); // 系统时间

        //get...()——获取年月日.....
        System.out.println(d.getYear()+1900); // 从1900年开始算的
        System.out.println(d.getMonth()+1); // 月份从0开始计算
        System.out.println(d.getDate()); // 天数
        System.out.println(d.getHours());// 小时

        //getTime()——获取到时间的毫秒形式 返回的是long
        System.out.println(d.getTime());
    }


    // Calender类
    public static void testCalendar() {
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar);

        // calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 31); // 计算时间(这里用天数计算的)

        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH) + 1); // 月份：从0开始的
        System.out.println(calendar.get(Calendar.DATE)); // 日
        System.out.println(calendar.get(Calendar.HOUR_OF_DAY));// 小时
        System.out.println(calendar.get(Calendar.MINUTE));
        System.out.println(calendar.get(Calendar.SECOND));
    }

    // cal.setTime(d); 把Date转化成Calendar
    public static void dateToCalender() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();

        cal.setTime(date); // 把Date转化成Calendar

        System.out.println(cal);
        System.out.println(cal.get(Calendar.YEAR));
        System.out.println(cal.get(Calendar.MONTH) + 1);
        System.out.println(cal.get(Calendar.DATE));
    }


    /**
     *  SimpleDateFormat 线程不安全
     * @throws ParseException
     */
    public static void dateFormat() throws ParseException {
        Date date = new Date( System.currentTimeMillis());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // format 时间转字符串
        String dateText = sdf.format(date);
        System.out.println(dateText); // 2023-06-18 21:27:12

        // 字符串转时间
        Date d = sdf.parse(dateText); // Sun Jun 18 21:27:12 CST 2023
        System.out.println(d);
    }

    /**
     * DateTimeFormatter 线程安全
     */
    public static void testDateTimeFormatter () {
        Date date = new Date( System.currentTimeMillis());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        //格式化：日期-->字符串
        LocalDateTime now = LocalDateTime.now();
        String str1 = formatter.format(now);

        System.out.println("格式化之前："+now);   //2023-06-18T22:12:54.166
        System.out.println("格式化之后"+str1);    //2023-06-18 10:12:54
        //解析：字符串-->日期
        TemporalAccessor parse = formatter.parse("2023-06-18 10:12:54");
        System.out.println(parse);//{MinuteOfHour=12, HourOfAmPm=10, MilliOfSecond=0, SecondOfMinute=54, MicroOfSecond=0, NanoOfSecond=0},ISO resolved to 2023-06-18



    }

    public static void dateDiff() throws ParseException {
        String s1 = "2021-08-12 12:00:00"; // 开始时间
        String s2 = "2021-08-12 14:35:00"; // 结束时间
        //格式化时间
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        //将字符串转成时间形式
        Date d1 = sdf.parse(s1);
        Date d2 = sdf.parse(s2);

        //计算时间差:先要获取时间毫秒形式（long类型） 再做差
        long long1 = d1.getTime();
        long long2 = d2.getTime();

        // 毫秒时间差
        long diffTime = Math.abs(long1 - long2);


        // 秒级别时间差
        long diffSec = diffTime / 1000;

        // 分级别时间差
        long diffMin = diffSec / 60;

        //显示 xx小时xx分钟
        long showHours = diffMin / 60; // 小时
        long showMin = diffMin % 60;   // 分钟

        System.out.println(showHours + "小时" + showMin + "分钟"); // 2小时35分钟

    }
}
