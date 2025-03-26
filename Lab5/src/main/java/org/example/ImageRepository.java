package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class ImageRepository {
    private List<File> images = new ArrayList<>();

    public void addImage(File image) {
        images.add(image);
    }

    public File getImage(int index) {
        if (index >= 0 && index < images.size()) {
            return images.get(index);
        }
        return null;
    }
}
