package com.example.mynotebook.controller;

import com.example.mynotebook.domain.Note;
import com.example.mynotebook.domain.User;
import com.example.mynotebook.repos.NoteRepo;
import org.springframework.beans.Mergeable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {


    @Autowired
    private NoteRepo noteRepo;

    @GetMapping("/")
    public String greeting(Map<String,Object> model){
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Note> notes = noteRepo.findAll();

        model.put("notes", notes);

        return "main";
    }


    @PostMapping("/main")
    public String add(@RequestParam String text,
                      @AuthenticationPrincipal User user,
                      @RequestParam String tag, Map<String, Object> model) {
        Note note = new Note(text, tag, user);

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
    @PostMapping("removeNote")
    public String deleteNote(@RequestParam Integer removeNote, Map<String, Object> model) {
        Note note;
        Iterable<Note> notes;
        if(noteRepo.findById(removeNote)!=null && removeNote != null && removeNote!=0 ){
            note = noteRepo.findById(removeNote);
            noteRepo.delete(note);
            notes = noteRepo.findAll();
        } else {
            notes = noteRepo.findAll();
        }



        model.put("notes", notes);

        return "main";
    }
}
