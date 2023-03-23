package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    UserRepository userRepository1;

    public Blog createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time
        User user = userRepository1.findById(userId).get();
        //set the blog attribute
        Blog newBlog = new Blog();
        newBlog.setTitle(title);
        newBlog.setContent(content);
        newBlog.setPubDate(new Date());
        newBlog.setUser(user);

        user.getBlogList().add(newBlog);//add the new blog in the list

        userRepository1.save(user);

        return newBlog;
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
//        Blog blog = blogRepository1.findById(blogId).get();//get blog object
//        User user  = userRepository1.findById(blog.getUser().getId()).get();//get user object
//
//        user.getBlogList().remove(blog);//remove the blog from user

        blogRepository1.deleteById(blogId);//delete the blog ,also image will also be deleted for given block

    }
}
