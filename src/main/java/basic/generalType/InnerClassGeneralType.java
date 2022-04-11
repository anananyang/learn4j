package basic.generalType;

import java.util.concurrent.ThreadLocalRandom;

public class InnerClassGeneralType {
    public static Generator<Transaction> getGenerator() {
        return new Generator<Transaction>() {
            @Override
            public Transaction next() {
                return new Transaction();
            }
        };
    }

    public static void main(String[] args) {

        Generator<Transaction> generator = InnerClassGeneralType.getGenerator();
        int random = ThreadLocalRandom.current().nextInt(20);
        for(int i = 0; i < random; i++) {
            Transaction transaction = generator.next();
            System.out.println(transaction);
        }
    }
}

interface Generator<T> {
    T next();
}

class Transaction {
    private static int counter = 0;
    private int id = counter++;
    public String toString() {
        return this.getClass().getSimpleName() + "_" +id;
    }
}