package DesignPattern.Builder;

/**
 * Created by wangbo on 2018/10/14.
 *
 * 建造模式
 */
public interface Builder {

    public void buildPart1();

    public void buildPart2();

    public Product retrieveResult();
}
