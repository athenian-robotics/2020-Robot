package frc.robot.lib;

import java.io.Closeable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class CountUpDownGenerator implements Closeable {

    private final AtomicInteger currValue = new AtomicInteger();
    private final AtomicBoolean finished = new AtomicBoolean(false);
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public CountUpDownGenerator(int start, int stop, int pauseMillis) {
        this.executor.submit(
                new Runnable() {
                    @Override
                    public void run() {
                        while (!finished.get()) {
                            for (int i = start; i < stop; i++) {
                                currValue.set(i);
                                if (finished.get())
                                    break;
                                pause(pauseMillis);
                            }

                            for (int i = stop; i > start; i--) {
                                currValue.set(i);
                                if (finished.get())
                                    break;
                                pause(pauseMillis);
                            }
                        }
                    }
                });
    }

    public int getValue() {
        return currValue.intValue();
    }

    @Override
    public void close() {
        executor.shutdownNow();
        finished.set(true);
    }

    private static void pause(int pauseMillis) {
        try {
            Thread.sleep(pauseMillis);
        } catch (InterruptedException e) {
            // Ignore
        }
    }

    public static void main(String[] args) {
        final CountUpDownGenerator generator = new CountUpDownGenerator(0, 10, 500);

        for (int i = 0; i < 25; i++) {
            System.out.println(generator.getValue());
            pause(500);
        }

        generator.close();
    }
}
