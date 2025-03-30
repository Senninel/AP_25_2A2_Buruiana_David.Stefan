package org.example;

import java.io.*;
import java.util.*;
import java.util.List;
import java.util.List;
import java.time.LocalDate;
import java.awt.Desktop;

interface Command{
    void execute(String[] args, ImageRepository repo) throws Exception;
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ImageRepository repo = new ImageRepository();

        while(true){
            System.out.println(">> ");
            String line = scanner.nextLine();
            if(line.equals("exit")){
                break;
            }

            String[] tokens = line.split(" ");
            String cmdName = tokens[0];
            String[] cmdArgs = Arrays.copyOfRange(tokens, 1, tokens.length);

            Command cmd = CommandFactory.getCommand(cmdName);
            if(cmd != null){
                try{
                    cmd.execute(cmdArgs, repo);
                }
                catch(IllegalAccessException e){
                    System.out.println("Eroarea argumente: " + e.getMessage());
                }
                catch(IOException e){
                    System.out.println("Eroare IO: " + e.getMessage());
                }
                catch(Exception e){
                    System.out.println("Eroare neasteptata: " + e.getMessage());
                }
            }
            else{
                System.out.println("Comanda nucunsocuta");
            }
        }

    }
}