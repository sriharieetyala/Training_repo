package com.bezkoder.springbootonetomany.repository;

import com.bezkoder.springbootonetomany.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByTutorialId(Long tutorialId);

    void deleteByTutorialId(Long tutorialId);
}
