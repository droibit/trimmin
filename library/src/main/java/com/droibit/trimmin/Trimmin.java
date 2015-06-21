package com.droibit.trimmin;

import android.graphics.Bitmap;
import android.graphics.Point;

/**
 * Utility class to trim a {@link Bitmap}.
 *
 * @author kumagai
 * @since 2015/06/19
 */
public final class Trimmin {

    /**
     * Trim to fit the short side of the bitmap. <br>
     * The basic point is the center of the bitmap.
     *
     * @param srcBitmap target bitmap.
     * @param canRecycle whether recycle the target bitmap after trim.
     * @return New bitmap that trimmed.
     */
    public static Bitmap trimSquare(Bitmap srcBitmap, boolean canRecycle) {
        if (srcBitmap == null) {
            return null;
        }

        final int shortSide = Math.min(srcBitmap.getWidth(), srcBitmap.getHeight());
        final int offset = Math.abs(srcBitmap.getWidth() - srcBitmap.getHeight());
        final Point fp;
        if (srcBitmap.getHeight() >= srcBitmap.getWidth()) {
            fp = new Point(0, offset / 2);
        } else {
            fp = new Point(offset / 2, 0);
        }
        final Bitmap destBitmap = Bitmap.createBitmap(srcBitmap, fp.x, fp.y, shortSide, shortSide);

        if (canRecycle) {
            srcBitmap.recycle();
        }
        return destBitmap;
    }
}
