package com.example.cs496_week4.AdapterListener;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public abstract class LongPressItemTouchListener extends RecyclerView.SimpleOnItemTouchListener {

    private final GestureDetector mGestureDetector;
    protected final OnItemInteractionListener mListener;
    protected TimeTableAdapter.ViewHolder mViewHolderLongPressed;
    public TimeTableAdapter.ViewHolder mViewHolderInFocus;

    public interface OnItemInteractionListener {
        void onLongItemClicked(RecyclerView recyclerView, TimeTableAdapter.ViewHolder mViewHolderTouched, int position);
        void onItemClicked(RecyclerView recyclerView, TimeTableAdapter.ViewHolder mViewHolderTouched, int position);
        void onViewHolderHovered(RecyclerView rv, TimeTableAdapter.ViewHolder viewHolder);
        void onMultipleViewHoldersSelected(RecyclerView rv, ArrayList<TimeTableAdapter.ViewHolder> selection);
    }

    public LongPressItemTouchListener(Context context, OnItemInteractionListener listener) {
        mGestureDetector = new GestureDetector(context, new LongPressGestureListener());
        mGestureDetector.setIsLongpressEnabled(true);
        mListener = listener;
    }


    public boolean onLongPressedEvent(RecyclerView rv, MotionEvent e) {

        if (mViewHolderLongPressed != null) {
            return false;
            // long pressed happened, my job here is done.
        }
        View childViewUnder = rv.findChildViewUnder(e.getX(), e.getY());
        if (childViewUnder != null) {
            mViewHolderInFocus = (TimeTableAdapter.ViewHolder) rv.findContainingViewHolder(childViewUnder);
            if (mGestureDetector.onTouchEvent(e) && mListener != null) {
                mListener.onItemClicked(rv, mViewHolderInFocus, rv.getChildAdapterPosition(childViewUnder));
            }
            return mViewHolderLongPressed != null;
        }
        return false;
    }

    class LongPressGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            if (mViewHolderInFocus != null && mListener != null) {
                RecyclerView recyclerView = (RecyclerView) mViewHolderInFocus.itemView.getParent();
                mListener.onLongItemClicked(recyclerView, mViewHolderInFocus, mViewHolderInFocus.getAdapterPosition());
                mViewHolderLongPressed = mViewHolderInFocus;
            }
        }
    }
}