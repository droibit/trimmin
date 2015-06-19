package com.droibit.trimmin.app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.droibit.trimin.Trimmin;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    private Bitmap mOriginBitmap;
    private Bitmap mTrimmedBitmap;

    /** {@inheritDoc} */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOriginBitmap = loadBitmap();
        mTrimmedBitmap = trimBitmap(mOriginBitmap);

        mImageView = (ImageView) findViewById(R.id.image);
        mImageView.setImageBitmap(mOriginBitmap);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                final BitmapDrawable bd = (BitmapDrawable) mImageView.getDrawable();
                if (bd.getBitmap() == mOriginBitmap) {
                    mImageView.setImageBitmap(mTrimmedBitmap);
                } else {
                    mImageView.setImageBitmap(mOriginBitmap);
                }
            }
        });
    }

    private Bitmap loadBitmap() {
        try {
            final InputStream is = getResources().getAssets().open("image01.png");
            return BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Bitmap trimBitmap(Bitmap srcBitmap) {
        final Bitmap trimmedBitmap = Trimmin.trimSquare(mOriginBitmap, false);

        final Bitmap newBitmap = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(newBitmap);
        final int offset = Math.abs(srcBitmap.getWidth() - srcBitmap.getHeight());
        final Point fp;
        if (srcBitmap.getHeight() >= srcBitmap.getWidth()) {
            fp = new Point(0, offset / 2);
        } else {
            fp = new Point(offset / 2, 0);
        }
        canvas.drawBitmap(trimmedBitmap, fp.x, fp.y, null);
        trimmedBitmap.recycle();

        return newBitmap;
    }

    /** {@inheritDoc} */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /** {@inheritDoc} */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
