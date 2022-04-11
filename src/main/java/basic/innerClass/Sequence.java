package basic.innerClass;

public class Sequence {
    private String[] item;
    public Sequence(int size, String marker) {
        item = new String[size];
        for(int i = 0; i < size; i++) {
            item[i] = marker + " " + i;
        }
    }

    class SequenceSelector implements Selector {
        int i = 0;
        @Override
        public String next() {
            String str = item[i];
            i++;
            return str;
        }

        @Override
        public boolean hasNext() {
            // 内部类可以使用外部类的所有成员
            return i < item.length;
        }

        @Override
        public int current() {
            return i;
        }
    }

    public Selector getSelector() {
        return new SequenceSelector();
    }

    public static void main(String[] args) {
        Sequence sequence = new Sequence(5, "hello world");
        Selector selector = sequence.getSelector();
        while(selector.hasNext()) {
            System.out.println("current index is " + selector.current());
            System.out.println("current value is " + selector.next());
        }
    }

}

interface Selector {
    int cursor = 0;
    String next();
    boolean hasNext();
    int current();
}