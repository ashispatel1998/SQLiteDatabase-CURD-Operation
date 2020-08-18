package com.example.sqlliteexample;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContactFragment extends Fragment {

    private Button bnSave;
    EditText id,name,email;

    public AddContactFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_contact, container, false);
        bnSave=view.findViewById(R.id.btn_add);
        id=view.findViewById(R.id.contact_id);
        name=view.findViewById(R.id.name);
        email=view.findViewById(R.id.email);
        bnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To add the data into database we need to create the object of sqliteopenhelper class

                String temp_id=id.getText().toString().trim();
                String temp_name=name.getText().toString().trim();
                String trmp_email=email.getText().toString().trim();

                //object
                ContactDbHelper contactDbHelper=new ContactDbHelper(getActivity());
                SQLiteDatabase database=contactDbHelper.getWritableDatabase();
                contactDbHelper.addContact(Integer.parseInt(temp_id),temp_name,trmp_email,database);
                contactDbHelper.close();

                id.setText("");
                name.setText("");
                email.setText("");

                Toast.makeText(getActivity(),"Contact added",Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}