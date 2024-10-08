package com.mouad.syntax.service.impl;

import com.mouad.syntax.dto.CoursDto;
import com.mouad.syntax.mapper.CoursMapper;
import com.mouad.syntax.model.Cours;
import com.mouad.syntax.repository.CoursRepository;
import com.mouad.syntax.service.CoursService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CoursServiceImpl implements CoursService {

    private final CoursRepository coursRepository;

    private final CoursMapper coursMapper;

    @Override
    public CoursDto ajouterCours(CoursDto coursDto) {
        Cours cours = coursMapper.toEntity(coursDto);
        Cours saved = coursRepository.save(cours);
        return coursMapper.toDto(saved);
    }

    @Override
    public List<CoursDto> allCourses() {
        List<Cours> coursList = coursRepository.findAllSortedById();
        return coursList.stream()
                .map(coursMapper::toDto)
                .toList();
    }

    @Override
    public Optional<CoursDto> coursById(Long id) {
        Optional<Cours> optionalCours = coursRepository.findById(id);
        return optionalCours.map(coursMapper::toDto);
    }

    @Override
    public Optional<CoursDto> coursByQuestionId(Long id) {
        Optional<Cours> optionalCours = coursRepository.findByQuestionsId(id);
        return optionalCours.map(coursMapper::toDto);
    }

    @Override
    public CoursDto editCours(Long id, CoursDto coursDto) {
        Optional<Cours> cours = coursRepository.findById(id);
        if (cours.isPresent()){
            Cours editedCours = cours.get();
            editedCours.setId(id);
            editedCours.setTitre(coursDto.getTitre());
            editedCours.setDescription(coursDto.getDescription());
            editedCours.setLecons(coursDto.getLecons());
            editedCours.setCertificat(coursDto.getCertificat());
            editedCours.setQuestions(editedCours.getQuestions());
            Cours saved = coursRepository.save(editedCours);
            return coursMapper.toDto(saved);
        }
        return null;
    }

    @Override
    public void deleteCours(Long id) {
        coursRepository.deleteById(id);
    }

    @Override
    public long count() {
        return coursRepository.count();
    }
}
