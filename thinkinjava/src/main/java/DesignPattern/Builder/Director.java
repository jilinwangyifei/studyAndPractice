package DesignPattern.Builder;

/**
 * Created by wangbo on 2018/10/14.
 */
public class Director {

    public  Builder builder;

    Director(Builder builder) {
        this.builder = builder;
    }

    public void construct() {
        builder.buildPart1();
        builder.buildPart2();
    }
}
