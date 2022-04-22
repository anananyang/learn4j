package basic.enumTest;

import static basic.enumTest.EnumBasic.Shrubbery.*;

public class EnumBasic {
    enum Shrubbery {
        GROUND, CRAWLING, HANGING
    }

    public static void main(String[] args) {
        for(Shrubbery shrubbery : Shrubbery.values()) {
            System.out.println(shrubbery + " ordinal: " + shrubbery.ordinal());
            System.out.println(shrubbery + " compareTo CRAWLING: " + shrubbery.compareTo(CRAWLING));
            System.out.println(shrubbery + " equals CRAWLING: " + shrubbery.equals(CRAWLING));
            System.out.println(shrubbery + " == CRAWLING: " + (shrubbery == CRAWLING));
            System.out.println(shrubbery + " class: " + shrubbery.getDeclaringClass());
            System.out.println(shrubbery.name());
            System.out.println("----------------------");
        }

        for(String str : "GROUND,CRAWLING,HANGING".split(",")) {
//            Shrubbery shrubbery = Enum.valueOf(Shrubbery.class, str);
            Shrubbery shrubbery = Shrubbery.valueOf(str);
            System.out.println(shrubbery);
        }
        System.out.println(Shrubbery.GROUND);

    }
}
