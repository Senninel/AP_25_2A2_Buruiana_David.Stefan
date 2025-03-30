package org.example;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ViewCommand implements Command{
    public void execute(String[] args, ImageRepository repo) throws Exception {
        if(args.length < 1){
            throw new IllegalArgumentException("Nu ai folosit formatul corect: view index");
        }
        int index = Integer.parseInt(args[0]);
        Image img = repo.getImage(index);
        if(img == null){
            throw new IllegalArgumentException("Imaginea nu a fost gasita");
        }
        Desktop.getDesktop().open(new File(img.path()));
    }
}
