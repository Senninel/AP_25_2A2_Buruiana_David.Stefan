package org.example;

import org.example.Command;
import org.example.ImageRepository;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SaveCommand implements Command {
    public void execute(String[] args, ImageRepository repo) throws Exception {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("repo.bin"))){
            out.writeObject(repo.getImages());
            System.out.println("Repo salvat binar");
        }
    }
}