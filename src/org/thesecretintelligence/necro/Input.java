package org.thesecretintelligence.necro;

import org.thesecretintelligence.necro.ssh.Ssh;
import org.thesecretintelligence.necro.utils.Utils;
import org.thesecretintelligence.necro.whois.WhoisObj;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("Duplicates")
public class Input {

    private static String input = null;

    public static void setInput(String input) {
        Input.input = input;
    }

    public static  String getUserInput(){
        return input;
    }

    /**Checks the users input*/
    public static void check(){
        String[] args = getUserInput().split(" ");

        if (args[0].equalsIgnoreCase("clear") || args[0].equalsIgnoreCase("cls")){
            Utils.clearScreen();
            Terminal.getInput();
        }else if(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("?")){
            Terminal.getInput();
        }else if(args[0].equalsIgnoreCase("ssh")){
            if (args.length < 3){
                System.out.println("Correct Usage: ssh [-bf|-l] [host]");
                System.out.println("Options");
                System.out.println("  -bf    START A BRUTEFORCE ATTACK ON THE SPECIFIED HOST");
                Terminal.getInput();
            }
            if (args[1].equalsIgnoreCase("-bf")){
                String host = args[2];
                Terminal.setTerminalPrefix("Necro [Ssh] $> ");
                System.out.print("What is the Ssh Port for the host [" + host + "]:(Default 22) ");
                Scanner scanner = new Scanner(System.in);
                input = scanner.next();
                int port =0;
                try{port = Integer.parseInt(input);}catch (NumberFormatException e){
                    System.out.println("Invalid Port Entered.");
                    Terminal.resetPrefix();
                    Terminal.getInput();
                }
                System.out.println("Attempting to establish a connection to the host.");
                try{
                    Socket socket = new Socket();
                    socket.connect(new InetSocketAddress(host, port), 10000);
                }catch (IOException e){
                    System.out.println("Could not successfully establish a connection to the host.");
                    Terminal.resetPrefix();
                    Terminal.getInput();
                }

                System.out.print("Please enter the absolute path to your password list (type Random if you want us to generate random passwords): ");
                Scanner scanner1 = new Scanner(System.in);
                input = scanner1.next();
                if (input.equalsIgnoreCase("random")){

                }else{
                    File file = new File(input);
                    if (!file.exists()){
                        System.out.println("Could not find the specified file.");
                        Terminal.resetPrefix();
                        Terminal.getInput();
                    }else if (!file.getName().endsWith(".txt")){
                        System.out.println("Please only use password files in .txt format.");
                        Terminal.resetPrefix();
                        Terminal.getInput();
                    }

                    System.out.println("Attempting to fetch the password list...");
                    List<String> passwords = new ArrayList<>();
                    try{passwords = Files.readAllLines(Paths.get("", input));}catch (IOException e){
                        System.out.println("Could not fetch passwords...");
                        Terminal.resetPrefix();
                        Terminal.getInput();
                    }

                    System.out.print("What is the username of the user you want to attempt to bruteforce:(Use root if unknown) ");
                    Scanner scanner2 = new Scanner(System.in);
                    input = scanner2.next();
                    String user = input;
                    Ssh ssh = new Ssh(user, passwords, port, host);
                    ssh.bruteforce();

                }
            }else{
                System.out.println(Lang.ERROR_INVALID_INPUT.getContent().replace("%s%", args[1]));
                Terminal.getInput();
            }
        }else if (args[0].equalsIgnoreCase("whois")) {

            if (args.length == 1) {
                System.out.println("Incorrect Usage of 'whois'");
                System.out.println("whois [host]");
                System.out.println("  -[host]  Host you want to lookup");
                Terminal.getInput();
            }
            WhoisObj.whois(args[1]);
            Terminal.getInput();

        }else if (args[0].equalsIgnoreCase("portscan")){
            if (args.length == 1) {
                System.out.println("Incorrect Usage of 'portscan'");
                System.out.println("portscan [host] <port>");
                System.out.println("  -[host]  Host you want to scan");
                System.out.println("  -<port>  Check a specific port on the network(Optional)");
                Terminal.getInput();
            }else if (args.length == 3){
                System.out.println("feature not released yet");
                Terminal.getInput();
            }else{
                System.out.print("Max Port Range Limit (Ex: If you type 60 it will only scan 60 potential ports) ");
                Scanner scanner = new Scanner(System.in);
                int x=0;
                try{x = Integer.parseInt(scanner.nextLine());}catch (Exception e){
                    System.out.println("Incorrect Number....");
                    Terminal.getInput();
                }
                Network.portscan(args[1], x);
                Terminal.getInput();
            }



        }else if (input.equalsIgnoreCase("exit")){
            System.exit(0);
        }

        System.out.println(Lang.ERROR_INVALID_INPUT.getContent().replace("%s%", input));
        Terminal.getInput();
    }


}
