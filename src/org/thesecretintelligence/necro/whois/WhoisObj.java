package org.thesecretintelligence.necro.whois;

import org.apache.commons.net.whois.WhoisClient;

public class WhoisObj {

    public static void whois(String host){
        StringBuilder sb = new StringBuilder("");
        WhoisClient client = new WhoisClient();
        try{
            client.connect(WhoisClient.DEFAULT_HOST);
            String[] wd1 = client.query(host).split(">>>");
            sb.append(wd1[0]);
            client.disconnect();
            System.out.println(sb.toString());
        }catch (Exception e){
            System.out.println("Ran into a fatal error while executing whois.");
        }

    }

}
