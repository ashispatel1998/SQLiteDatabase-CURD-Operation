package com.example.sqlliteexample;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ReadContactFragment extends Fragment {

    private TextView txt_display;

    public ReadContactFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_read_contact, container, false);
        txt_display=view.findViewById(R.id.txt_display);
        readContact();
        return view;
    }

    //Read contact details
    private void readContact(){
        ContactDbHelper contactDbHelper=new ContactDbHelper(getActivity());
        SQLiteDatabase database=contactDbHelper.getReadableDatabase();

        Cursor cursor=contactDbHelper.readContacts(database);
        String info="";
        while (cursor.moveToNext()){
            String id=Integer.toString(cursor.getInt(cursor.getColumnIndex(ContactContract.ContactEntry.CONTACT_ID)));
            String name=cursor.getString(cursor.getColumnIndex(ContactContract.ContactEntry.NAME));
            String email=cursor.getString(cursor.getColumnIndex(ContactContract.ContactEntry.EMAIL));
            info= info+"\n\n"+"id :"+id+"\n"+"Name :"+name+"\n"+"Email :"+email;
        }
        txt_display.setText(info);
        contactDbHelper.close();
    }
}