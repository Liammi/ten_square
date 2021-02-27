package com.tensquare.article.repository;

import com.tensquare.article.pojo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    /**
     * 根据文章ID查询评论集合
     * @param articleId 文章ID
     * @return 评论集合
     */
    List<Comment> findByArticleid(String articleId);

    /**
     * 根据发布时间和点赞数查询
     * @param date
     * @param thumbup
     * @return
     */
    List<Comment> findByPublishdateAndThumbup(Date date,Integer thumbup);

    List<Comment> findByUseridOrderByPublishdateDesc(String userid);
}
