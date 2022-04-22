package basic.enumTest;

import java.util.concurrent.ThreadLocalRandom;

public class EnumImplementsInterface {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(CartoonCharacter.BOB.next());
        }
    }

}

interface Generator<T> {
    T next();
}


enum CartoonCharacter implements Generator<CartoonCharacter> {
    SLAPPY, SPANKY, PUNCHY, SILLY, BOUNCY, NUTTY, BOB;


    @Override
    public CartoonCharacter next() {
        CartoonCharacter[] cartoonCharacters = values();
        int size = cartoonCharacters.length;
        int idx = ThreadLocalRandom.current().nextInt(size);
        return cartoonCharacters[idx];
    }
}
