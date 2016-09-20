package com.blackhorse.network;

/**
 * @author vrudi
 */
public class Tablet implements NetworkDevice {
    private String name;

    public Tablet(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public NetworkDeviceType getType() {
        return NetworkDeviceType.TABLET;
    }
}
