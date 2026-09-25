package io.github.asher0913.platformer;

import javafx.application.Platform;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/** Starts the JavaFX runtime once per test JVM, whichever test class asks first. */
public final class FxRuntime {
    private static boolean started;

    private FxRuntime() {}

    public static synchronized void start() throws InterruptedException {
        if (started) {
            return;
        }
        CountDownLatch latch = new CountDownLatch(1);
        try {
            Platform.startup(latch::countDown);
            latch.await(5, TimeUnit.SECONDS);
        } catch (IllegalStateException alreadyRunning) {
            // started elsewhere; nothing to wait for
        }
        started = true;
    }
}
