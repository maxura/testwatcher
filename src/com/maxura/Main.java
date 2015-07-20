package com.maxura;

import java.io.IOException;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        System.out.println(new Watcher().getTimeFromJenkinswatchStateCheck().getTime());
    }
}
