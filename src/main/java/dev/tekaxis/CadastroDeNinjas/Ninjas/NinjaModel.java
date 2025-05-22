package dev.tekaxis.CadastroDeNinjas.Ninjas;

import dev.tekaxis.CadastroDeNinjas.Missoes.MissaoModel;
import jakarta.persistence.*;


// @Entity transforma uma classe em uma entidade do DB

@Entity
@Table(name = "tb_cadastro")

public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private int idade;

    // Uma missão por ninja (pode ter mais de um ninja na missão)
    @ManyToOne
    private MissaoModel missoes;

    public NinjaModel(){
    }

    public NinjaModel(String nome, String email, int idade){
        this.nome = nome;
        this.email = email;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

}
