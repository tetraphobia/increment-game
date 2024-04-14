package com.wyvrn.increment.panels;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

import com.googlecode.lanterna.TextColor;
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
     * Factory method for instantiating Headers from text streams.
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
