package com.example.lobbying.controller;

import com.example.lobbying.vacancy.Vacancy;
import com.example.lobbying.vacancy.VacancyRepository;
import com.example.lobbying.vacancy.VacancyRequestDTO;
import com.example.lobbying.vacancy.VacancyResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vacancy")
public class VacancyController {

    @Autowired
    private VacancyRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveFood(@RequestBody VacancyRequestDTO data){
        Vacancy vacancyData = new Vacancy(data);
        repository.save(vacancyData);
        return;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<VacancyResponseDTO> getAll(){

        List<VacancyResponseDTO> vacancyList = repository.findAll().stream().map(VacancyResponseDTO::new).toList();
        return vacancyList;
    }
}
