package com.driver.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;
    @CreatedDate
    private Date pubDate;

    @ManyToOne
    @JoinColumn
    User user;

    @OneToMany(mappedBy = "blog",cascade = CascadeType.ALL)
    List<Image> imageList = new ArrayList<>();//create a empty list on blog creation




}