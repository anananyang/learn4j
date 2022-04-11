package basic.generalType.generator;

import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public interface Generator<T> {
    T next();
}

class Coffee {
    private static long counter = 0;
    private final long id = counter++;

    public String toString() {
        return this.getClass().getSimpleName() + "_" + id;
    }
}

// 拿铁
class Latte extends Coffee {}

// 摩卡
class Mocha extends Coffee {}

// 卡布奇诺
class Cappuccin extends Coffee {}

// 美式咖啡
class Americano extends Coffee {}

class CoffeeGenerator implements Generator<Coffee>, Iterable<Coffee> {

    private static final Class[] types = {
            Latte.class,
            Mocha.class,
            Cappuccin.class,
            Americano.class,
    };

    private int size = 0;

    CoffeeGenerator(int size) {
        this.size = size;
    }

    @Override
    public Coffee next() {
        // 生成一个随机数
        int typeNum = types.length;
        int typeIndex = ThreadLocalRandom.current().nextInt(typeNum);
        Coffee coffees = null;
        try {
            coffees = (Coffee) types[typeIndex].newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return coffees;
    }

    @Override
    public Iterator<Coffee> iterator() {
        return new CoffeeIterator();
    }

    class CoffeeIterator implements Iterator<Coffee> {
        private int count = size;

        @Override
        public boolean hasNext() {
            return count > 0;
        }

        @Override
        public Coffee next() {
            Coffee coffee = CoffeeGenerator.this.next();
            count--;
            return coffee;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        int coffeeNum = ThreadLocalRandom.current().nextInt(10);
        CoffeeGenerator coffeeGenerator = new CoffeeGenerator(coffeeNum);
        Iterator<Coffee> iterator = coffeeGenerator.iterator();
        while (iterator.hasNext()) {
            Coffee coffee = iterator.next();
            System.out.println(coffee);
        }
    }
}