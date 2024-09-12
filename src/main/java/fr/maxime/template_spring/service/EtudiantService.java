package fr.maxime.template_spring.service;

import fr.maxime.template_spring.dao.EtudiantRepository;
import fr.maxime.template_spring.entity.Etudiant;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class EtudiantService {
    private EtudiantRepository etudiantRepository;

    public EtudiantService(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    public List<Etudiant> findAll() {
        return etudiantRepository.findAll();
    }

    public Etudiant findById(UUID id) {
        return etudiantRepository.findById(id).orElse(null);
    }

    public Etudiant findByName(String name) {
        return etudiantRepository.findByNom(name);
    }

    public boolean findByLoginAndPassword(String login, String password) {
        return etudiantRepository.findByLoginAndPassword(login, password) != null;
    }

    public Etudiant save(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }


    public void delete(Etudiant etudiant) {
        etudiantRepository.delete(etudiant);
    }

//    private final Map<UUID, Etudiant> listEtudiants;
//
//    public EtudiantService() {
//        this.listEtudiants = new HashMap<UUID, Etudiant>();
//    }
//
//    public List<Etudiant> getAllEtudiants() {
//        return listEtudiants.values().stream().toList();
//    }
//
//    public Etudiant getEtudiantById(UUID id) {
//        return listEtudiants.get(id);
//    }
//    public Etudiant getEtudiantByName(String name) {
//        return listEtudiants.values().stream().filter(e -> e.getNom().equals(name)).findFirst().orElse(null);
//    }
//
//    public Etudiant addEtudiant(Etudiant etudiant) {
//        Etudiant newEtudiant = Etudiant.builder()
//                .id(UUID.randomUUID())
//                .nom(etudiant.getNom())
//                .prenom(etudiant.getPrenom())
//                .age(etudiant.getAge())
//                .email(etudiant.getEmail())
//                .build();
//        listEtudiants.put(newEtudiant.getId(), newEtudiant);
//        return newEtudiant;
//    }
//
//    public Etudiant updateEtudiant(UUID id, Etudiant etudiant) {
//        Etudiant existingEtudiant = listEtudiants.get(id);
//        if (existingEtudiant!=null) {
//            existingEtudiant.setNom(etudiant.getNom());
//            existingEtudiant.setPrenom(etudiant.getPrenom());
//            existingEtudiant.setAge(etudiant.getAge());
//            existingEtudiant.setEmail(etudiant.getEmail());
//            return existingEtudiant;
//        }
//        return null;
//
//    }
//
//    public boolean deleteEtudiant(Etudiant etudiant) {
//        return listEtudiants.remove(etudiant.getId())!=null;
//    }
}
