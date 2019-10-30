package Java8.Chapter8;

import lombok.Data;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class Test8_3 {

    //8.3.2 测试lamdba方法的行为
    @Test
    public void test8_3_2(){
        List<Point> points =
                Arrays.asList(new Point(5, 5), new Point(10, 5));
        List<Point> expectedPoints =
                Arrays.asList(new Point(15, 5), new Point(20, 5));
        List<Point> newPoints = Point.moveAllPointsRightBy(points, 10);
        assertEquals(expectedPoints, newPoints);
    }

}


@Data
class Point{
    private final int x;
    private final int y;

    public static List<Point> moveAllPointsRightBy(List<Point> points, int x){
        return points.stream().
                map(p -> new Point(p.getX()+x, p.getY())).
                collect(Collectors.toList());
    }
}