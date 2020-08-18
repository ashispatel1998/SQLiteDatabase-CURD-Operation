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


public class DeleteContactFragment extends Fragment {

    private Button btn_delete;
    private EditText contact_id;
    public DeleteContactFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_delete_contact, container, false);
        btn_delete=view.findViewById(R.id.btn_delete);
        contact_id=view.findViewById(R.id.contact_id);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteContact();
            }
        });
        return view;
    }

    private void deleteContact() {
        int id=Integer.parseInt(contact_id.getText().toString().trim());

        ContactDbHelper contactDbHelper=new ContactDbHelper(getActivity());
        SQLiteDatabase database=contactDbHelper.getWritableDatabase();
        contactDbHelper.deleteContact(id,database);
        contactDbHelper.close();
        contact_id.setText("");
        Toast.makeText(getActivity(),"Contact deleted with id :"+id,Toast.LENGTH_SHORT).show();
    }
}