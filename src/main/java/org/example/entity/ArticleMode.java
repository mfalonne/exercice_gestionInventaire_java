package org.example.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.entity.enums.CategorieMode;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ArticleMode extends Article {

    @Enumerated(EnumType.STRING)
    private CategorieMode categorie;

    private String taille;
}
