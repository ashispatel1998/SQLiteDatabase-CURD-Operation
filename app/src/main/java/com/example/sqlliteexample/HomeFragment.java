package com.example.sqlliteexample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment {


    private Button BnSave,BnView,BnDelete,BnUpdate;
    OnDbOperationListener dbOperationListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    //Create an Interface to communicate with the activity
    public interface OnDbOperationListener{
        public void dbOperationPerformed(int method);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        BnSave=view.findViewById(R.id.bn_add_contact);
        BnView=view.findViewById(R.id.bn_view_contact);
        BnDelete=view.findViewById(R.id.bn_delete_contact);
        BnUpdate=view.findViewById(R.id.bn_update_contact);


        BnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbOperationListener.dbOperationPerformed(0);
            }
        });

        BnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbOperationListener.dbOperationPerformed(1);
            }
        });

        BnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbOperationListener.dbOperationPerformed(2);
            }
        });

        BnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbOperationListener.dbOperationPerformed(3);
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Activity activity=(Activity) context;
        try{
            dbOperationListener=(OnDbOperationListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString()+" must implement the interface method!");
        }

    }
}