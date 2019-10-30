package Java8.Chapter12;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

import static java.time.temporal.TemporalAdjusters.*;

public class Test12_2 {

    //操纵 解析和格式化日期
    void test12_2() {

        //直观的方式修改LocalDate属性
        LocalDate d1 = LocalDate.of(2014, 3, 18);
        LocalDate d2 = d1.withYear(2011);


        //以相对方式修改LocalDate属性
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        LocalDate data2 = date1.plusWeeks(1);
        LocalDate date3 = date1.minusYears(1);
        LocalDate date4 = date1.plus(6, ChronoUnit.MONTHS);
    }

    //使用TemporalAdjuster
    void test12_2_1() {
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        LocalDate date2 = date1.with(nextOrSame(DayOfWeek.SUNDAY)); //2014-03-23
        LocalDate date3 = date1.with(lastDayOfMonth());//2014-03-31

        TemporalAdjuster temporalAdjuster = TemporalAdjusters.ofDateAdjuster(
                temporal -> {
                    DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
                    int dayToAdd = 1;
                    if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;
                    if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;
                    return temporal.plus(dayToAdd, ChronoUnit.DAYS);
                });
    }

    // 打印输出及解析日期-时间对象
    void test12_2_2() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        String s1 = date.format(DateTimeFormatter.ISO_DATE_TIME); //2014-03-18
        String s2 = date.format(DateTimeFormatter.BASIC_ISO_DATE); //20140318

        LocalDate date1 = LocalDate.parse("20140318",DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate date2 = LocalDate.parse("2014-03-18",DateTimeFormatter.ISO_DATE_TIME);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date3 = LocalDate.of(2014, 3, 18);
        String formatTime = date3.format(formatter);
        LocalDate date4 = LocalDate.parse(formatTime, formatter);

        //创建本地化 DateTimeFormatter
        DateTimeFormatter italianFormatter =
                DateTimeFormatter.ofPattern("d. MMM yyyy", Locale.ITALIAN);
        LocalDate date5 = LocalDate.of(2014, 3, 18);
        String formateDate = date5.format(italianFormatter); // 18. marzo 2014
        LocalDate date6 = LocalDate.parse(formateDate, italianFormatter);

        //构建DateTimeFormatter
        DateTimeFormatter italianFormatter2 =
                new DateTimeFormatterBuilder().
                        appendText(ChronoField.DAY_OF_MONTH).appendLiteral(". ").
                        appendText(ChronoField.MONTH_OF_YEAR).appendLiteral(" ").
                        appendText(ChronoField.YEAR).
                        parseCaseInsensitive().toFormatter(Locale.ITALIAN);


    }

}
