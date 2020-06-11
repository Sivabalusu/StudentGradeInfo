package com.example.assignment2;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
/**
 * A simple {@link Fragment} subclass.
 */
public class ViewFragment extends Fragment
{
    private RecyclerView recyclerView;   //Recycler view to show records
    private List<Student> stuList=new ArrayList<Student>();  //Storing all records in List
    private StudentListAdapter stuAdapter;    //Adapter to set Recycler
    DatabaseHepler dbh;
    public ViewFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_view, container, false);
        recyclerView=(RecyclerView)v.findViewById(R.id.recyclerView); //Intiating values
        dbh=new DatabaseHepler(getActivity());
        Cursor cursor=dbh.viewData();

        if(cursor.getCount()==0)
        {
            Toast.makeText(getActivity(),"No Record",Toast.LENGTH_SHORT).show();
            return v;
        }
        else
        {
            if(cursor.moveToFirst())
            {
                do {
                    //Setting properties of student class
                    Student student = new Student();
                    student.setStudeentId(cursor.getInt(cursor.getColumnIndex("id")));
                    student.setFname(cursor.getString(cursor.getColumnIndex("firstName")));
                    student.setLname(cursor.getString(cursor.getColumnIndex("secondName")));
                    student.setMarks(cursor.getInt(cursor.getColumnIndex("marks")));
                    student.setProgCode(cursor.getString(cursor.getColumnIndex("progCode")));
                    student.setCredits(cursor.getInt(cursor.getColumnIndex("credits")));
                    stuList.add(student);

                }while (cursor.moveToNext());

            }
            cursor.close();
            dbh.close();
            bindAdapter();  //Function used to bind Student data with Recyxler View
            return  v;
        }


    }
    //Function used to bind Student data with Recyxler View
    private void bindAdapter()
    {
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        stuAdapter=new StudentListAdapter(stuList,getContext());
        recyclerView.setAdapter(stuAdapter);
        stuAdapter.notifyDataSetChanged();
    }

}
