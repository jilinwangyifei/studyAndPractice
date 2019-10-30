package Lombok;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "ofName")
public class Student {
    //RequiredArgsConstructor 静态构造参数和必传参数构造方法

    @NonNull
    private String name;
    private int age;

    public static void main(String[] args) {
        Student student =
                new Student("asd").setAge(1);

        Student student1 = Student.ofName("zs");

    }
}
