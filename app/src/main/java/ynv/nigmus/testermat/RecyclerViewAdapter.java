package ynv.nigmus.testermat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerVievVHolder> {

    private ArrayList<ResultRecyclerVievItem> arrayList;

    public static class RecyclerVievVHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView textView0;
        public TextView textView1;

        public RecyclerVievVHolder(@NonNull View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.imageresult);
            textView0 = itemView.findViewById(R.id.textresult);
            textView1 = itemView.findViewById(R.id.texttimer);
        }
    }


    public RecyclerViewAdapter(ArrayList<ResultRecyclerVievItem> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    //@org.jetbrains.annotations.NotNull
    @Override
    public RecyclerVievVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_viev_item
        ,parent,false);

        RecyclerVievVHolder recyclerVievVHolder = new RecyclerVievVHolder(view);
        return recyclerVievVHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerVievVHolder holder, int position) {
        ResultRecyclerVievItem resultRecyclerVievItem = arrayList.get(position);
        holder.imageView.setImageResource(resultRecyclerVievItem.getImageresult());
        holder.textView0.setText(resultRecyclerVievItem.getResult());
        holder.textView1.setText(resultRecyclerVievItem.getTimer());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
