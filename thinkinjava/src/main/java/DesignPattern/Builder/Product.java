package DesignPattern.Builder;

import java.lang.annotation.Retention;

/**
 * Created by wangbo on 2018/10/14.
 */

public class Product {
    String part1;
    String part2;

    public String getPart1() {
        return part1;
    }

    public void setPart1(String part1) {
        this.part1 = part1;
    }

    public String getPart2() {
        return part2;
    }

    public void setPart2(String part2) {
        this.part2 = part2;
    }
}
