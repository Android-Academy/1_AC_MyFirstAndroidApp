package com.vullnetlimani.myapplication.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vullnetlimani.myapplication.R;
import com.vullnetlimani.myapplication.util.DBHelper;

public class ListDataCursorAdapter extends RecyclerView.Adapter<ListDataCursorAdapter.ViewHolder> {

    private Context mContext;
    private CursorAdapter mCursorAdapter;
    private ViewHolder holder;

    public ListDataCursorAdapter(Context context, Cursor cursor) {
        this.mContext = context;

        mCursorAdapter = new CursorAdapter(mContext, cursor, 0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(R.layout.data_base_single_item_layout, parent, false);
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void bindView(View view, Context context, Cursor cursor) {

                String id = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_NAME));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_EMAIL));

                holder.title_textView.setText(name);
                holder.email_textView.setText(email);
            }
        };

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = mCursorAdapter.newView(mContext, mCursorAdapter.getCursor(), parent);
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        mCursorAdapter.getCursor().moveToPosition(position);
        mCursorAdapter.bindView(holder.itemView, mContext, mCursorAdapter.getCursor());
    }

    @Override
    public int getItemCount() {
        return mCursorAdapter.getCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title_textView;
        TextView email_textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title_textView = itemView.findViewById(R.id.title_textView);
            email_textView = itemView.findViewById(R.id.email_textView);
        }
    }
}
