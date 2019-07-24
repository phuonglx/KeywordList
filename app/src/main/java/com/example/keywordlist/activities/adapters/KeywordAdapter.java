package com.example.keywordlist.activities.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.keywordlist.R;
import com.example.keywordlist.models.Keyword;
import com.example.keywordlist.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class KeywordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Keyword> mItems = new ArrayList<>();
    private LayoutInflater mInflater;

    public KeywordAdapter(Context context) {
        if (context == null) return;

        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_keyword, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setData(List<Keyword> items) {
        mItems.clear();

        if (items != null && !items.isEmpty()) {
            mItems.addAll(items);
            notifyDataSetChanged();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvKeyword;

        ViewHolder(View view) {
            super(view);

            tvKeyword = view.findViewById(R.id.tv_keyword);

        }

        void bind(Keyword item) {
            if (item == null
                    || item.getKeyword() == null
                    || item.getKeyword().isEmpty()) {
                return;
            }

            tvKeyword.setText(Utils.splitKeyword(item.getKeyword()));

            GradientDrawable drawable = (GradientDrawable) tvKeyword.getBackground();
            drawable.setColor(Color.parseColor(item.getColor()));
        }

    }

}
