package com.mouad.syntax.repository;

import com.mouad.syntax.model.Cours;
import com.mouad.syntax.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByCours(Cours cours);

    @Query(value = """
            SELECT q.* FROM question q
            INNER JOIN cours c ON q.cours = c.id
            WHERE c.titre = :category
            ORDER BY RANDOM()
            LIMIT :numQ
            """, nativeQuery = true)
    List<Question> findRandomQuestionByCours(@Param("category") String category, @Param("numQ") int numQ);

}
