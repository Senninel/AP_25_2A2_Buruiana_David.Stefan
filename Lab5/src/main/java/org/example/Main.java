package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.time.LocalDate;
import java.util.List;
import java.time.LocalDate;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;



public class Main {
    public static void main(String[] args) {
            String path = "C:\\Users\\Sennin\\Downloads\\image.png";
            ImageRepository imageRepository = new ImageRepository();

            File imageFile = new File(path);
            if (imageFile.exists()) {
                imageRepository.addImage(imageFile);

                File toOpen = imageRepository.getImage(0);
                if (Desktop.isDesktopSupported() && toOpen != null) {
                    try {
                        Desktop.getDesktop().open(toOpen);
                    } catch (IOException e) {
                        System.out.println("Eroare deschidere imagine: " + e.getMessage());
                    }
                }
            } else {
                System.out.println("Image file nu exista.");
            }
        }
}