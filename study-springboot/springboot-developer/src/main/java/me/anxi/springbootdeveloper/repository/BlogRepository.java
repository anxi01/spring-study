package me.anxi.springbootdeveloper.repository;

import me.anxi.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Article, Long> {
}
