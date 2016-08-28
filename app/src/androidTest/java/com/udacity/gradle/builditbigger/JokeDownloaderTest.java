package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

/**
 * Created by Karry on 28-Aug-16.
 */
public class JokeDownloaderTest extends AndroidTestCase implements JokeDelegate{
    private JokeDownloader jokeDownloader;
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        jokeDownloader = new JokeDownloader(this);
    }

    @Override
    public void downloadComplete(String joke) {
        assertNotNull(joke);
        assertEquals(" This is joke ", joke);
    }
}
