package com.star.amlakonline.Model;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.star.amlakonline.R;

import java.util.ArrayList;
import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ItemViewHolder> {
    Context context;
    private List<File> files = new ArrayList<>();

    public void addFiles(List<File> f) {
        files.addAll(f);
        notifyDataSetChanged();
    }

    public FileAdapter(Context context, List<File> files)
    {
        this.context = context;
        this.files = files;
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        File file = files.get(position);
        holder.tv.setText(file.code+"");
    }

    @Override
    public int getItemCount() {
        return files.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.fileCode);
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/BNazanin.ttf");
            tv.setTypeface(typeface);

        }
    }
}
