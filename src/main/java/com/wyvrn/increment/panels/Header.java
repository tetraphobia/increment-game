package com.wyvrn.increment.panels;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Label;

/**
 * A {@link BasicWindow} for the header.
 */
public class Header extends Label {

    public Header(String s) {
        super(s);
    }

    /**
     * Helper function to read an {@link InputStream} into a {@link String}.
     * 
     * @return {@link Header}
     */
    public static Header fromStream(InputStream s) throws FileNotFoundException {
        Scanner scnr = new Scanner(s);
        String banner = scnr.useDelimiter("\\Z").next();
        scnr.close();

        return new Header(banner);
    }
}
