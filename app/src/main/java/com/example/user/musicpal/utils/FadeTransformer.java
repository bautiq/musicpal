package com.example.user.musicpal.utils;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

public class FadeTransformer implements ViewPager.PageTransformer {

    @Override
    public void transformPage(@NonNull View page, float position) {
        page.setTranslationX(page.getWidth() * -position);

        if (position <= -1 || position >= 1) {
            page.setAlpha(0);
        } else if (position == 0) {
            page.setAlpha(1);
        } else {
            page.setAlpha(1 - Math.abs(position));
        }
    }
}
