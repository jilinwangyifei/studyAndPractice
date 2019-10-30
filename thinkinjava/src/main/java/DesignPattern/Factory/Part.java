package DesignPattern.Factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by wangbo on 2018/9/2.
 */
public class Part {

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    static List<Factory<? extends Part>> partFactries =
            new ArrayList<Factory<? extends Part>>();

    static {
        partFactries.add(new FuelFileter.Factory());
        partFactries.add(new ChainAirFileter.Factory());
        partFactries.add(new FuelBelt.Factory());
        partFactries.add(new ChainAirBelt.Factory());
    }

    private static Random random = new Random(47);
    public static Part createRamdom() {
        int n = random.nextInt(partFactries.size());
        System.out.println(n);
        return partFactries.get(n).create();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10 ; i++) {
            System.out.println(Part.createRamdom());
        }
    }
}

class Filter extends Part {}

class FuelFileter extends Filter {
    public static class Factory implements DesignPattern.Factory.Factory<FuelFileter> {
        @Override
        public FuelFileter create() {
            return new FuelFileter();
        }
    }
}


class ChainAirFileter extends Filter {
    public static class Factory implements DesignPattern.Factory.Factory<ChainAirFileter> {
        @Override
        public ChainAirFileter create() {
            return new ChainAirFileter();
        }
    }
}


class Belt extends Part {}

class FuelBelt extends Belt {
    public static class Factory implements DesignPattern.Factory.Factory<Belt> {
        @Override
        public FuelBelt create() {
            return new FuelBelt();
        }
    }
}


class ChainAirBelt extends Belt {
    public static class Factory implements DesignPattern.Factory.Factory<ChainAirBelt> {
        @Override
        public ChainAirBelt create() {
            return new ChainAirBelt();
        }
    }
}



