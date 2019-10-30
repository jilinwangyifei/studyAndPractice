package Java8.Chapter12;

import java.time.*;

public class Test12_3 {

    //处理不同时区和方法
    void test12_3() {
        ZoneId romeZone = ZoneId.of("Europe/Rome");

        LocalDate date = LocalDate.of(2014, Month.MARCH, 18);
        ZonedDateTime zdt1 = date.atStartOfDay(romeZone);

        LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
        ZonedDateTime zdt2 = dateTime.atZone(romeZone);

        Instant instant = Instant.now();
        ZonedDateTime zdt3 = instant.atZone(romeZone);


        Instant instant1 = Instant.now();
        LocalDateTime time = LocalDateTime.ofInstant(instant1,  romeZone);
    }

    //利用和UTC/格林威治时间的固定偏差计算时区
    void test12_3_1() {

    }

    //使用别的日历系统
    void test12_3_2() {

    }
}
