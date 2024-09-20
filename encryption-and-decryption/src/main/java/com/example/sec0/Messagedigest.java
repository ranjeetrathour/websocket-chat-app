package com.example.sec0;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Messagedigest {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        Scanner sc = new Scanner(System.in);
        MessageDigest digest = MessageDigest.getInstance("SHA-256");  //defining an hash function

        System.out.println("enter your message");
        String message = sc.nextLine();

        digest.update(message.getBytes());

        byte[] di = digest.digest(); //it return an hash value on given input after performing hashing operation

        StringBuffer decimal = new StringBuffer();
        for (int i=0;i<di.length;i++) {
            decimal.append(Integer.toString(0xf & di[i]));
        }
        System.out.println("Hex format : " + decimal.toString());

    }
}
