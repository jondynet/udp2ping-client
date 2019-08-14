package com.zhack;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UDPSenderThread extends Thread{
    DatagramSocket socket = null;

    public UDPSenderThread(DatagramSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        super.run();
        while (true) {
            this.ping("172.16.101.200", "192.168.11.5");
            this.ping("172.16.101.201", "192.168.11.5");
            this.ping("172.16.101.202", "192.168.11.5");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void ping(String bridge, String target) {
        byte[] data =	("ping " + target).getBytes();
        DatagramPacket datagramPacket =  new DatagramPacket(data, data.length, new InetSocketAddress(bridge, 1234));

        try {
            this.socket.send(datagramPacket);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
