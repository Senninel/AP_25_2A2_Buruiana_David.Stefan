package org.example;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class LoadCommand implements Command{
    public void execute(String[] args, ImageRepository repo) throws Exception{
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("repo.bin"))){
            List<Image> images = (List<Image>) in.readObject();
            repo.setImages(images);
            System.out.println("Repo loadat din fisier binar");
        }
    }
}