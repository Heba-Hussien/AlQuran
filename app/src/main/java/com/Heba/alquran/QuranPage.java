package com.Heba.alquran;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class QuranPage extends AppCompatActivity {
ImageView imageView;
TextView textView;
int pageNum;

    private float x1,x2;
    static final int MIN_DISTANCE = 150;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran_page);
        imageView=findViewById(R.id.Sora_image);
        textView=findViewById(R.id.pageNum_text);
        pageNum =getIntent().getIntExtra("pageNum",1);
        try {
            Glide.with(this).load("https://quran-images-api.herokuapp.com/show/page/"+ pageNum).diskCacheStrategy(DiskCacheStrategy.DATA).into(imageView);
            textView.setText("- "+pageNum+" -");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;

                if (Math.abs(deltaX) > MIN_DISTANCE)
                {
                    // Left to Right swipe action
                    if (x2 > x1)
                    {
                        //Toast.makeText(this, "Left to Right swipe [Next]", Toast.LENGTH_SHORT).show ();
                        if(pageNum<604)
                        pageNum++;
                        try {
                            Glide.with(this).load("https://quran-images-api.herokuapp.com/show/page/"+ pageNum).diskCacheStrategy(DiskCacheStrategy.DATA).into(imageView);
                            textView.setText("- "+pageNum+" -");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    // Right to left swipe action
                    else
                    {
                        //Toast.makeText(this, "Right to Left swipe [Previous]", Toast.LENGTH_SHORT).show ();
                        if(pageNum>1)
                        pageNum--;
                        try {
                            Glide.with(this).load("https://quran-images-api.herokuapp.com/show/page/"+ pageNum).diskCacheStrategy(DiskCacheStrategy.DATA).into(imageView);
                            textView.setText("- "+pageNum+" -");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                else
                {
                    // consider as something else - a screen tap for example
                }
                break;
        }
        return super.onTouchEvent(event);
    }
}
