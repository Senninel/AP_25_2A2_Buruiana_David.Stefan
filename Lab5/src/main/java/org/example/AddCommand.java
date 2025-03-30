package org.example;

import java.time.LocalDate;
import java.util.List;

public class AddCommand implements Command{
    public void execute(String[] args, ImageRepository repo) throws Exception {
        if(args.length < 4){
            throw new IllegalArgumentException("Nu ai folosit formatul corect: add nume, data, tag-uri(separate prin ,), cale");
        }
        String name = args[0];
        LocalDate date = LocalDate.parse(args[1]);
        List<String> tags = List.of(args[2].split(","));
        String path = args[3];

        Image img = new Image(name, date, tags, path);
        repo.addImage(img);
        System.out.println("Imagine adaugata");
    }
}