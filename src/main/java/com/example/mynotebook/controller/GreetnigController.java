package com.example.mynotebook.controller;

import com.example.mynotebook.domain.Note;
import com.example.mynotebook.repos.NoteRepo;
import org.springframework.beans.Mergeable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetnigController {


    @Autowired
    private NoteRepo noteRepo;

    @GetMapping
    public String main(Map<String, Object> model) {
        Iterable<Note> notes = noteRepo.findAll();

        model.put("notes", notes);

        return "main";
    }

    @PostMapping
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        Note note = new Note(text, tag);

        noteRepo.save(note);

        Iterable<Note> notes = noteRepo.findAll();

        model.put("notes", notes);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Note> notes;

        if (filter != null && !filter.isEmpty()) {
            notes = noteRepo.findByTag(filter);
        } else {
            notes = noteRepo.findAll();
        }

        model.put("notes", notes);

        return "main";
    }
}
