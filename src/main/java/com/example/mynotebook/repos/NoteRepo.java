package com.example.mynotebook.repos;

import com.example.mynotebook.domain.Note;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoteRepo extends CrudRepository<Note, Long> {
    List<Note> findByTag(String tag);
}
