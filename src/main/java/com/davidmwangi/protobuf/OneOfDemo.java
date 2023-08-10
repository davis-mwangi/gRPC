package com.davidmwangi.protobuf;

import com.davidmwangi.models.Credentials;
import com.davidmwangi.models.EmailCredentials;
import com.davidmwangi.models.PhoneOTP;

public class OneOfDemo {
    public static void main(String[] args) {
        EmailCredentials emailCredentials =  EmailCredentials.newBuilder()
                .setEmail("nobody@gmail.com")
                .setPassword("admin123")
                .build();

        PhoneOTP phoneMode = PhoneOTP.newBuilder()
                .setCode(383783784)
                .setNumber(3839839)
                .build();

        //Takes the last one been set
        Credentials credentials = Credentials.newBuilder()
                .setEmailMode(emailCredentials)
                .setPhoneMode(phoneMode)
                .build();
        login(credentials);
    }
    public static void login(Credentials credentials){
        switch (credentials.getModeCase()){
            case EMAILMODE:
                System.out.println(credentials.getEmailMode());
                break;
            case PHONEMODE:
                System.out.println(credentials.getPhoneMode());
                break;
        }
        System.out.println(credentials.getEmailMode());
    }
}
