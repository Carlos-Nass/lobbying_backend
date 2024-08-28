package com.example.lobbying.controller;

import com.example.lobbying.vacancy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("vacancy")
public class VacancyController {

    @Autowired
    private VacancyService vacancyService;

    @PostMapping
    public ResponseEntity<Vacancy> create(@RequestBody VacancyRequestDTO body){
        Vacancy newVacancy = this.vacancyService.createVacancy(body);
        return ResponseEntity.ok(newVacancy);
    }

    @GetMapping
    public List<VacancyResponseDTO> getAll(){

        return vacancyService.getVacancies();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVacancy(@PathVariable Long id){
        vacancyService.deleteVacancy(id);
        return ResponseEntity.noContent().build();
    }

}
