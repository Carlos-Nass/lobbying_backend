package com.example.lobbying.controller;

import com.example.lobbying.tag.TagRepository;
import com.example.lobbying.tag.TagResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("tag")
public class TagController {

    @Autowired
    private TagRepository repository;

    @GetMapping
    public List<TagResponseDTO> getAll(){

        List<TagResponseDTO> tagList = repository.findAll().stream().map(TagResponseDTO::new).toList();
        return tagList;
    }
}
