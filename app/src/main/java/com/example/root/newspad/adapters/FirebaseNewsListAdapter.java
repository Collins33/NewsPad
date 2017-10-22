package com.example.root.newspad.adapters;

import android.content.Context;

import com.example.root.newspad.models.News;
import com.example.root.newspad.util.ItemTouchHelperAdapter;
import com.example.root.newspad.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by root on 10/2/17.
 */

public class FirebaseNewsListAdapter extends FirebaseRecyclerAdapter<News, FirebaseNewsViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    //private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FirebaseNewsListAdapter(Class<News> modelClass, int modelLayout,
                                         Class<FirebaseNewsViewHolder> viewHolderClass,
                                         Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        //mOnStartDragListener = onStartDragListener;
        mContext = context;
    }

    @Override
    protected void populateViewHolder(FirebaseNewsViewHolder viewHolder, News model, int position) {
        viewHolder.bindNews(model);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position) {

    }


}
