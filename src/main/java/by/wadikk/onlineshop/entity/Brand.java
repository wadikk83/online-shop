package by.wadikk.onlineshop.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    private String name;

}
