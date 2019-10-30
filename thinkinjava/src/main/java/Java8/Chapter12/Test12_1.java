package Java8.Chapter12;

import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class Test12_1 {

    //使用LocalDate和LocalTime
    @Test
    public void test12_1_1 () {
        LocalDate date = LocalDate.of(2014, 3, 14);

        int year = date.get(ChronoField.YEAR);
        int month = date.get(ChronoField.MONTH_OF_YEAR);
        int day = date.get(ChronoField.DAY_OF_MONTH);

        //获取当天时间
        LocalDate  now = LocalDate.now();

        LocalTime time = LocalTime.of(13, 45, 20);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();

        LocalDate date2 = LocalDate.parse("2014-03-18");
        LocalTime time2 = LocalTime.parse("13:45:20");

    }

    //合并􏱨􏱩日期和时间
    void test12_1_2 () {
        LocalDateTime localDateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);

        LocalDate date = LocalDate.of(2014, 3, 14);
        LocalTime time = LocalTime.of(13, 45, 20);

        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(time);
        LocalDateTime dt4 = date.atTime(13, 45, 20);
        LocalDateTime dt5 = time.atDate(date);
    }

    //机器的日期和时间格式
    void test12_1_3 () {
        Instant.ofEpochSecond(3);
        Instant.ofEpochSecond(3, 0);
        //2秒之后的100万纳秒(1秒)
        Instant.ofEpochSecond(2, 1000000000);
        //4秒之前的100万纳秒(1秒)
        Instant.ofEpochSecond(4, -1000000000);

    }

    //定义Duration和Period
    void test12_1_4 () {

        LocalTime time1 = LocalTime.of(13, 45, 20);
        LocalTime time3 = LocalTime.of(13, 45, 20);
        Duration d1 = Duration.between(time1, time3);

        Period p1 = Period.between(LocalDate.of(2014, 3, 8),
                                        LocalDate.of(2014,3, 18));


        Duration threeMinutes = Duration.ofMinutes(3);
        Duration threeMinutes2 = Duration.of(3, ChronoUnit.MINUTES);

        Period tenDays = Period.ofDays(10);
        Period threeWeeks = Period.ofWeeks(3);
        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
    }

}
