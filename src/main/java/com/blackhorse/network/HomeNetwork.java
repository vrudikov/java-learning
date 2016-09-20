package com.blackhorse.network;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vrudi
 */
public class HomeNetwork {
    private List<NetworkDevice> devices = new ArrayList<NetworkDevice>();

    private List<NetworkDevice> getDeviceList() {
        return devices;
    }

    public void printStatus() {
        for (NetworkDevice device : devices) {
            System.out.println("Device[name=" + device.getName() + ", type=" + device.getType() + "]");
        }
    }




    public static void main(String[] args) {
        HomeNetwork network = new HomeNetwork();
        network.getDeviceList().add(new Computer("Val's Macbook"));
        network.getDeviceList().add(new PrinterImpl("HP 4340", true));
        network.getDeviceList().add(new PrinterImpl("Samsung 5566", false));
        network.getDeviceList().add(new Scanner("Zyxel 456"));
        network.getDeviceList().add(new MobilePhone("Nokia 3230"));
        network.getDeviceList().add(new Tablet("Samsung Galaxy T3"));

        network.printStatus();

        for (NetworkDevice nd : network.getDeviceList()) {
            if (nd.getType().equals(NetworkDeviceType.PRINTER)) {
                ((PrinterImpl) nd).print("Hey hahsa");
                ((PrinterImpl) nd).printPhoto(new Image());
            }
        }
    }

}