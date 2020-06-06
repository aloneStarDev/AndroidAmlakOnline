package com.star.amlakonline.Model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.star.amlakonline.IndexActivity;
import com.star.amlakonline.MainActivity;
import com.star.amlakonline.R;
import com.star.amlakonline.StartActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.Serializable;
import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ItemViewHolder> {
    Context context;
    private List<File> files;
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
        holder.addressPu.setText(" آدرس: "+file.addressPu+" ");
        if(file.buy != 0){
            holder.ejare.setVisibility(View.GONE);
            holder.rahn.setVisibility(View.GONE);
            String text = " خرید: "+file.getPrice(file.buy)+" ";
            holder.buy.setVisibility(View.VISIBLE);
            holder.buy.setText(text);
        }else{
            holder.buy.setVisibility(View.GONE);
            if(file.ejare == 0)
                holder.ejare.setVisibility(View.GONE);
            else{
                holder.ejare.setVisibility(View.VISIBLE);
                holder.ejare.setText(" اجاره: "+file.getPrice(file.ejare)+" ");
            }
            if(file.rahn != 0){
                holder.rahn.setVisibility(View.VISIBLE);
                holder.rahn.setText(" رهن: "+file.getPrice(file.rahn)+" ");
            }
        }
        holder.title.setText(file.getTitle());
        holder.code = file.code;
    }

    @Override
    public int getItemCount() {
        return files.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView addressPu;
        TextView rahn;
        TextView ejare;
        TextView buy;
        int code;
    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        addressPu = itemView.findViewById(R.id.addressPu);
        title = itemView.findViewById(R.id.title);
        rahn = itemView.findViewById(R.id.rahn);
        ejare = itemView.findViewById(R.id.ejare);
        buy = itemView.findViewById(R.id.buy);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/BNazanin.ttf");
        addressPu.setTypeface(typeface);
        title.setTypeface(typeface);
        rahn.setTypeface(typeface);
        ejare.setTypeface(typeface);
        buy.setTypeface(typeface);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.mainActivity, IndexActivity.class);
                File f = null;
                for (File x:files)
                    if(x.code == code)
                        f = x;
                Gson g = new Gson();
                intent.putExtra("file",g.toJson(f,File.class));
                context.startActivity(intent);
            }
        });
        }
    }
}
