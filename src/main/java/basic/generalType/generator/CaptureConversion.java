package basic.generalType.generator;

public class CaptureConversion {
    public <T> void f(Holder<T> holder) {
        T t = holder.get();
        System.out.println(t.getClass().getSimpleName());
    }

    public static void main(String[] args) {
        CaptureConversion captureConversion = new CaptureConversion();
        Holder<Integer> holder = new Holder<>();
        holder.set(1);
        captureConversion.f(holder);


        SubHolder subHolder = new SubHolder();
        SubHolder subHolder2 = new SubHolder();
        subHolder.set(subHolder2);
    }
}

class Color {}
class Red {}
class Blue {}

class Holder<T> {
    private T element;
    public void set(T element) {
        this.element = element;
    }
    public T get() {
        return element;
    }
}

class SubHolder extends Holder<SubHolder> {}