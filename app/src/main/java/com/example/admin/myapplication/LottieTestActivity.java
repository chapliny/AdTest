package com.example.admin.myapplication;

import android.animation.Animator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.OnCompositionLoadedListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by maraonda admin on 2017/3/28.
 */

public class LottieTestActivity extends Activity{

    private final Map<String, String> assetFolders = new HashMap<String, String>() {{
        put("qiandai.json", "images");
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie);
        LottieAnimationView anim_1 = (LottieAnimationView)findViewById(R.id.animation_view);
//        final String assetName = data.getStringExtra(EXTRA_ANIMATION_NAME);
        final String assetName = "qiandai.json";
        anim_1.setImageAssetsFolder(assetFolders.get(assetName));
        LottieComposition.Factory.fromAssetFileName(this, assetName,
                new OnCompositionLoadedListener() {
                    @Override
                    public void onCompositionLoaded(LottieComposition composition) {
//                        setComposition(composition, assetName);
                    }
                });


        anim_1.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });


//        anim_1.setAnimation("zuoyou.json");
//        anim_1.setAnimation("data.json");
        anim_1.setAnimation("qiandai.json");
        anim_1.loop(true);
        anim_1.playAnimation();


//        LottieComposition.Factory.fromJson(getResources(), new JSONObject(), new OnCompositionLoadedListener() {
//            @Override
//            public void onCompositionLoaded(LottieComposition composition) {
//
//            }
//        });

    }

    private void setComposition(LottieComposition composition, String name) {
//        instructionsContainer.setVisibility(View.GONE);
//        seekBar.setProgress(0);
//        animationView.setComposition(composition);
//        animationNameView.setText(name);
    }

}
