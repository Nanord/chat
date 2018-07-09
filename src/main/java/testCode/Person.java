package testCode;

public final class Person extends Thread{

    public void h() {
        this.start();
    }
    @Override
    public void run() {
        for (int i = 0; i < 100; i++)
            System.out.println("я тут");
    }
}

