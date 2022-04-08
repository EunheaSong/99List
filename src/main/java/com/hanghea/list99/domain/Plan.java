package com.hanghea.list99.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Plan extends Timestamped{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long planId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int stars;

    @Column(nullable = false)
    private Boolean status;

    @ManyToOne()
    @JoinColumn(name = "USER_ID")
    private User user;


}
