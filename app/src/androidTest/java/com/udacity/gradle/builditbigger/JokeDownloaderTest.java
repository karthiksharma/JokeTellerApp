package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.v4.util.Pair;
import android.test.AndroidTestCase;
import android.test.UiThreadTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


/**
 * Created by Karry on 28-Aug-16.
 */
public class JokeDownloaderTest extends AndroidTestCase implements JokeDelegate{
    private JokeDownloader jokeDownloader;
    CountDownLatch signal;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        signal = new CountDownLatch(1);
        jokeDownloader = new JokeDownloader(this);
    }

    @UiThreadTest
    public void testAsyncTask() throws InterruptedException {
        jokeDownloader.execute(new Pair<Context, String>(getContext(), "Karthik"));
        signal.await(30, TimeUnit.SECONDS);
    }

    @Override
    public void downloadComplete(String joke) {
        signal.countDown();
        assertNotNull(joke);
        assertEquals(" This is joke ", joke);
    }
}
