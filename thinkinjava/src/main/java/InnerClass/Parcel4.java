package InnerClass;

/**
 * Created by wangbo on 2018/11/3.
 */
public class Parcel4 {
    private class PContents implements Contents {
        private int i = 1;
        @Override
        public int value() {
            return i;
        }
    }

    protected class PDestination implements Destination {
        private String label;

        PDestination(String whereTo) {
            this.label = whereTo;
        }

        public String readLabel(){
            return label;
        }
    }

    public PDestination destination(String s) {
        return new PDestination(s);
    }

    public PContents contents() {
        return new PContents();
    }

}

class test {
    public static void main(String[] args) {
        Parcel4 p = new Parcel4();
        Contents pContents = p.contents();
        Destination pDestination = p.destination("si");
        //cant access private class
        //Parcel4.PContents PP= p.new PContents();
    }
}

interface Destination {
    String readLabel();
}

interface Contents {
    int value();
}