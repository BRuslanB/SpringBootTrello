package com.example.springboottrello.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "t_task_statuses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TaskStatuses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name; // 0_todo, 1-intest, 2-done, 3-failed
}