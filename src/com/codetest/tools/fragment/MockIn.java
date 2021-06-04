package com.codetest.tools.fragment;

import java.io.InputStream;

public class MockIn extends InputStream {
    private StringBuilder sb = new StringBuilder();

    public void type(String str){
        sb.append(str).append(System.getProperty("line.separator"));
    }

    @Override
    public int read() {
        if (sb.length() == 0) {
            return -1;
        }
        int result = sb.charAt(0);
        sb.deleteCharAt(0);
        return result;
    }
}