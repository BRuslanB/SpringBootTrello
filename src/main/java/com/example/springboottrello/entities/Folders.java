package com.example.springboottrello.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_folders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Folders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private List<TaskCategories> taskCategories; // Many To Many
}