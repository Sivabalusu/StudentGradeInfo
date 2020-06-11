package com.example.assignment2;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;


/**
 * A A simple Fragment used to Search Records based on either Student Id or ProgramCode
 */
public class SearchFragment extends Fragment
{
    RadioButton radId,radCode;

    public SearchFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_search, container, false);
        radId=(RadioButton)v.findViewById(R.id.radById);
        radCode=(RadioButton)v.findViewById(R.id.radByProg);
        //Loading Search By Id Fragment on selecting Id Radio Button
        radId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                loadFragment(new SearchidFragment());
            }
        });
        //Loading Search By Code Fragment on selecting Program Code Radio Button
        radCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new CodeFragment());
            }
        });
        return v;
    }
    //Function to load Particular Fragment based on used Button
    private void loadFragment(Fragment fragment)
    {
        FragmentManager fmgr=getChildFragmentManager();
        FragmentTransaction fragtrans=fmgr.beginTransaction();
        fragtrans.replace(R.id.frame1,fragment);
        fragtrans.commit();
    }

}
