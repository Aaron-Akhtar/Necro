package org.thesecretintelligence.necro;

import org.thesecretintelligence.necro.utils.Utils;

/**
 * Developed by Aaron Akhtar.
 * Copyright (c) 2019 - The Secret Intelligence Squadron (TheSecretIntelligence.org), All rights reserved.
 * */

public class Necro {

    public static void main(String[] args){
        Utils.clearScreen();
        System.out.println("Necro Terminal - Version 1.0-Alpha (This version is under Early Release, contact me via aaron@stressing.ninja for help)");
        System.out.println(Lang.COPYRIGHT.getContent());
        System.out.println(" ");
        Terminal.getInput();

    }

}
