package com.blackhorse.network;

/**
 * @author vrudi
 */
public interface Printer extends NetworkDevice {
    void print(String someStr);
    void printPhoto(Image photo);
}