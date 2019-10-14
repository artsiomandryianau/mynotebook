package com.example.mynotebook.controller;

import com.example.mynotebook.domain.Note;
import com.example.mynotebook.domain.User;
import com.example.mynotebook.repos.NoteRepo;
import org.springframework.beans.Mergeable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {


    @Autowired
    private NoteRepo noteRepo;

    @Value("${upload.path}")
    private String uploadpath;

    @GetMapping("/")
    public String greeting(Map<String,Object> model){
        return "greeting";
    }

    @GetMapping("/main")
    public String main(
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model) {
        Iterable<Note> notes = noteRepo.findAll();
        if (filter != null && !filter.isEmpty()) {
            notes = noteRepo.findByTag(filter);
        } else {
            notes = noteRepo.findAll();
        }
        model.addAttribute("notes", notes);
        model.addAttribute("filter", filter);

        return "main";
    }


    @PostMapping("/main")
    public String add(@RequestParam String text,
                      @RequestParam(required = false, defaultValue = "") String filter,
                      @AuthenticationPrincipal User user,
                      @RequestParam String tag, Map<String, Object> model,
                      @RequestParam("file")MultipartFile file) throws IOException {
        Note note = new Note(text, tag, user);
        if(file!=null && !file.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadpath);

            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();


            file.transferTo(new File(uploadpath + "/" + resultFileName));
            note.setFilename(resultFileName);
        }
        noteRepo.save(note);

        Iterable<Note> notes = noteRepo.findAll();

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
