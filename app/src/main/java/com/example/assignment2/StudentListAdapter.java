package com.example.assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//Adapter to set List Values to Recycler View
public class StudentListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private List<Student> mList;
    public StudentListAdapter(List<Student> list, Context context)
    {
        super();
        mList=list;
    }
    class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tvId;
        public TextView tvFirst;
        public TextView tvLast;    //Declaring variables to all TextViews used in record_layout
        public TextView tvMarks;
        public TextView tvPgCode;
        public TextView tvCredits;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tvId=(TextView)itemView.findViewById(R.id.tvID);
            tvFirst=(TextView)itemView.findViewById(R.id.tvFname);  //Intialising values
            tvLast=(TextView)itemView.findViewById(R.id.tvLname);
            tvMarks=(TextView)itemView.findViewById(R.id.tvMark);
            tvPgCode=(TextView)itemView.findViewById(R.id.tvProgram);
            tvCredits=(TextView)itemView.findViewById(R.id.tvCredit);
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.record_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        Student stuAdapter=mList.get(position);
        ((ViewHolder) holder).tvId.setText(Integer.toString(stuAdapter.getStudeentId()));
        ((ViewHolder) holder).tvFirst.setText(stuAdapter.getFname());                     //Setting values to Texviews
        ((ViewHolder) holder).tvLast.setText(stuAdapter.getLname());
        ((ViewHolder) holder).tvMarks.setText(Integer.toString(stuAdapter.getMarks()));
        ((ViewHolder) holder).tvPgCode.setText(stuAdapter.getProgCode());
        ((ViewHolder) holder).tvCredits.setText(Integer.toString(stuAdapter.getCredits()));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
