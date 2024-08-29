package com.example.lobbying.vacancy;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    List<Vacancy> findAllByTagsIdIn(List<Long> tagIds);

}
