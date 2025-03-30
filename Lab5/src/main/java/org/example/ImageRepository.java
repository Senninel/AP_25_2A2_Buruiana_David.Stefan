package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class ImageRepository {
    private List<Image> images = new ArrayList<>();

    public void addImage(Image image) {
        images.add(image);
    }

    public void updateImage(int index, Image newImage){
        images.set(index, newImage);
    }

    public Image getImage(int index) {
        if (index >= 0 && index < images.size()) {
            return images.get(index);
        }
        return null;
    }

    public List<Image> getImages(){
        return images;
    }

    public void setImages(List<Image> newImages){
        this.images = newImages;
    }
}
