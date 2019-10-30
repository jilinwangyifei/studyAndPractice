package Java8.Chapter9;

public class Test9_3 {

    //9.3.1可选方法


    //9.3.2 行为的多继承
    void test() {
        //接口Rotatable Moveable Resizable 组合接口的实现
        //应用场景 游戏场景 有的需要调整大小 但是不需要旋转 有的需要旋转移动 但是不需要调整大小
    }


}

interface Rotatable {
    void setRotationAngle(int angleInDegrees);
    int getRotationAngle();
    default void rotateBy(int angleInDegrees){
        setRotationAngle((getRotationAngle () + angleInDegrees) % 360);
    }
}

interface Moveable {
    int getX();
    int getY();
    void setX(int x);
    void setY(int y);
    default void moveHorizontally(int distance){
        setX(getX() + distance);
    }
    default void moveVertically(int distance){
        setY(getY() + distance);
    }
}

interface Resizable {
    int getWidth();
    int getHeight();
    void setWidth(int width);
    void setHeight(int height);
    void setAbsoluteSize(int width, int height);
    default void setRelativeSize(int wFactor, int hFactor){
        setAbsoluteSize(getWidth() / wFactor, getHeight() / hFactor);
    }
}