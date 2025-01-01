package com.botola.apicmi.utils;

import java.util.Random;

public class GeneratedRef {
    private static final String ref="01234567899876";
    public static String generateRef(){
        Random rd=new Random();
        StringBuilder refRes=new StringBuilder();
        for(int i=0;i<ref.length();i++){
            refRes.append(rd.nextInt(ref.length()));
        }
        return refRes.toString();
    }
}
