package org.example;

import java.time.LocalDate;
import java.util.List;

public class UpdateCommand implements Command{
    public void execute(String[] args, ImageRepository repo) throws Exception{
        if(args.length < 5){
            throw new IllegalArgumentException("Nu e formatu bun boss: update index nume data taguri(separate prin ,) cale");
        }
        int index = Integer.parseInt(args[0]);
        String name = args[1];
        LocalDate date = LocalDate.parse(args[2]);
        List<String> tags = List.of(args[3].split(","));
        String path = args[4];

        if(repo.getImage(index) == null){
            throw new IllegalArgumentException("Nu este imagine la indexul: " + index);
        }

        Image newImage = new Image(name, date, tags, path);
        repo.updateImage(index, newImage);
        System.out.println("Image updated");
    }
}
