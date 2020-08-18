package com.example.sqlliteexample;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateFragment extends Fragment {


    private EditText txt_contact_id,txt_update_name,txt_update_email;
    private Button btn_update_save;
    public UpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_update, container, false);
        txt_contact_id=view.findViewById(R.id.txt_contact_id);
        txt_update_name=view.findViewById(R.id.txt_update_name);
        txt_update_email=view.findViewById(R.id.txt_update_email);
        btn_update_save=view.findViewById(R.id.btn_update_save);
        btn_update_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateInfo();
            }


        });
        return view;
    }

    private void updateInfo() {
        String txt_id=txt_contact_id.getText().toString().trim();
        String txt_name=txt_update_name.getText().toString().trim();
        String txt_email=txt_update_email.getText().toString().trim();

        ContactDbHelper contactDbHelper=new ContactDbHelper(getActivity());
        SQLiteDatabase database=contactDbHelper.getWritableDatabase();
        contactDbHelper.updateContact(Integer.parseInt(txt_id),txt_name,txt_email,database);
        contactDbHelper.close();

        txt_contact_id.setText("");
        txt_update_name.setText("");
        txt_update_email.setText("");

        Toast.makeText(getActivity(),"Contact Updated with id no :"+txt_id,Toast.LENGTH_LONG).show();
    }
}