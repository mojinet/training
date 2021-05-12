package fr.modji.papeterie.dal;

import fr.modji.papeterie.bo.Article;
import java.util.List;

public interface ArticleDAO {
    Article selectById(int id);

    List<Article> selectAll();

    int update(Article article);

    int insert(Article article);

    int delete(int id);
}
