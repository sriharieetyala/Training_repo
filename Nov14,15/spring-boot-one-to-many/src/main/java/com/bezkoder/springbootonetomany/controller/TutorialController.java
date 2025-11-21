package com.bezkoder.springbootonetomany.controller;

import com.bezkoder.springbootonetomany.model.Comment;
import com.bezkoder.springbootonetomany.model.Tutorial;
import com.bezkoder.springbootonetomany.repository.CommentRepository;
import com.bezkoder.springbootonetomany.repository.TutorialRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TutorialController {

    private final TutorialRepository tutorialRepository;
    private final CommentRepository commentRepository;

    public TutorialController(TutorialRepository tutorialRepository,
                              CommentRepository commentRepository) {
        this.tutorialRepository = tutorialRepository;
        this.commentRepository = commentRepository;
    }

    // ---------- Tutorials ----------

    @GetMapping("/tutorials")
    public List<Tutorial> getAllTutorials() {
        return tutorialRepository.findAll();
    }

    @PostMapping("/tutorials")
    public Tutorial createTutorial(@RequestBody Tutorial tutorial) {
        return tutorialRepository.save(tutorial);
    }

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable Long id) {
        Optional<Tutorial> optional = tutorialRepository.findById(id);
        return optional.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<Void> deleteTutorial(@PathVariable Long id) {
        if (!tutorialRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        tutorialRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ---------- Comments for a Tutorial ----------

    @PostMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable Long tutorialId,
                                                 @RequestBody Comment commentRequest) {

        Optional<Tutorial> tutorialOpt = tutorialRepository.findById(tutorialId);
        if (tutorialOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Tutorial tutorial = tutorialOpt.get();

        Comment comment = new Comment();
        comment.setContent(commentRequest.getContent());
        comment.setTutorial(tutorial);

        Comment saved = commentRepository.save(comment);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<List<Comment>> getCommentsByTutorial(@PathVariable Long tutorialId) {
        if (!tutorialRepository.existsById(tutorialId)) {
            return ResponseEntity.notFound().build();
        }
        List<Comment> comments = commentRepository.findByTutorialId(tutorialId);
        return ResponseEntity.ok(comments);
    }

    @DeleteMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<Void> deleteCommentsByTutorial(@PathVariable Long tutorialId) {
        if (!tutorialRepository.existsById(tutorialId)) {
            return ResponseEntity.notFound().build();
        }
        commentRepository.deleteByTutorialId(tutorialId);
        return ResponseEntity.noContent().build();
    }
}
