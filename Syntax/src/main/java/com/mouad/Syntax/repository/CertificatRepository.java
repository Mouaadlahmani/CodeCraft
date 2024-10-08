package com.mouad.Syntax.repository;

import com.mouad.Syntax.model.Certificat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificatRepository extends JpaRepository<Certificat, Long> {
    boolean existsByUtilisateurIdAndCoursesId(Long userId, Long courseId);
    List<Certificat> findAllByUtilisateurId(Long id);
    List<Certificat> findAllByCoursesId(Long id);

    @Override
    long count();
}
