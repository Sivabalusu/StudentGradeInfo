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
 * A Fragment to show delete Operation.
 */
public class DeleteFragment extends Fragment
{
    EditText etId;        //Reads StudentId from user
    TextView tvFname,tvLname,tvMarks,tvProgCode,tvCredits;  //Dispalys row data in corresponding TextView
    Button getData,delete;  //Corresponding Buttons to do Operations
    CardView delLinear;     //CardView to show data of record which is to be deleted
    DatabaseHepler dbh;     //dbh is instance variable of DatabaseHepler class
    int id;
    public DeleteFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_delete, container, false);
        etId=(EditText) v.findViewById(R.id.etDlId);
        tvFname=(TextView) v.findViewById(R.id.tvdFirstName);
        tvLname=(TextView) v.findViewById(R.id.tvdLastName);  //Intializing values
        tvMarks=(TextView) v.findViewById(R.id.tvdMarks);
        tvProgCode=(TextView) v.findViewById(R.id.tvdProg);
        tvCredits=(TextView) v.findViewById(R.id.tvdCredit);
        delLinear=(CardView) v.findViewById(R.id.delData);
        getData=(Button)v.findViewById(R.id.btndGet);
        delete=(Button)v.findViewById(R.id.btnDelete);
        dbh=new DatabaseHepler(getActivity());
        //Onclick listener for Get Data Button
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(etId.getText().toString().length()==0)
                {
                    Toast.makeText(getActivity(),"Error:Please enter Id to complete data",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    id=Integer.parseInt(etId.getText().toString());
                    Cursor cursor=dbh.viewRecordData(id);
                    if(cursor.getCount()==0)
                    {
                        Toast.makeText(getActivity(),"Data not available for provided Id",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        delLinear.setVisibility(View.VISIBLE);   //Making CarView to visible to show Record data
                        if(cursor.moveToFirst())
                        {
                            //Assigning values to TextViewa
                            tvFname.setText(cursor.getString(cursor.getColumnIndex("firstName")));
                            tvLname.setText(cursor.getString(cursor.getColumnIndex("secondName")));
                            tvMarks.setText(cursor.getString(cursor.getColumnIndex("marks")));
                            tvProgCode.setText(cursor.getString(cursor.getColumnIndex("progCode")));
                            tvCredits.setText(cursor.getString(cursor.getColumnIndex("credits")));
                        }
                    }
                }
            }
        });

        //onclick listener for delete function
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int numRowsDel=dbh.deleteRecord(id);
                //making CarView to invisible to after deleting record
                delLinear.setVisibility(View.INVISIBLE);
                if(numRowsDel>0)
                {
                    Toast.makeText(getContext(),"Student Record Successfully deleted",Toast.LENGTH_SHORT).show();
                }
                if(numRowsDel==0)
                {
                    Toast.makeText(getContext(),"Record does not exist",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }

}
