package org.ing.hackaton.psdating.util;

import java.io.IOException;

public final class Logger {

    private Logger() {}

    /** TODO */
    public static void log(String s) {
        System.out.println(s);
    }

    public static void log(IOException e) {
        e.printStackTrace();
    }
}
