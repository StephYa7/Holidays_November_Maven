package FirstPackage.ThreadTestDeadBlockPlus;

import java.util.Random;

public class InterruptedThread {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            Random random = new Random();
            for (int i = 0; i < 1_000_000_000; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Thread interrupted " + i);
                    break;
                }


            }
        });

        System.out.println("Starting");
        thread.start();

        Thread.sleep(900);
        thread.interrupt();
        thread.join();
        System.out.println("Thread stopped");
    }
}