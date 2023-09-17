package com.example.board.entity;

import lombok.*;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column
    private Long id;

    @Column
    private String title;


    @Column
    private String content;

}
