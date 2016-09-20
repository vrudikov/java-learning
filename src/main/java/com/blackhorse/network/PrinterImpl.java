package com.blackhorse.network;

/**
 * @author vrudi
 */
public class PrinterImpl implements Printer {
    private String name;
    private boolean canPrintPhoto;

    public PrinterImpl(String name, boolean canPrintPhoto) {
        this.name = name;
        this.canPrintPhoto = canPrintPhoto;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public NetworkDeviceType getType() {
        return NetworkDeviceType.PRINTER;
    }

    public void print(String someStr) {
        System.out.println(getName() + ": " + someStr);
    }

    @Override
    public void printPhoto(Image photo) {
        if (canPrintPhoto) {
            System.out.println(getName() + ": Printing image...");
        } else {
            throw new IllegalStateException(getName() + ": Can't print images");
        }
    }
}
