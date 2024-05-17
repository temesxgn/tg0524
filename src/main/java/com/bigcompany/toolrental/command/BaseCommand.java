package com.bigcompany.toolrental.command;

import java.util.Scanner;

public abstract class BaseCommand implements Command {

    protected Scanner scanner;

    public BaseCommand() {
        scanner = new Scanner(System.in);
    }
}
