package fr.maxime.template_spring.dao;

import fr.maxime.template_spring.entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EtudiantRepository extends JpaRepository<Etudiant, UUID> {
    Etudiant findByNom(String nom);
}
