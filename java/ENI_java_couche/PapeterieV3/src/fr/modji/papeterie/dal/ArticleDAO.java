package fr.modji.papeterie.dal;

import fr.modji.papeterie.bo.Article;
import java.util.List;

public interface ArticleDAO {
    public Article selectById(int id);
    public List<Article> selectAll();
    public int update(Article article);

    public int insert(Article article);

    public int delete(int id);
}
