package com.blackhorse.network;

/**
 * @author vrudi
 */
public class MobilePhone implements NetworkDevice {
    private String name;

    public MobilePhone(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public NetworkDeviceType getType() {
        return NetworkDeviceType.MOBILE;
    }
}