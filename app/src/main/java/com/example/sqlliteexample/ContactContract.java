package com.example.sqlliteexample;

public class ContactContract  {

    // Constructur
    private ContactContract(){

    }

    //Innner Class
    public class ContactEntry{

        //Specify Database Schema

        //Table Name
        public static final String TABLE_NAME="contact_info";
        //First Column name : id
        public static final String CONTACT_ID="contact_id";
        //Second Column name : name
        public static final String NAME="name";
        //Third Column name : email
        public static final String EMAIL="email";

    }

}
