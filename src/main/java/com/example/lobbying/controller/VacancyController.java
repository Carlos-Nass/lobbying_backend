package com.example.lobbying.controller;

import com.example.lobbying.vacancy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vacancy")
public class VacancyController {

    @Autowired
    private VacancyService vacancyService;

    @PostMapping
    public ResponseEntity<Vacancy> createVacancy(@RequestBody VacancyRequestDTO body){
        Vacancy newVacancy = this.vacancyService.createVacancy(body);
        return ResponseEntity.ok(newVacancy);
    }

    @GetMapping
    public List<VacancyResponseDTO> getAll(){

        return vacancyService.getVacancies();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vacancy> updateVacancy(@PathVariable Long id, @RequestBody Vacancy vacancy){
        Vacancy updatedVacancy = vacancyService.updateVacancy(id, vacancy);
        return new ResponseEntity<>(updatedVacancy, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVacancy(@PathVariable Long id){
        vacancyService.deleteVacancy(id);
        return ResponseEntity.noContent().build();
    }

}
