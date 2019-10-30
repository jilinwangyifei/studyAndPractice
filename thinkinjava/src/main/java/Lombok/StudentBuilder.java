package Lombok;

import lombok.Builder;

/**
 *Builder 使用builder构造建造者模式
 */
@Builder
public class StudentBuilder {

    private String name;
    private int age;

    public static void main(String[] args) {
        StudentBuilder  studentBuilder =  StudentBuilder.builder().name("").age(11).build();
    }

}
