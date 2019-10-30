package Chapter11.Item77.enum_singleton;

import java.util.Arrays;// Enum singleton - the preferred approach - Page 311

import java.util.*;

public enum Elvis {
    INSTANCE;
    private String[] favoriteSongs =
        { "Hound Dog", "Heartbreak Hotel" };
    public void printFavorites() {
        System.out.println(Arrays.toString(favoriteSongs));
    }
}