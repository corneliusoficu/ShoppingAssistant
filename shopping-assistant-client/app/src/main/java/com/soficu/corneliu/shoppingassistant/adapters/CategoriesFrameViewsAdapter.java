package com.soficu.corneliu.shoppingassistant.adapters;

import android.app.Activity;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soficu.corneliu.shoppingassistant.R;
import com.soficu.corneliu.shoppingassistant.builders.FrameItem;
import com.soficu.corneliu.shoppingassistant.entities.Category;
import com.soficu.corneliu.shoppingassistant.helpers.ArraysHelper;

import java.util.List;

public class CategoriesFrameViewsAdapter extends FrameViewsAdapter<Pair<Category,Category>> {

    public CategoriesFrameViewsAdapter(Activity mContext, List<Category> items) {
        super(mContext, ArraysHelper.convertToPairs(items));
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = mContext.getLayoutInflater();

        if(view == null) {
            view = inflater.inflate(R.layout.frame_view_list_item, viewGroup, false);

            Pair<Category, Category> categories = (Pair<Category, Category>) getItem(position);

            FrameItem firstFrame = new FrameItem.FrameItemBuilder(view, R.id.first_frame_layout)
                    .textHolder(R.id.frame_view_item_first_text_view)
                    .imageHolder(R.id.frame_view_item_first_image)
                    .category(categories.first).build();

            FrameItem secondFrame = new FrameItem.FrameItemBuilder(view, R.id.second_frame_layout)
                    .textHolder(R.id.frame_view_item_second_text_view)
                    .imageHolder(R.id.frame_view_item_second_image)
                    .category(categories.second).build();

            firstFrame.display();
            secondFrame.display();
        }

        return view;
    }
}
