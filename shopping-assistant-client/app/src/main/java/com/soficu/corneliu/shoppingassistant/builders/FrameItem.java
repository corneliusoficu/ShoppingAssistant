package com.soficu.corneliu.shoppingassistant.builders;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.soficu.corneliu.shoppingassistant.BaseFragmentActivity;
import com.soficu.corneliu.shoppingassistant.BuildConfig;
import com.soficu.corneliu.shoppingassistant.MainActivity;
import com.soficu.corneliu.shoppingassistant.R;
import com.soficu.corneliu.shoppingassistant.entities.Category;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class FrameItem {
    private final ImageView mFrameImage;
    private final TextView mTextView;
    private final FrameLayout mFrameLayout;
    private final Category mCategory;
    private final MainActivity mMainActivity;

    private FrameItem(
            ImageView frameImage,
            TextView textView,
            FrameLayout frameLayout,
            Category category,
            MainActivity activity
    ){
        this.mFrameImage = frameImage;
        this.mTextView = textView;
        this.mFrameLayout = frameLayout;
        this.mCategory = category;
        this.mMainActivity = activity;
    }

    public static class FrameItemBuilder {

        private ImageView imageView;
        private TextView textView;
        private FrameLayout frameLayout;
        private Category category;
        private MainActivity mainActivity;

        public FrameItemBuilder(View parentView, int frameLayoutResourceID) {
            this.frameLayout = parentView.findViewById(frameLayoutResourceID);
        }

        public FrameItemBuilder textHolder(int textViewID) {
            this.textView = frameLayout.findViewById(textViewID);
            return this;
        }

        public FrameItemBuilder imageHolder(int imageViewID) {
            this.imageView = frameLayout.findViewById(imageViewID);
            return this;
        }

        public FrameItemBuilder activity(Activity context) {
            this.mainActivity = (MainActivity) context;
            return this;
        }

        public FrameItemBuilder category(Category category) {
            this.category = category;
            return this;
        }

        public FrameItem build() {
            return new FrameItem(
                    this.imageView,
                    this.textView,
                    this.frameLayout,
                    this.category,
                    mainActivity
            );
        }
    }

    public void display() {
        if(this.mCategory == null) {
            this.mFrameLayout.setVisibility(FrameLayout.INVISIBLE);
            return;
        }

        String categoryName = this.mCategory.getCategoryName();
        String imageURL = BuildConfig.API_URL + this.mCategory.getImageURL();

        if(categoryName != null) {
            this.mTextView.setText(categoryName);
        }

        if(imageURL != null) {
            Picasso.get()
                    .load(imageURL)
                    .placeholder(R.drawable.placeholder)
                    .into(this.mFrameImage);
        } else {
            this.mFrameImage.setImageResource(R.drawable.placeholder);
        }

        setFrameOnClickListener();
    }

    private void setFrameOnClickListener() {
        this.mFrameLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mMainActivity.showFragment("items_selection", new BaseFragmentActivity.FragmentBuilder() {

                    @Override
                    public void addAdditionalData(Bundle fragmentBundle) {
                        fragmentBundle.putSerializable("category", mCategory);
                        return;
                    }
                });
            }
        });
    }
}
