package com.example.java_play_ground.service.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TokenCheck {
    Scanner scanner = new Scanner(System.in);

    public boolean lengthChecking(String[] tk, int n) {
        if (tk.length<n) {
            return false;
        }
        else {
            return true;
        }
    }

    public int validIntScanner(){
        int value;
        while (true) {
            try {
                value = scanner.nextInt();
                scanner.nextLine();
                if (value<0){
                    value = (-1)*value;
                }
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input... (type again)");
                scanner.nextLine();
            }
        }
    }

    public float validFloatScanner(){
        float value;
        while (true) {
            try {
                value = scanner.nextFloat();
                scanner.nextLine();
                if (value<0){
                    value = (-1)*value;
                }
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input... (type again)");
                scanner.nextLine();
            }
        }
    }
}
