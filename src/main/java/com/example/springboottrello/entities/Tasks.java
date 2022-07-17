package com.example.springboottrello.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "t_tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description; // TEXT

    @ManyToOne(fetch = FetchType.EAGER)
    private TaskStatuses taskStatus; // Many To One

    @ManyToOne(fetch = FetchType.EAGER)
    private Folders folder; // Many To One
}