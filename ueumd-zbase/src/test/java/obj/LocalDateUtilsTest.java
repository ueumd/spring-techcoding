package obj;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class LocalDateUtilsTest {

    @Test
    public void dateToLocalDate() {
        Date date = new Date();
        LocalDate localDate = LocalDateUtils.dateToLocalDate(date);
        System.out.println(localDate.toString());
    }

    @Test
    public void dateToLocalDateTime() {
        Date date = new Date();
        LocalDateTime localDateTime = LocalDateUtils.dateToLocalDateTime(date);
        System.out.println(localDateTime.toString());
    }

    @Test
    public void localDateTimeToDate() {
        LocalDateTime localDateTime = LocalDateUtils.dateToLocalDateTime(new Date());
        Date date = LocalDateUtils.localDateTimeToDate(localDateTime);
        System.out.println(date);

    }

    @Test
    public void localDateToDate() {
        Date date = LocalDateUtils.localDateToDate(LocalDateUtils.dateToLocalDate(new Date()));
        System.out.println(date);
    }

    @Test
    public void formatNow() {
        System.out.println(LocalDateUtils.formatNow(LocalDateUtils.YYYY_MM_DD_HH_MI_SS));
    }

    @Test
    public void formatNowOfTimestampMS() {
        System.out.println(LocalDateUtils.formatNowOfTimestampMS());
    }

    @Test
    public void formatNowOfTimestampSec() {
        System.out.println(LocalDateUtils.formatNowOfTimestampSec());
    }

    @Test
    public void formatNowOfDay() {
        System.out.println(LocalDateUtils.formatNowOfDay());
    }

    @Test
    public void formatNowOfHour() {
        System.out.println(LocalDateUtils.formatNowOfHour());
    }

    @Test
    public void formatNowOfMin() {
        System.out.println(LocalDateUtils.formatNowOfMin());
    }

    @Test
    public void formatNowOfSec() {
        System.out.println(LocalDateUtils.formatNowOfSec());
    }

    @Test
    public void formatDate() {
        System.out.println(LocalDateUtils.formatDate(new Date(), LocalDateUtils.YYYY_MM_DD_HH));
    }

    @Test
    public void formatString() {
        System.out.println(LocalDateUtils.formatString("2020-01-01", LocalDateUtils.YYYY_MM_DD, LocalDateUtils.YYYY_MM_DD_HH_MI_SS));
    }

    @Test
    public void compare() {
        System.out.println(LocalDateUtils.compare("2020-01-01 00:00:00", "2020-01-02 00:00:00"));
    }

    @Test
    public void diffMillis() {
        System.out.println(LocalDateUtils.diffMillis("2020-01-01 00:00:00", "2020-01-02 00:00:00"));
    }

    @Test
    public void diffSec() {
        System.out.println(LocalDateUtils.diffSec("2020-01-01 00:00:00", "2020-01-02 00:00:00"));
    }

    @Test
    public void diffMin() {
        System.out.println(LocalDateUtils.diffMin("2020-01-01 00:00:00", "2020-01-02 00:00:00"));
    }

    @Test
    public void diffHour() {
        System.out.println(LocalDateUtils.diffHour("2020-01-01 00:00:00", "2020-01-02 00:00:00"));
    }

    @Test
    public void diffDay() {
        System.out.println(LocalDateUtils.diffDay("2020-01-01 00:00:00", "2020-01-02 00:00:00"));
    }

    @Test
    public void minusYears() {
        System.out.println(LocalDateUtils.minusYears("2020-01-01 10:10:10", 1));
    }

    @Test
    public void minusMonths() {
        System.out.println(LocalDateUtils.minusMonths("2020-01-01 10:10:10", 1));
    }

    @Test
    public void minusDay() {
        System.out.println(LocalDateUtils.minusDay("2020-01-01 10:10:10", 1));
    }

    @Test
    public void minusHours() {
        System.out.println(LocalDateUtils.minusHours("2020-01-01 10:10:10", 1));
    }

    @Test
    public void minusMin() {
        System.out.println(LocalDateUtils.minusMin("2020-01-01 10:10:10", 1));
    }

    @Test
    public void minusSeconds() {
        System.out.println(LocalDateUtils.minusSeconds("2020-01-01 10:10:10", 1));
    }

    @Test
    public void isLastDayOfMonth() {
        System.out.println(LocalDateUtils.isLastDayOfMonth());
    }

    @Test
    public void isFirstDayOfMonth() {
        System.out.println(LocalDateUtils.isFirstDayOfMonth());
    }

    @Test
    public void getLastDayOfMonth() {
        System.out.println(LocalDateUtils.getLastDayOfMonth());
    }

    @Test
    public void getFirstDayOfMonth() {
        System.out.println(LocalDateUtils.getFirstDayOfMonth());
    }

    @Test
    public void parseStrToDate() {
        System.out.println(LocalDateUtils.parseStrToDate("2020-01-01", LocalDateUtils.YYYY_MM_DD));
    }

    @Test
    public void dayOfStart() {
        System.out.println(LocalDateUtils.dayOfStart());
    }

    @Test
    public void dayOfEnd() {
        System.out.println(LocalDateUtils.dayOfEnd());
    }
}
