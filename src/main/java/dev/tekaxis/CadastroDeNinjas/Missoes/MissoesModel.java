package dev.tekaxis.CadastroDeNinjas.Missoes;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.tekaxis.CadastroDeNinjas.Ninjas.NinjaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tb_missoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MissoesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "dificuldade")
    private char dificuldade;

    //Varios ninjas para uma unica missão

    @OneToMany(mappedBy = "missoes")
    @JsonIgnore
    private List<NinjaModel> ninja;
}