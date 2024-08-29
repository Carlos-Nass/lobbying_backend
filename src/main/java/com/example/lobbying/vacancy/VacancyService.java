package com.example.lobbying.vacancy;

import com.example.lobbying.generic.GenericDTO;
import com.example.lobbying.tag.Tag;
import com.example.lobbying.tag.TagRepository;
import com.example.lobbying.user.User;
import com.example.lobbying.user.UserRepository;
import com.example.lobbying.vacancy.dto.VacancyDTO;
import com.example.lobbying.vacancy.dto.VacancyRequestDTO;
import com.example.lobbying.vacancy.dto.VacancyResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
public class VacancyService {

    @Autowired
    private VacancyRepository repository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UserRepository userRepository;

    public VacancyResponseDTO createVacancy(VacancyRequestDTO data) {
        Vacancy newVacancy = new Vacancy();
        newVacancy.setTitle(data.title());
        newVacancy.setDescription(data.description());

        if (!CollectionUtils.isEmpty(data.tagIds())) {
            List<Tag> tags = this.tagRepository.findAllByIdIn(data.tagIds());
            newVacancy.setTags(tags);
        }

        this.repository.save(newVacancy);

        return new VacancyResponseDTO(newVacancy);
    }

    public List<VacancyResponseDTO> getVacancies() {
        return repository.findAll().stream().map(VacancyResponseDTO::new).toList();
    }

    public void deleteVacancy(Long id) {
        this.repository.delete(this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Vacancy not found")));
    }

    public Vacancy updateVacancy(Long id, Vacancy vacancy) {
        Vacancy savedVacancy = repository.findById(id).orElseThrow(() -> new RuntimeException("Timeout"));
        savedVacancy.setTitle(vacancy.getTitle());
        savedVacancy.setDescription(vacancy.getDescription());

        repository.save(savedVacancy);
        return savedVacancy;

    }

    public Optional<Vacancy> findById(Long id) {
        return repository.findById(id);
    }

    public List<VacancyDTO> loadVacanciesByTags(Long userId) {
        Optional<User> optUser = this.userRepository.findById(userId);
        if(optUser.isEmpty()) {
            throw new EntityNotFoundException("Usuário com o id:" + userId + ". Não encontrado.");
        }

        List<Long> tagIds = optUser.get().getTags().stream().map(Tag::getId).toList();
        List<Vacancy> vacancies = this.repository.findAllByTagsIdIn(tagIds);

        return vacancies.stream().map(vacancy -> {
            VacancyDTO vac = new VacancyDTO();
            vac.setId(vacancy.getId());
            vac.setName(vacancy.getTitle());
            vac.setTags(vacancy.getTags().stream().map(tag -> new GenericDTO(tag.getId(), tag.getName())).toList());

            return vac;
        }).toList();
    }

}
