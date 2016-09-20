package com.blackhorse.network;

/**
 * @author vrudi
 */
public class Scanner implements NetworkDevice {
    private String name;

    public Scanner(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public NetworkDeviceType getType() {
        return NetworkDeviceType.SCANNER;
    }

}