package com.robgthai.spoon.hellospoon.echo;

public class Echoer {

    private String message;

    public Echoer(String message) {
        this.message = message;
    }


    public String echo() {
        return message;
    }
}
