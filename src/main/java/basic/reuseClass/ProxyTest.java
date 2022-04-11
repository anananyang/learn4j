package basic.reuseClass;

import java.io.*;

public class ProxyTest {
    public void f1() {
        System.out.println("ProxyTest.f1()");
    }

    public void f2() {
        System.out.println("ProxyTest.f2()");
    }
}

class ProxyTestDelegation {
    private ProxyTest proxyTest;

    ProxyTestDelegation(ProxyTest proxyTest) {
        this.proxyTest = proxyTest;
    }

    public void f1() {
        proxyTest.f1();
    }

    public static void main(String[] args) throws IOException {
        InputStream is = null;
        BufferedInputStream bis = null;
        try {
            is = new FileInputStream("filepath");
            bis = new BufferedInputStream(is);
        } finally {
            if(bis != null) {
                bis.close();
            }
        }


    }
}
