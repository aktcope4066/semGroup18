package com.napier.sem;

import java.sql.Connection;

public class db {

    public static void printCountries(Object object) {
        System.out.println("print country place holder");
    }

    private Connection con = null;

    //try connect to db
    public void connect(){
        try{
            //load db driver
        } catch (Exception e){
            //error msg and exit
        }

        //num of retries
        int retry = 2;
        //
        for(int i=0; i<retry; ++i){
            //try con to db
            try {
                //connect to db
            } catch (Exception e) {
                //eroor msg and exit
            }
        }
    }
}
