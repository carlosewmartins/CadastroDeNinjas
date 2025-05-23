package dev.tekaxis.CadastroDeNinjas.Ninjas;

import dev.tekaxis.CadastroDeNinjas.Missoes.MissoesModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// @Entity transforma uma classe em uma entidade do DB

@Entity
@Table(name = "tb_cadastro")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    private int idade;

    // @ManyToOne - Uma missão por ninja (pode ter mais de um ninja na missão)
    @ManyToOne
    private MissoesModel missoes;

}
