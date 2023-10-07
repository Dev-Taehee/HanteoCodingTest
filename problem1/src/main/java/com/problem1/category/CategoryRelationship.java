package com.problem1.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CategoryRelationship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryRelationshipId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_idx")
    private Category parent; // parent가 null이라면 최상단 카테고리

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "child_id")
    private Category child; // child가 null이라면 최하단 카테고리

}
