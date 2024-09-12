package fr.maxime.template_spring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank
    @NotNull(message = "Ce champ doit être rempli!")
    @Size(min = 2, message = "Minimum de 2 caractere !")
    private String nom;
    @NotBlank
    @NotNull(message = "Ce champ doit être rempli!")
    @Size(min = 3, message = "Minimum de 3 caractere !")
    private String prenom;
    @Min(18)
    @Max(100)
    private int age;
    private String email;
}
