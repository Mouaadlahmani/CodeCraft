package com.mouad.Syntax.service.impl;

import com.mouad.Syntax.dto.ContenuDto;
import com.mouad.Syntax.mapper.ContenuMapper;
import com.mouad.Syntax.model.Contenu;
import com.mouad.Syntax.model.Lecon;
import com.mouad.Syntax.repository.ContenuRepository;
import com.mouad.Syntax.repository.LeconRepository;
import com.mouad.Syntax.service.ContenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContenuServiceImpl implements ContenuService {

    @Autowired
    ContenuRepository contenuRepository;
    @Autowired
    LeconRepository leconRepository;
    @Autowired
    ContenuMapper contenuMapper;

    @Override
    public ContenuDto addContenu(Long id, ContenuDto contenuDto) {
        Lecon lecon = leconRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Lecon not found")
        );
        Contenu contenu = contenuMapper.toEntity(contenuDto);
        contenu.setLecon(lecon);
        Contenu saved = contenuRepository.save(contenu);
        return contenuMapper.toDto(saved);
    }

    @Override
    public ContenuDto editContenu(Long id, ContenuDto contenuDto) {
        Optional<Contenu> contenu = contenuRepository.findById(id);
        if (contenu.isPresent()) {
            Contenu editedContenu = contenu.get();
            editedContenu.setId(id);
            editedContenu.setTitre(contenuDto.getTitre());
            editedContenu.setLecon(contenuDto.getLecon());
            editedContenu.setContenu(contenuDto.getContenu());
            Contenu saved = contenuRepository.save(editedContenu);
            return contenuMapper.toDto(saved);
        }

        return null;
    }

    @Override
    public List<ContenuDto> contenuList() {
        List<Contenu> contenus = contenuRepository.findAll();
        return contenus.stream()
                .map(contenuMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ContenuDto> contenuById(Long id) {
        Optional<Contenu> contenu = contenuRepository.findById(id);
        return contenu.map(contenuMapper::toDto);
    }

    @Override
    public void deleteContenu(Long id) {
            contenuRepository.deleteById(id);
    }
}
