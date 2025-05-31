package dev.tekaxis.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")

public class NinjaController {

    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }


    // ------- CRUD -------
    // Adicionar Ninja (CREATE)
    @PostMapping("/criar")
        public NinjaModel criarNinja(@RequestBody NinjaModel ninja){
            return ninjaService.criarNinja(ninja);
    }

    // Exibir todos os Ninjas (READ)
    @GetMapping("/listar")
    public List<NinjaModel> listarNinjas(){
        return ninjaService.listarNinjas();
    }

    // Exibir Ninja por ID (READ)
    @GetMapping("/listar/{id}")
    public NinjaModel listarNinjasPorId(@PathVariable Long id){
        return ninjaService.listarNinjasPorId(id);
    }
    // Alterar dados do Ninja (UPDATE)
    @PutMapping("/alterar")
    public String alterarNinjaPorId(){
        return "Alterar Ninja por ID";
    }

    // Deletar Ninja (DELETE)
    @DeleteMapping("/deletar")
    public String deletarNinjaPorId(){
        return "Ninja deletado por ID";
    }
}
