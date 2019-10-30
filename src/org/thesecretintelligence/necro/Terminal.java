package org.thesecretintelligence.necro;

import java.util.Scanner;

public class Terminal {

    private static String terminalPrefix = "Necro $> ";

    public static void resetPrefix(){
        terminalPrefix = "Necro $> ";
    }

    public static String getTerminalPrefix() {
        return terminalPrefix;
    }

    public static void setTerminalPrefix(String terminalPrefix) {
        Terminal.terminalPrefix = terminalPrefix;
    }

    /**Used to get the users input*/
    public static void getInput(){
        System.out.print(terminalPrefix);
        Scanner scanner = new Scanner(System.in);
        Input.setInput(scanner.nextLine());
        Input.check();
    }

}
