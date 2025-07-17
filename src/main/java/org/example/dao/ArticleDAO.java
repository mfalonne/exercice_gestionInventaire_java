package org.example.dao;

import org.example.entity.Article;

public class ArticleDAO extends GenericDAO<Article>{
    public ArticleDAO(Class<Article> type) {
        super(type);
    }
}
