package basic.init;

public class FinalizeTest {
    public static void main(String[] args) {
        Book book1 = new Book(true);
        book1.checkIn();
        new Book(true);
        //
        System.gc();
    }
}


class Book {
    private boolean checkout = false;
    Book(boolean checkout) {
        this.checkout = checkout;
    }
    void checkIn() {
        this.checkout = false;
    }

    @Override
    protected void finalize() throws Throwable {
        if(checkout) {
            System.out.println("Error: check out");
        }
//        super.finalize();
    }
}