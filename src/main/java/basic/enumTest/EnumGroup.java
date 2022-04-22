package basic.enumTest;

import java.util.EnumSet;

public class EnumGroup {
}

interface Food {
    enum Appetzer implements Food {
        SALAD, SOUP, SPRING_ROOLS;
    }

    enum MainCourse implements Food {
        LASANGE, BURRITO, PAD_THAI, LENTILS, HUMMOUS, VINDALOO;
    }
}

class FoodMain {
    public static void main(String[] args) {
        EnumSet<Food.Appetzer> foodEnumSet = EnumSet.noneOf(Food.Appetzer.class);
        foodEnumSet.addAll(EnumSet.of(Food.Appetzer.SALAD, Food.Appetzer.SALAD));
        foodEnumSet.add(Food.Appetzer.SOUP);
        foodEnumSet.addAll(EnumSet.allOf(Food.Appetzer.class));

        System.out.print(foodEnumSet);
    }
}
