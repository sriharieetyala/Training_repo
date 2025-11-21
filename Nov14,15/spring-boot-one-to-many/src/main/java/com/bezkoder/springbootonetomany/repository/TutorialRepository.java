package com.bezkoder.springbootonetomany.repository;



import com.bezkoder.springbootonetomany.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
}
