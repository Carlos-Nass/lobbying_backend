package com.example.lobbying.vacancy.dto;

import com.example.lobbying.generic.GenericDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacancyDTO {

    private Long id;
    
    private String title;

    private String description;
    
    private String urlForm;
    
    private LocalDate createdAt;

    private List<GenericDTO> tags;

}
