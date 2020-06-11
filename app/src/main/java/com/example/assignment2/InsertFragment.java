package com.example.assignment2;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple Fragment used to insert Records {@link Fragment}
 */
public class InsertFragment extends Fragment
{
    EditText fName,lName,marks;    //Input fields to read data from user
    Button submit;
    private ListView lstProgCodes;   //ListView to show data
    private ArrayAdapter adaptProgs;  //ArrayAdapter used to set values to ListView
    private String[] progCodes={"PROG8470","PROG8480","PROG8460","PROG8450"};  //ListView Data stored in progCodes
    RadioGroup rg;
    RadioButton rb1,rb2,rb3,rb4;   //Radio Buttons to select credits
    TextView tvSelect,etSelect;    //Selected Course of LisView will be Assigned to these values
    int credits;                  //Selected credit value stored in credits
    String progCode;              //Selected value ListView stored in progCode
    DatabaseHepler dbh;
    boolean insertStat;         //boolean to check whether row inserted or not
    public InsertFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_insert, container, false);
        fName=(EditText)v.findViewById(R.id.etFirstName);
        lName=(EditText)v.findViewById(R.id.etLastName);    //Intialising values
        marks=(EditText)v.findViewById(R.id.etMarks);
        lstProgCodes=(ListView)v.findViewById(R.id.lvProgList);
        rg=(RadioGroup)v.findViewById(R.id.rgCredits);
        rb1=(RadioButton)v.findViewById(R.id.radCredit1);
        rb2=(RadioButton)v.findViewById(R.id.radCredit2);
        rb3=(RadioButton)v.findViewById(R.id.radCredit3);
        rb4=(RadioButton)v.findViewById(R.id.radCredit4);
        submit=(Button)v.findViewById(R.id.btnSubmit);
        etSelect=(TextView) v.findViewById(R.id.etSelect);
        tvSelect=(TextView) v.findViewById(R.id.tvSelect);

        //Instantiating ArrayAdapter
        adaptProgs=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,progCodes);
        lstProgCodes.setAdapter(adaptProgs);                      //Setting data to ListView
        lstProgCodes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                etSelect.setVisibility(View.VISIBLE);        //Making Selected course TexViews to visible
                tvSelect.setVisibility(View.VISIBLE);
                progCode=progCodes[position];          //Assigning Selected Program value to progCode
                etSelect.setText(progCode);

            }
        });
        dbh=new DatabaseHepler(getActivity());

        //OnClick Listener for Submit Button
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int studentID=1;   //Intially setting Student ID value to 1
                if(rb1.isChecked())
                {
                    credits=1;
                    rb1.setChecked(false);
                }
                else if(rb2.isChecked())           //Checking whether radio buttons checked or not
                {                                  //Storing corresponding credit value to credits variable
                    credits=2;
                    rb2.setChecked(false);         //Making radio button to uncheck to make it available for next selection
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
                Student student=new Student(studentID,fName.getText().toString(),lName.getText().toString(),Integer.parseInt(marks.getText().toString()),
                                                progCode,credits);
                insertStat=dbh.insertStudent(student);
                fName.setText(null);
                lName.setText(null);        //Resetting all input widgets after insertion
                marks.setText(null);
                etSelect.setText(null);
                if(insertStat)
                {
                    Toast.makeText(getActivity(),"Grade Entered Successfully",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getActivity(),"Grade Entry failed",Toast.LENGTH_SHORT).show();
                }
            }
        });

       return v;
    }


}
