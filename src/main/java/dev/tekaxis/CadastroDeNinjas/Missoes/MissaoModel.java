package dev.tekaxis.CadastroDeNinjas.Missoes;

import java.util.List;
import dev.tekaxis.CadastroDeNinjas.Ninjas.NinjaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_missoes")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class MissaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private char dificuldade;
    //Varios ninjas para uma unica missão
    @OneToMany(mappedBy = "missoes")
    @JoinColumn(name = "missoes_id") // Foreign Key - Chave estrangeira
    private List<NinjaModel> ninja;
}