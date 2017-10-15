package com.cse.mist.bookstack;

/**
 * Created by User on 9/25/2017.
 */

public class ParseEmail {

    String s;

    public String Parse(String email) {
        s = "";
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '.') {
                s = s + ":";
            } else s = s + email.charAt(i);
        }
        return s;
    }
}
