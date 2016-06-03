package org.androidtown.bbokbbok_bubble;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by SAMSUNG on 2016-05-28.
 * <p>
 * – There is a grid space ( N * M)
 * – Each cell fills with a Bubble
 * – Each bubble breaks if the user
 * touches the cell
 */
public class BbokBbok extends View {
    private int c_x = 0, c_y = 0;
    private boolean[][] check;
    private Bitmap bubb, pop;
    private int screenWidth, screenHeight;
    private boolean finish;

    public BbokBbok(Context c) {
        super(c);
        init();

        Display display = ((WindowManager) c.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        screenWidth = display.getWidth();
        screenHeight = display.getHeight();
    }


    public BbokBbok(Context c, AttributeSet a) {
        super(c, a);

        init();

        Display display = ((WindowManager) c.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        screenWidth = display.getWidth();
        screenHeight = display.getHeight();
    }

    public void init() {
        check = new boolean[6][11];
        finish = false;

        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 11; j++)
                check[i][j] = true;

        Resources res = getResources();
        bubb = BitmapFactory.decodeResource(res, R.drawable.bb1);//Converting images in the resource folder to Bitmap.
        pop = BitmapFactory.decodeResource(res, R.drawable.bub2);
    }

    protected void onDraw(Canvas canvas) {
        if (finish)
            Toast.makeText(getContext(), "Finish!!", Toast.LENGTH_LONG).show();
        else {
            for (int x = 0; x < 6; x++) {
                for (int y = 0; y < 11; y++) {//The position that bubble to be popped
                    if ((c_x < screenWidth / 6 + x * bubb.getWidth() && c_x > x * bubb.getWidth()) && (c_y < screenHeight / 11 + y * bubb.getHeight() && c_y > y * bubb.getHeight())) {
                        check[x][y] = false;
                        canvas.drawBitmap(pop, x * bubb.getWidth(), y * bubb.getHeight(), null);
                        canvas.save();
                    } else {
                        if (check[x][y])
                            canvas.drawBitmap(bubb, x * bubb.getWidth(), y * bubb.getHeight(), null);
                        else
                            canvas.drawBitmap(pop, x * bubb.getWidth(), y * bubb.getHeight(), null);
                    }
                }
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        c_x = (int) event.getX();
        c_y = (int) event.getY();

        if (event.getAction() == MotionEvent.ACTION_UP) { // When you touch the bubble
            if (checkEnd())
                finish = true;
            invalidate();
        }
        return true;
    }

    private boolean checkEnd() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 11; j++) {
                if (check[i][j])
                    return false;
            }
        }
        return true;
    }

}
