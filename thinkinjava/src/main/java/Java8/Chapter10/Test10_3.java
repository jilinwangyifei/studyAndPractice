package Java8.Chapter10;

import java.util.Optional;

public class Test10_3 {

    //10.3.1 创建Optional对象
    void test10_3_1() {
        //1 声明一个空的Optional
        Optional<Car> car = Optional.empty();

        //2 依据非空值创建Optional
        Car car1 = new Car();
        Optional<Car> optCar = Optional.of(car1);

        //3 可接受null的Optional
        Optional<Car> optCar2 = Optional.ofNullable(car1);

    }

    //10.3.2 使用map从Optional对象中提取和转换值
    void test10_3_2() {
        Insurance insurance = new Insurance();
        Optional<Insurance> optionalInsurance = Optional.ofNullable(insurance);
        Optional<String> name = optionalInsurance.map(Insurance::getName);
    }

    //10.3.3 使用flatmap链接Optional对象
    void test10_3_3() {
        Person person = new Person();
        Optional<Person> optPerson = Optional.of(person);
        //错误写法
       /* Optional<String> name = optPerson.
                map(Person::getCar).
                map(Car::getInsurance).
                map(Insurance::getName);*/

        String name = optPerson.
                flatMap(Person::getCar).
                flatMap(Car::getInsurance).
                map(Insurance::getName).
                orElse("Unkone");
    }

    //10.3.5 默认行为及解引用Optional对象
    public Insurance findCheapestInsurance(Person person, Car car) {
        Insurance cheapestCompany = new Insurance();
        return cheapestCompany;
    }

    public Optional<Insurance> nullSafeFindCheapestInsurance(
            Optional<Person> person, Optional<Car> car) {
        if (person.isPresent() || car.isPresent()) {
            return Optional.of(findCheapestInsurance(person.get(), car.get()));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Insurance> nullSafeFindCheapestInsurance2(
            Optional<Person> person, Optional<Car> car) {
       return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
    }

    //10.3.6 使用filter剔除特定的值
    public String getCarInsuranceName(Optional<Person> person, int minAge) {
        return person.filter(p -> p.getAge() > minAge).
                flatMap(Person::getCar).flatMap(Car::getInsurance).map(Insurance::getName).orElse("");
    }

    //10.3.7 使用Optional的实战示例
    // TODO: 2019/5/5
}

class Person {
    private Optional<Car> car;
    private Integer age;

    public Integer getAge(){
        return age;
    }

    public Optional<Car> getCar() {
        return car;
    }
}

class Car {
    private Optional<Insurance> insurance;
    public Optional<Insurance> getInsurance() {
        return insurance;
    }
}

class Insurance {
    private String name;
    public String getName() {
        return name;
    }
}