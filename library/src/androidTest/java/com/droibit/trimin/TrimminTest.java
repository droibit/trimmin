package com.droibit.trimin;


import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test the {@link Trimmin} class.
 *
 * @auther kumagai
 * @since 15/06/19
 */
@SmallTest
@RunWith(AndroidJUnit4.class)
public class TrimminTest extends AndroidTestCase {

    /** {@inheritDoc} */
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /** {@inheritDoc} */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testTrimSquare() throws Exception {
        final Bitmap nullBitmap = Trimmin.trimSquare(null, false);
        assertNull(nullBitmap);

        // Long height bitmap
        final Bitmap srcBitmapLongHeight = Bitmap.createBitmap(1920, 1080, Config.ARGB_8888);
        final Bitmap resultBitmapLongHeight = Trimmin.trimSquare(srcBitmapLongHeight, true);

        assertNotNull(resultBitmapLongHeight);

        assertEquals(resultBitmapLongHeight.getWidth(), 1080);
        assertEquals(resultBitmapLongHeight.getHeight(), 1080);

        assertEquals(srcBitmapLongHeight.isRecycled(), true);
        resultBitmapLongHeight.recycle();


        // Long width bitmap
        final Bitmap srcBitmapLongWidth = Bitmap.createBitmap(1280, 720, Config.ARGB_8888);
        final Bitmap resultBitmapLongWidth = Trimmin.trimSquare(srcBitmapLongWidth, false);

        assertNotNull(resultBitmapLongWidth);

        assertEquals(resultBitmapLongWidth.getWidth(), 720);
        assertEquals(resultBitmapLongWidth.getHeight(), 720);

        assertEquals(srcBitmapLongWidth.isRecycled(), false);
        srcBitmapLongWidth.recycle();
        assertEquals(srcBitmapLongWidth.isRecycled(), true);
        resultBitmapLongWidth.recycle();


        // Square bitmap
        final Bitmap srcBitmapSquare = Bitmap.createBitmap(600, 600, Config.ARGB_8888);
        final Bitmap resultBitmapSquare = Trimmin.trimSquare(srcBitmapSquare, true);

        assertNotNull(resultBitmapSquare);

        assertEquals(resultBitmapSquare.getWidth(), 600);
        assertEquals(resultBitmapSquare.getHeight(), 600);

        assertEquals(srcBitmapSquare.isRecycled(), true);
        resultBitmapSquare.recycle();
    }
}