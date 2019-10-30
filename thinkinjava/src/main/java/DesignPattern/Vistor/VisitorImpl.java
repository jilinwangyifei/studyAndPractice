package DesignPattern.Vistor;

public class VisitorImpl implements Visitor {

    @Override
    public void visit(Subject subject) {
        System.out.println("visit subject");
    }
}
