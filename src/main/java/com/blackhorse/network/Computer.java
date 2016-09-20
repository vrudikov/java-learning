package com.blackhorse.network;

/**
 * @author vrudi
 */
public class Computer implements NetworkDevice {
    private String name;

    public Computer(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public NetworkDeviceType getType() {
        return NetworkDeviceType.PC;
    }
}
