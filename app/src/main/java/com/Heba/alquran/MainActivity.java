package com.Heba.alquran;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.Heba.alquran.Modules.ApiResponse;
import com.Heba.alquran.Modules.Surahs;
import com.Heba.alquran.Networking.GlobalFunctions;
import com.Heba.alquran.Networking.MyAPI;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    public static final int STARTUP_DELAY = 300;
    public static final int ANIM_ITEM_DURATION = 1000;
    public static final int ITEM_DELAY = 300;
    private boolean animationStarted = false;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GetQuranPages();
        GetData();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        if (!hasFocus || animationStarted) {
            return;
        }

        animate();

        super.onWindowFocusChanged(hasFocus);
    }

    private void animate() {
        ImageView logoImageView =  findViewById(R.id.imgSplashLogo);
        ViewGroup container =  findViewById(R.id.container);

        ViewCompat.animate(logoImageView)
                .translationY(-250)
                .setStartDelay(STARTUP_DELAY)
                .setDuration(ANIM_ITEM_DURATION).setInterpolator(
                new DecelerateInterpolator(1.2f)).start();

        ViewPropertyAnimatorCompat viewAnimator = null;
        for (int i = 0; i < container.getChildCount(); i++) {
            View v = container.getChildAt(i);

            if (!(v instanceof Button)) {
                viewAnimator = ViewCompat.animate(v)
                        .translationY(50).alpha(1)
                        .setStartDelay((ITEM_DELAY * i) + 500)
                        .setDuration(1000);
            } else {
                viewAnimator = ViewCompat.animate(v)
                        .scaleY(1).scaleX(1)
                        .setStartDelay((ITEM_DELAY * i) + 500)
                        .setDuration(500);
            }
            viewAnimator.setInterpolator(new DecelerateInterpolator()).start();

        }
        assert viewAnimator != null;
        viewAnimator.setListener(new ViewPropertyAnimatorListener() {
            @Override
            public void onAnimationStart(View view) {
            }
            @Override
            public void onAnimationEnd(View view) {

                    Intent i = new Intent(MainActivity.this, HomePage.class);
                    startActivity(i);
                    finish();}

            @Override
            public void onAnimationCancel(View view) {

            }
        });

    }
    public void GetQuranPages(){
        imageView=findViewById(R.id.image);
        for (int i=0;i<604;i++){
            Glide.with(this).load("https://quran-images-api.herokuapp.com/show/page/"+ i).diskCacheStrategy(DiskCacheStrategy.DATA).into(imageView);

        }
        Log.e("Msg", "All pages saved to disk cach");
    }
    public void GetData() {
        MyAPI myAPI = GlobalFunctions.getAppRetrofit(MainActivity.this).create(MyAPI.class);
        Call<ApiResponse> call = myAPI.GetData(

        );
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response != null) {
                    if (response.body() != null) {
                        if (response.body().getCode().equals("200")) {
                            Log.e("", "Response >> " + new Gson().toJson(response.body()));
                            List<Surahs> surahs = Paper.book().read("quran-surahs");
                            if (surahs == null) {
                                surahs = response.body().getData().getSurahs();
                                Paper.book().write("quran-surahs", surahs);
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Check Internet your connection", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.e("", "body === null");

                    }
                } else {
                    Log.e("", "response === null");


                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


}
