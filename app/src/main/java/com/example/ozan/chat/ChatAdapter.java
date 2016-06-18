package com.example.ozan.chat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by thales on 2/23/15.
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private List<Chat> mDataSet;
    private String mId;

    private static final int CHAT_RIGHT = 1;
    private static final int CHAT_LEFT = 2;

    /**
     * Inner Class for a recycler view
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;
        private TextView mUsernameView;
        private TextView mTimeView;
        public ImageView mImageView;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) itemView.findViewById(R.id.tvMessage);
            mUsernameView = (TextView)itemView.findViewById(R.id.tvUsername);
            mTimeView = (TextView)itemView.findViewById(R.id.timeStamp);
            mImageView = (ImageView)itemView.findViewById(R.id.imgView);
        }
    }

    /**
     * Called when a view has been clicked.
     *
     * @param dataSet Message list
     * @param id      Device id
     */
    public ChatAdapter(List<Chat> dataSet, String id) {
        mDataSet = dataSet;
        mId = id;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;

        if (viewType == CHAT_RIGHT) {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_chat_right, parent, false);
        } else {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_chat_left, parent, false);
        }

        return new ViewHolder(v);
    }

    @Override
    public int getItemViewType(int position) {
        if (mDataSet.get(position).getId().equals(mId))
            return CHAT_RIGHT;

        return CHAT_LEFT;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        sdf.format(cal.getTime());
        Chat chat = mDataSet.get(position);
        if(chat.getMessage().length() < 10000) {
            holder.mTextView.setVisibility(View.VISIBLE);
            holder.mTextView.setText(chat.getMessage());
            holder.mImageView.setImageBitmap(null);
            holder.mImageView.setVisibility(View.GONE);
        }else{
            holder.mImageView.setVisibility(View.VISIBLE);
            String image = chat.getMessage();
            Bitmap b = StringToBitMap(image);
            holder.mImageView.setImageBitmap(b);
            holder.mTextView.setText(null);
            holder.mTextView.setVisibility(View.GONE);
        }

        holder.mUsernameView.setText(chat.getUsername());
        holder.mTimeView.setText(sdf.format(cal.getTime()));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
    public Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte= Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }
}
