package dev.tekaxis.CadastroDeNinjas.Ninjas;

import dev.tekaxis.CadastroDeNinjas.Missoes.MissoesModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


// @Entity transforma uma classe em uma entidade do DB

@Entity
@Table(name = "tb_cadastro")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString (exclude = "missoes")
public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(unique = true)
    private String email;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "idade")
    private int idade;

    @Column(name = "rank")
    private String rank;

    // @ManyToOne - Uma missão por ninja (pode ter mais de um ninja na missão)
    @ManyToOne
    @JoinColumn(name = "missoes_id") // Foreign Key - Chave estrangeira
    private MissoesModel missoes;

}
