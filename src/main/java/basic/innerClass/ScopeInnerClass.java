package basic.innerClass;

public class ScopeInnerClass {

    private int i = 11;

    public ScopeInnerInterface getScopeInnerInterface(final int param) {

        class MethodInnerClass implements ScopeInnerInterface {
            @Override
            public void f() {
                System.out.println("MethodInnerClass.f(param = " + param + ")");
                System.out.println("MethodInnerClass ScopeInnerClass.i = " + i);
            }
        }
        return new MethodInnerClass();
    }

    public ScopeInnerInterface getLocalScopeInnerClass(final int param) {
        if(true) {
            class LocalScopeInnerClass implements ScopeInnerInterface {
                @Override
                public void f() {
                    System.out.println("LocalScopeInnerClass.f(param = " + param + ")");
                    System.out.println("LocalScopeInnerClass ScopeInnerClass.i = " + i);
                }
            }

            return new LocalScopeInnerClass();
        }

        return null;
    }

    public ScopeInnerInterface getAnonymousInnerClass(final int param) {
        return new ScopeInnerInterface() {
            @Override
            public void f() {
                System.out.println("AnonymousInnerClass.f(param = " + param + " ) ");
                System.out.println("AnonymousInnerClass.i = " + i);
            }
        };
    }


    public AnonymousParentClass getAnonymousInnerClass2(final int param) {
        return new AnonymousParentClass() {
            @Override
            public void f() {
                System.out.println("AnonymousInnerClass.f(param = " + param + " ) ");
                System.out.println("AnonymousInnerClass.i = " + i);
            }
        };
    }
}

interface ScopeInnerInterface {
    void f();
}

class AnonymousParentClass {
    public void f() {
        System.out.println("AnonymousParentClass.f()");
    }
}

class Main {
    public static void main(String[] args) {
        ScopeInnerClass scopeInnerClass = new ScopeInnerClass();

        ScopeInnerInterface scopeInnerInterface = scopeInnerClass.getScopeInnerInterface(1);
        scopeInnerInterface.f();

        ScopeInnerInterface scopeInnerInterface2 = scopeInnerClass.getLocalScopeInnerClass(2);
        scopeInnerInterface2.f();

        ScopeInnerInterface scopeInnerInterface3 = scopeInnerClass.getAnonymousInnerClass(3);
        scopeInnerInterface3.f();

        AnonymousParentClass scopeInnerInterface4 = scopeInnerClass.getAnonymousInnerClass2(4);
        scopeInnerInterface4.f();
    }
}
