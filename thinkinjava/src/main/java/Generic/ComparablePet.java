package Generic;

/**
 * Created by wangbo on 2018/10/20.
 */
public class ComparablePet implements Comparable<ComparablePet>{
    @Override
    public int compareTo(ComparablePet o) {
        return 0;
    }
}

/*
无法编译
class Cat extends ComparablePet implements Comparable<Cat> {

    @Override
    public int compareTo(Cat cat) {
        return super.compareTo(cat);
    }
}*/


class Hamster extends ComparablePet implements Comparable<ComparablePet> {
    @Override
    public int compareTo(ComparablePet o) {
        return super.compareTo(o);
    }
}

class Gecko extends ComparablePet {
    @Override
    public int compareTo(ComparablePet o) {
        return super.compareTo(o);
    }
}