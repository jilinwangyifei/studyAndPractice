package Java8.Chapter8;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Test8_2 {

    final static Map<String, Supplier<Product>> map = new HashMap<>(); static {
        map.put("loan", Loan::new);
        map.put("stock", Stock::new);
        map.put("bond", Bond::new);
    }

    //8.2.1 策略模式
    void test8_2_1() {
        Validator numericValidator = new Validator(new IsNumeric());
        boolean b1 = numericValidator.validate("aaaa");
        Validator lowerCaseValidator = new Validator(new IsAllLowerCase ());
        boolean b2 = lowerCaseValidator.validate("bbbb");

        //lamdba写法
        Validator numericValidator2 = new Validator(s -> s.matches("[a-z]+"));
        numericValidator2.validate("aaaa");
        Validator lowerCaseValidator2 = new Validator(s ->s.matches("\\d+"));
        lowerCaseValidator2.validate("bbbb");
    }

    //8.2.2模板方法
    void test8_2_2() {
        new OnlineBankingLambda().processCustomer(11, (Customer c) -> System.out.println("客户满意"));
    }

    //8.2.3观察者模式
    void test8_2_3() {
        Feed f = new Feed();
        f.registerObserver(new NYTimes());
        f.registerObserver(new Guardian());
        f.registerObserver(new LeMonde());
        f.notifyObservers("The queen said her favourite book is Java 8 in Action!");

        //用lambda表达式实现
        f.registerObserver(tweet -> {
            if(tweet != null && tweet.contains("queen")){
                System.out.println("Yet another news in London... " + tweet);
            }
        });
    }

    //8.2.4 责任链模式
    void test8_2_4() {
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();
        p1.setSuccessor(p2);
        p1.handle("");

        //使用lamdba方式
        UnaryOperator<String> headerProcessing = (String text) -> "From Raoul, Mario and Alan: "+ text;

        UnaryOperator<String> spellCheckerProcessing =
                (String text) -> text.replaceAll("labda", "lambda");

        Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);

        String result = pipeline.apply("");
    }

    //8.2.5工厂模式
    Product test8_2_5(String name) {
        Supplier<Product> p = map.get(name);
        return (p != null) ? p.get() : null;
    }
}

//策略模式
interface  ValidationStrategy {
    boolean execute(String s);
}

class IsAllLowerCase implements ValidationStrategy {
    @Override
    public boolean execute(String s) {
        return s.matches("[a-z]+");
    }
}

class IsNumeric implements ValidationStrategy {
    public boolean execute(String s){
        return s.matches("\\d+");
    }
}

class Validator {
    private final ValidationStrategy validationStrategy;

    Validator(ValidationStrategy v) {
       this.validationStrategy = v;
    }

    public boolean validate(String s){
        return validationStrategy.execute(s);
    }
}

//模板方法
abstract class OnlineBanking {
    public void processCustomer(int id) {
        Customer c = new Customer();
        makeCustomerHappy(c);
    }

    void makeCustomerHappy(Customer c) {

    }
}

class Customer{

}

class OnlineBankingLambda extends OnlineBanking {
    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy){
        Customer c = new Customer();
        makeCustomerHappy.accept(c);
    }
}

//观察者模式
interface Observer {
    void notify(String tweet);
}

class NYTimes implements Observer {
    @Override
    public void notify(String tweet) {

    }
}

class Guardian implements Observer {
    @Override
    public void notify(String tweet) {

    }
}

class LeMonde implements Observer{
    @Override
    public void notify(String tweet) {

    }
}

interface Subject{

    void registerObserver(Observer o);

    void notifyObservers(String tweet);
}

class Feed implements Subject{

    private final List<Observer> observers = new ArrayList<>();

    public void registerObserver(Observer o) {
        this.observers.add(o);
    }

    public void notifyObservers(String tweet) {
        observers.forEach(o -> o.notify(tweet));
    }
}


//责任链模式
@Data
abstract class ProcessingObject<T> {

    protected ProcessingObject<T> successor;

    public T handle(T input){
        T r = handleWork(input);
        if(successor != null){
            return successor.handle(r);
        }
        return r;
    }

    abstract protected T handleWork(T input);
}

class HeaderTextProcessing extends ProcessingObject<String>{

    @Override
    protected String handleWork(String input) {
        return null;
    }
}

class SpellCheckerProcessing extends ProcessingObject<String> {
    @Override
    protected String handleWork(String input) {
        return null;
    }
}


//工厂模式
class Product{

}

class Loan extends Product{

}

class Stock extends Product{

}

class Bond extends Product{

}

