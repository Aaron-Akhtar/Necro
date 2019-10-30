package org.thesecretintelligence.necro;

import org.thesecretintelligence.necro.utils.Utils;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Network {

    public static void portscan(String host, int total){
        int port=0;
        for (int x=0; x < total; x++) {
            try {
                port = x;
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(host, x), 3000);
                System.out.println(Utils.ANSI_GREEN + "[*] " + Utils.ANSI_GREEN + "Port Open ["+port+"]");
            } catch (Exception e) {
                System.out.println(Utils.ANSI_RED + "[-] " + Utils.ANSI_RESET + "Port Closed [" + port + "]");
            }
        }
    }

}
