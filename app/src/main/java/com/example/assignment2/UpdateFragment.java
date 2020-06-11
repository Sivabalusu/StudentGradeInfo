package com.example.assignment2;


import android.database.Cursor;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple Fragment used to Update Record {@link Fragment}
 */
public class UpdateFragment extends Fragment
{
    EditText etId,etFname,etLname,etMarks;    //Input fields to read data from user
    ListView lstProg;                         //ListView to show data
    TextView tvSelect,etSelect;              //Selected Course of LisView will be Assigned to these values
    private ArrayAdapter adaptProgs;        //ArrayAdapter used to set values to ListView
    private String[] progCodes={"PROG8470","PROG8480","PROG8460","PROG8450"};   //ListView Data stored in progCodes
    Button getData,update;
    CardView upLinear;                     //CardView to show data of record which is to be updated
    DatabaseHepler dbh;
    String program;                 //Selected value ListView stored in program
    RadioButton rb1,rb2,rb3,rb4;    //Radio Buttons to select credits
    int credits;                      //Selected credit value stored in credits
    int id;
    public UpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_update, container, false);
        etId=(EditText)v.findViewById(R.id.etUpId);
        etFname=(EditText)v.findViewById(R.id.etFirstName);
        etLname=(EditText)v.findViewById(R.id.etLastName);
        etMarks=(EditText)v.findViewById(R.id.etMarks);            //Intialising values
        upLinear=(CardView) v.findViewById(R.id.UpData);
        lstProg=(ListView)v.findViewById(R.id.lvUPProgList);
        getData=(Button)v.findViewById(R.id.btnGet);
        update=(Button)v.findViewById(R.id.btnUpdate);
        etSelect=(TextView) v.findViewById(R.id.etSelect);
        tvSelect=(TextView) v.findViewById(R.id.tvSelect);
        rb1=(RadioButton)v.findViewById(R.id.radCredit1);
        rb2=(RadioButton)v.findViewById(R.id.radCredit2);
        rb3=(RadioButton)v.findViewById(R.id.radCredit3);
        rb4=(RadioButton)v.findViewById(R.id.radCredit4);
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
                        Toast.makeText(getActivity(),"No data avaialble for corresponding Id",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        upLinear.setVisibility(View.VISIBLE);      //Making CardView to visible
                        tvSelect.setVisibility(View.VISIBLE);   //Making other widgets to visible
                        etSelect.setVisibility(View.VISIBLE);
                        lstProg.setVisibility(View.VISIBLE);
                        if(cursor.moveToFirst())
                        {
                            etFname.setText(cursor.getString(cursor.getColumnIndex("firstName")));
                            etLname.setText(cursor.getString(cursor.getColumnIndex("secondName"))); //setting text to widgets
                            etSelect.setText(cursor.getString(cursor.getColumnIndex("progCode")));
                            credits=Integer.parseInt(cursor.getString(cursor.getColumnIndex("credits")));
                            if(credits==1)
                            {
                                rb1.setChecked(true);
                            }
                            if(credits==2)
                            {
                                rb2.setChecked(true);
                            }
                            if(credits==3)
                            {
                                rb3.setChecked(true);
                            }
                            if(credits==4)
                            {
                                rb4.setChecked(true);
                            }
                            etMarks.setText(cursor.getString(cursor.getColumnIndex("marks")));
                        }
                        adaptProgs=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,progCodes);
                        lstProg.setAdapter(adaptProgs);
                        lstProg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                            {
                                etSelect.setVisibility(View.VISIBLE);
                                tvSelect.setVisibility(View.VISIBLE);
                                program=progCodes[position];
                                etSelect.setText(program);

                            }
                        });
                        etId.setText(null);
                    }
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(rb1.isChecked())
                {
                    credits=1;
                    rb1.setChecked(false);   ////Making radio button to uncheck to make it available for next selection
                }
                else if(rb2.isChecked())
                {
                    credits=2;
                    rb2.setChecked(false);
                }
                else if(rb3.isChecked())
                {
                    credits=3;
                    rb3.setChecked(false);
                }
                else if(rb4.isChecked())
                {
                    credits=4;
                    rb4.setChecked(false);
                }
                Student student=new Student(id,etFname.getText().toString(),etLname.getText().toString(),
                        Integer.parseInt(etMarks.getText().toString()),
                        etSelect.getText().toString(),credits);
                int numRowsUpdated=dbh.updateStudent(student);
                etFname.setText(null);
                etLname.setText(null);   //Resetting all input widgets after updation
                etMarks.setText(null);
                etSelect.setText(null);
                if(numRowsUpdated>0)
                {
                    Toast.makeText(getActivity(),"Successfully updated ther record",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getActivity(),"Updationg record failed",Toast.LENGTH_LONG).show();
                }
            }
        });
        return v;
    }

}
