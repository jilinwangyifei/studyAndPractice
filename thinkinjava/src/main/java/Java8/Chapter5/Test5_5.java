package Java8.Chapter5;

import lombok.Data;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Test5_5 {

    void test() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        //1 找出2011􏰔的所有􏴷易并按􏴷易额排序
        List<Transaction> tr2011 = transactions.stream().
                filter(d -> d.getYear() == 2011).
                sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());


        //2 易员都在哪些不同的􏴺􏴻工作过􏴺􏴻
        List<String> citys = transactions.stream().
                map(d -> d.getTrader().getCity()).
                distinct().collect(Collectors.toList());

        //3 查找所有来自于􏴼􏴽的􏴷易员，并按􏴾名排序
        List<Trader> traders = transactions.stream().map(d -> d.getTrader()).
                filter(d -> d.getCity().equals("Cambridge")).
                sorted(Comparator.comparing(Trader::getName)).
                distinct().collect(Collectors.toList());

        //4 返回所有􏴷易员的􏴾名字符串，按字􏲘顺序排序
        String names = transactions.stream().
                map(d -> d.getTrader().getName()).
                sorted().reduce("",(n1,n2) -> n1+n2);

        ///5 有没有􏴷易员是在􏴿􏵀工作的
        boolean isMiLan = transactions.stream().
                anyMatch(d -> d.getTrader().getCity().equals("Milan"));

        //6 打印生活在剑桥交易员的所有交易额
        transactions.stream().filter(d -> "剑桥".equals(d.getTrader().getCity())).
                map(Transaction::getValue).forEach(System.out::println);

        //7 所有􏴷易中，最高的􏴷易额是多少
        Optional<Integer> max = transactions.stream().
                map(Transaction::getValue).reduce(Integer::max);

        //8 找到交易额最小的交易
        Optional<Transaction> smallestTransaction = transactions.stream().
                min(Comparator.comparing(Transaction::getValue));
    }
}


@Data
class Trader {
    private final String name;
    private final String city;

    public Trader(String n, String c) {
        this.name = n;
        this.city = c;
    }
}

@Data
class Transaction{
    private final Trader trader;
    private final int year;
    private final int value;
}
