package com.example.assignment2;


import android.database.Cursor;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
/**
 * Sub Fragment to SearchFragment
 * Used to search student records by provided Program Code
 */
public class CodeFragment extends Fragment
{
    ListView programs;                //ListView to show list of programs
    TextView tvSelect,tvSelect1;      //Displaying Selected Course in Texview
    RecyclerView recyclerView1;       //Recycler View shows the list of records for provided Program Code
    CardView recycleCard;             //CardView contains Recycler View
    Button getData;
    String selectedCourse;           //selected program is storing is selectedCourse String
    private ArrayAdapter adaptProgs;  //ArrayAdapter used to set values to List View
    private String[] progCodes={"PROG8470","PROG8480","PROG8460","PROG8450"};  //Storing List View Values in String Array
    List<Student> stuList=new ArrayList<Student>();   //Storing list of students with same Program code in stuList which is used to set values to recycler view
    StudentListAdapter stuAdapter;       //Used to set stuList values to Recycler View
    DatabaseHepler dbh;                  //dbh is instance variable of DatabaseHepler class
    public CodeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_code, container, false);
        programs=(ListView)v.findViewById(R.id.lvProgList);
        tvSelect=(TextView)v.findViewById(R.id.tvSelect);
        tvSelect1=(TextView)v.findViewById(R.id.tvSelect1);              //Intiating values
        recyclerView1=(RecyclerView)v.findViewById(R.id.recyclerView1);
        recycleCard=(CardView)v.findViewById(R.id.recyclerCard);
        getData=(Button)v.findViewById(R.id.btnCGet);
        dbh=new DatabaseHepler(getActivity());

        adaptProgs=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,progCodes); //Instantiating ArrayAdapter
        programs.setAdapter(adaptProgs);                            //Setting data to ListView
        programs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                tvSelect.setVisibility(View.VISIBLE);        //Making Selected course TexViews to visible
                tvSelect1.setVisibility(View.VISIBLE);
                selectedCourse=progCodes[position];     //Assigning Selected Program value to selectedCourse
                tvSelect1.setText(selectedCourse);
            }
        });
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {    //Onclick Function of getData function
                stuList.clear();
                //recyclerView1.setVisibility(View.VISIBLE);   //Making CardView and Recycler View visible to display data
                Cursor cursor=dbh.viewRecordsData(selectedCourse);
                if(cursor.getCount()==0)  //if there are empty records
                {
                    Toast.makeText(getActivity(),"Error : No data Available",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    recycleCard.setVisibility(View.VISIBLE);   //Making CardView and Recycler View visible to display data
                    recyclerView1.setVisibility(View.VISIBLE);
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
                }
            }
        });

        return v;
    }
    //Function used to bind Student data with Recyxler View
    private void bindAdapter()
    {
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView1.setLayoutManager(layoutManager);
        stuAdapter=new StudentListAdapter(stuList,getContext());
        recyclerView1.setAdapter(stuAdapter);
        stuAdapter.notifyDataSetChanged();
    }

}
