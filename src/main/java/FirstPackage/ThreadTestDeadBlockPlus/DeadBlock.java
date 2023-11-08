package FirstPackage.ThreadTestDeadBlockPlus;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadBlock {
    public static void main(String[] args) throws InterruptedException {
        Runner runner = new Runner();

        Thread thread1 = new Thread(() -> {
            try {
                runner.firstThread();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                runner.secondThread();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread3 = new Thread(() -> {
            try {
                runner.thirdThread();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread4 = new Thread(() -> {
            try {
                runner.fourthThread();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread5 = new Thread(() -> {
            try {
                runner.fiveThread();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread6 = new Thread(() -> {
            try {
                runner.sixthThread();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();
        thread6.join();

        runner.finished();
    }
}

class Runner {
    private Account account1 = new Account();
    private Account account2 = new Account();
    private Account account3 = new Account();

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    public void firstThread() throws InterruptedException {
        extracted(account1, account2);
    }

    public void secondThread() throws InterruptedException {
        extracted(account2, account3);
    }

    public void thirdThread() throws InterruptedException {
        extracted(account3, account1);
    }

    public void fourthThread() throws InterruptedException {
        extracted(account1, account3);
    }

    public void fiveThread() throws InterruptedException {
        extracted(account2, account1);
    }

    public void sixthThread() throws InterruptedException {
        extracted(account3, account2);
    }

    private void extracted(Account account1, Account account2) throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i < 10_000; i++) {
            takeLocks();
            try {
                Account.transfer(account1, account2, random.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    private void takeLocks() throws InterruptedException {
        boolean firstLockTaken = false;
        boolean secondLockTaken = false;

        while (true) {
            try {
                firstLockTaken = lock1.tryLock();
                secondLockTaken = lock2.tryLock();
            } finally {
                if (firstLockTaken && secondLockTaken) {
                    return;
                }
                if (firstLockTaken) {
                    lock1.unlock();
                }
                if (secondLockTaken) {
                    lock2.unlock();
                }
            }
            Thread.sleep(100);
        }
    }


    public void finished() {
        System.out.println(account1.getBalance());
        System.out.println(account2.getBalance());
        System.out.println(account3.getBalance());
        System.out.println("Total balance : " + (account2.getBalance()
                + account1.getBalance()
                + account3.getBalance()));
    }
}

class Account {
    private int balance = 10_000;

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }

    public static void transfer(Account acc1, Account acc2, int amount) {
        acc1.withdraw(amount);
        acc2.deposit(amount);
    }
}