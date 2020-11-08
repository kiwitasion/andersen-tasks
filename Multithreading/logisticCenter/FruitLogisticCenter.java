package Multithreading.logisticCenter;

import java.util.LinkedList;
import java.util.Queue;

public class FruitLogisticCenter {

    private Queue<FruitBox> queue = new LinkedList<>();
    private int capacity;

    public FruitLogisticCenter(int capacity) {
        this.capacity = capacity;
    }


    public synchronized void unload(FruitBox fruitBox) throws InterruptedException {

        while (queue.size() == capacity){
            System.out.println(Thread.currentThread().getName() + ": not enough space, i will wait");
            wait();
            System.out.println(Thread.currentThread().getName() + ": space appeared");
        }
        queue.offer(fruitBox);
        notifyAll();

    }

    public synchronized FruitBox load() throws InterruptedException {
        while (queue.isEmpty()){
            System.out.println(Thread.currentThread().getName() + ": nothing to take, i will wait");
            wait();
            System.out.println(Thread.currentThread().getName() + ": something appeared");
        }
        notifyAll();
        return queue.poll();
    }

    public static void main(String[] args) throws InterruptedException {
        FruitLogisticCenter center = new FruitLogisticCenter(100);
        FruitBox bananaBox = new FruitBox(100, "Bananas");
        FruitBox appleBox = new FruitBox(150, "Apples");

        Unloader unloader1 = new Unloader(center, bananaBox, 1000);
        Unloader unloader2 = new Unloader(center, appleBox, 500);
        Loader loader1 = new Loader(center);
        Loader loader2 = new Loader(center);

        Thread ul1 = new Thread(unloader1);
        ul1.setName("Unloader 1");

        Thread ul2 = new Thread(unloader2);
        ul2.setName("Unloader 2");

        Thread l1 = new Thread(loader1);
        l1.setName("Loader 1");

        Thread l2 = new Thread(loader2);
        l2.setName("Loader 2");

        ul1.start();
        ul2.start();
        l1.start();
        l2.start();

        ul1.join();
        ul2.join();

        center.unload(null);//Flag for first loader
        center.unload(null);//Flag for second loader


    }
}
