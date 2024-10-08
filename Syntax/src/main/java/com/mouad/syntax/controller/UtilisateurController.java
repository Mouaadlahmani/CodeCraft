package com.mouad.syntax.controller;

import com.mouad.syntax.dto.UtilisateurDto;
import com.mouad.syntax.service.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/utilisateur/")
@RequiredArgsConstructor
public class UtilisateurController {


    private final UtilisateurService utilisateurService;

    @GetMapping("all")
    public List<UtilisateurDto> getAll() {
        return utilisateurService.findAll();
    }

    @GetMapping("count")
    public long count() {
        return utilisateurService.count();
    }


    @GetMapping("{id}")
    public Optional<UtilisateurDto> getById(@PathVariable("id") Long id) {
        return utilisateurService.findById(id);
    }

    @PutMapping("edit/{id}")
    public UtilisateurDto update(@PathVariable("id") Long id, @RequestBody UtilisateurDto utilisateur) {
        return utilisateurService.editInfos(id, utilisateur);
    }
}
