package com.problem1.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRelationship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_idx")
    private Category parent; // parent가 null이라면 최상단 카테고리

    @ManyToOne
    @JoinColumn(name = "child_id")
    private Category child; // child가 null이라면 최하단 카테고리

}
