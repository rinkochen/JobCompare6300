package edu.gatech.seclass.jobcompare6300;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{


    private ArrayList<String> titles = new ArrayList<>();
    private ArrayList<String> companies = new ArrayList<>();
    private ArrayList<Double> scores = new ArrayList<>();
    private ArrayList<Boolean> currents = new ArrayList<>();
    private Context context;
    private ArrayList<Integer> selected = new ArrayList<>();

    public RecyclerViewAdapter(Context context, ArrayList<String> s1, ArrayList<String> s2, ArrayList<Double> s3, ArrayList<Boolean> s4){
        titles = s1;
        companies = s2;
        scores = s3;
        currents = s4;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.title.setText(titles.get(position));
        holder.company.setText(companies.get(position));
        holder.score.setText(String.valueOf(Math.floor(scores.get(position))));
        holder.current.setVisibility(currents.get(position)==true? View.VISIBLE : View.INVISIBLE);
        holder.itemView.setBackgroundColor(selected.contains(position) ? Color.LTGRAY : Color.WHITE);
        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selected.contains(position)){
                    selected.remove(Integer.valueOf(position));
                }else{
                    if (selected.size()<2){
                        selected.add(position);
                    }
                }

                holder.itemView.setBackgroundColor(selected.contains(position) ? Color.LTGRAY : Color.WHITE);
            }
        }
        );
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView company;
        TextView score;
        TextView current;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.rowTitleID);
            company = itemView.findViewById(R.id.rowCompanyID);
            score = itemView.findViewById(R.id.rowScoreID);
            current = itemView.findViewById(R.id.rowCurrentID);
        }


    }

    public ArrayList<Integer> getSelected() {
        return selected;
    }
}
