package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        //get the blog object
        Blog blog = blogRepository2.findById(blogId).get();
        //create a image object
        Image newImage = new Image();
        newImage.setDescription(description);
        newImage.setDimensions(dimensions);

        //add the image to imageList of blog
        blog.getImageList().add(newImage);

        newImage.setBlog(blog);

        blogRepository2.save(blog);//save the blog which automatically save image

        return newImage;

    }

    public void deleteImage(Integer id){
        //get the image object
//        Image image = imageRepository2.findById(id).get();
//        //get the blog it belongs to
//        Blog blog = blogRepository2.findById(image.getBlog().getId()).get();
//
//        blog.getImageList().remove(image);//remove image from given list

        imageRepository2.deleteById(id);

    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`

        int indOfX=screenDimensions.indexOf('X');
        // find the left(length & right(breadth) character around 'X' and convert this into integer
        int screenH=Integer.parseInt(screenDimensions.substring(0,indOfX));
        int screenW=Integer.parseInt(screenDimensions.substring(indOfX+1));

        //find the image that is to be set in the given dimension(screenDimensions)
        Image image=imageRepository2.findById(id).get();

        //image is found , extract its wdith and height
        int indxOfXinImage=image.getDimensions().indexOf('X');

        int imageH=Integer.parseInt(image.getDimensions().substring(0,indxOfXinImage));
        int imageW=Integer.parseInt(image.getDimensions().substring(indxOfXinImage+1));

        return (screenH/imageH) * (screenW/imageW); //number of complete images that fit in the screen of given dimensions
    }
}
