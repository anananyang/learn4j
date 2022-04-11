package basic.exceptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ConstructorFinally {

    ConstructorFinally(String fileName) {
        InputStream is = null;
        try {
            is = new FileInputStream(fileName);
            // may throw an exception
            throw new NullPointerException();
        }catch (Exception e) {
            System.out.println("failed  to close input stream");
        } finally {
            if(is != null) {
                try {
                    is.close();
                }catch (IOException e) {
                    System.out.println("failed  to close input stream");
                }

            }
        }
    }

}
