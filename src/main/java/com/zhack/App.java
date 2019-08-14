package com.zhack;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Hello world!
 *
 */

public class App {

    public static void main(String[] args) throws SocketException {
        // TODO Auto-generated method stub
        DatagramSocket socket= new DatagramSocket(8800);

        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);

        System.out.println(".....服务器准备启动......");
        int count = 0;

        UDPSenderThread udpSenderThread = new UDPSenderThread(socket);
        udpSenderThread.start();

        while(true)
        {

            try {
                socket.receive(packet);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            count++;

            String delay = new String(data, 0, packet.getLength());
            delay = delay.replace("\n", "");
            System.out.println("IP:" + packet.getAddress() + "\t延时" + delay + "次数:" + count);
        }

    }

}

