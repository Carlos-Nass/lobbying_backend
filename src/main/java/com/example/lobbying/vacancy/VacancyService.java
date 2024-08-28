package com.example.lobbying.vacancy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class VacancyService {

    @Autowired
    private VacancyRepository repository;

    public Vacancy createVacancy(VacancyRequestDTO data){

        Vacancy newVacancy = new Vacancy();

        newVacancy.setTitle(data.title());
        newVacancy.setDescription(data.description());

        repository.save(newVacancy);

        return newVacancy;
    }

    public List<VacancyResponseDTO> getVacancies(){

        return repository.findAll().stream().map(VacancyResponseDTO::new).toList();
    }

    public void deleteVacancy(Long id){

        this.repository.delete(this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Vacancy not found")));

    }

    public Vacancy updateVacancy(Long id, Vacancy vacancy){
        Vacancy savedVacancy = repository.findById(id).orElseThrow(() -> new RuntimeException("Timeout"));
        savedVacancy.setTitle(vacancy.getTitle());
        savedVacancy.setDescription(vacancy.getDescription());

        repository.save(savedVacancy);
        return savedVacancy;

    }

    public Optional<Vacancy> findById(Long id){
        return repository.findById(id);
    }

}
