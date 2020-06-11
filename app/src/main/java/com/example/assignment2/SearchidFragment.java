package com.example.assignment2;


import android.database.Cursor;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Sub Fragment to SearchFragment
 * Used to search student records by provided Student Id
 */
public class SearchidFragment extends Fragment
{
    EditText etid;    //Reads StudentId from user
    TextView fName,lName,marks,progCode,credits;  //Dispalys row data in corresponding TextView
    Button getData;
    CardView searchLayout;    //CardView to show data of record
    int id;
    DatabaseHepler dbh;
    public SearchidFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_searchid, container, false);
        etid=(EditText)v.findViewById(R.id.etsId);
        fName=(TextView)v.findViewById(R.id.tvsFname);
        lName=(TextView)v.findViewById(R.id.tvsLname);
        marks=(TextView)v.findViewById(R.id.tvsMarks);  //Intializing values
        progCode=(TextView)v.findViewById(R.id.tvsPg);
        credits=(TextView)v.findViewById(R.id.tvsCredit);
        searchLayout=(CardView) v.findViewById(R.id.searchData);
        getData=(Button)v.findViewById(R.id.btnSGet);
        dbh=new DatabaseHepler(getActivity());
        //Onclick listener for Get Data Button
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(etid.getText().toString().length()==0)
                {
                    Toast.makeText(getActivity(),"Error:Please enter Id to complete data",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    id=Integer.parseInt(etid.getText().toString());
                    Cursor cursor=dbh.viewRecordData(id);
                    if(cursor.getCount()==0)
                    {
                        Toast.makeText(getActivity(),"Data not available for provided Id",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        searchLayout.setVisibility(View.VISIBLE); //Making CarView to visible to show Record data
                        if(cursor.moveToFirst())
                        {
                            fName.setText(cursor.getString(cursor.getColumnIndex("firstName")));
                            lName.setText(cursor.getString(cursor.getColumnIndex("secondName")));
                            marks.setText(cursor.getString(cursor.getColumnIndex("marks")));      //Setting data to TextViews
                            progCode.setText(cursor.getString(cursor.getColumnIndex("progCode")));
                            credits.setText(cursor.getString(cursor.getColumnIndex("credits")));
                        }
                    }
                }
            }
        });

        return v;
    }

}
