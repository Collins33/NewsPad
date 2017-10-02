package com.example.root.newspad.util;

/**
 * Created by root on 10/2/17.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition,int toPosition);
    void onItemDismiss(int position);
}
