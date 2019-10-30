package DesignPattern.Vistor;

//访问者模式
//访问者模式将有关行为集中到一个访问者对象中，其改变不影响系统数据结构
public class MySubject implements Subject {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public static void main(String[] args) {
        Subject subject = new MySubject();
        Visitor visitor = new VisitorImpl();
        subject.accept(visitor);
    }
}
