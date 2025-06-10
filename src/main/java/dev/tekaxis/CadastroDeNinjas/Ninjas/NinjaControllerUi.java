package dev.tekaxis.CadastroDeNinjas.Ninjas;

import dev.tekaxis.CadastroDeNinjas.Missoes.MissoesModel;
import dev.tekaxis.CadastroDeNinjas.Missoes.MissoesRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ninjas/ui")
public class NinjaControllerUi {

    private final NinjaService ninjaService;
    private final MissoesRepository missoesRepository;

    // Injetando MissoesRepository para buscar as missões disponíveis
    public NinjaControllerUi(NinjaService ninjaService, MissoesRepository missoesRepository) {
        this.ninjaService = ninjaService;
        this.missoesRepository = missoesRepository;
    }

    // LISTAR: Exibe a lista de todos os ninjas
    @GetMapping("/listar")
    public String listarNinjas(Model model) {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        model.addAttribute("ninjas", ninjas);
        return "listarNinjas"; // Corresponde a listarNinjas.html
    }

    // DETALHES: Exibe os detalhes de um ninja específico
    @GetMapping("/detalhes/{id}")
    public String detalhesNinja(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);
        if (ninja != null) {
            model.addAttribute("ninja", ninja); // Corrigido para "ninja" para corresponder ao template
            return "detalhesNinja"; // Corresponde a detalhesNinja.html
        }
        // Adiciona mensagem de erro e redireciona se o ninja não for encontrado
        model.addAttribute("mensagemErro", "Ninja com ID " + id + " não encontrado.");
        return "redirect:/ninjas/ui/listar";
    }

    // CRIAR (GET): Exibe o formulário para criar um novo ninja
    @GetMapping("/novo")
    public String mostrarFormularioDeCriacao(Model model) {
        NinjaDTO ninjaDTO = new NinjaDTO();
        ninjaDTO.setMissoes(new MissoesModel()); // Inicializa missoes para evitar NullPointerException no data binding
        model.addAttribute("ninja", ninjaDTO);

        List<MissoesModel> missoes = missoesRepository.findAll();
        model.addAttribute("missoesDisponiveis", missoes); // Adiciona missões ao modelo

        return "criarNinja"; // Corresponde a criarNinja.html
    }

    // CRIAR (POST): Processa o envio do formulário de criação
    @PostMapping("/criar")
    public String criarNinja(@ModelAttribute("ninja") NinjaDTO ninjaDTO, RedirectAttributes redirectAttributes) {
        // Se nenhuma missão foi selecionada, o ID será null. Ajustamos para o objeto ser nulo.
        if (ninjaDTO.getMissoes() != null && ninjaDTO.getMissoes().getId() == null) {
            ninjaDTO.setMissoes(null);
        }
        ninjaService.criarNinja(ninjaDTO);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Ninja cadastrado com sucesso!");
        return "redirect:/ninjas/ui/listar";
    }

    // EDITAR (GET): Exibe o formulário para editar um ninja existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEdicao(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);
        if (ninja != null) {
            model.addAttribute("ninja", ninja);

            List<MissoesModel> missoes = missoesRepository.findAll();
            model.addAttribute("missoesDisponiveis", missoes); // Adiciona missões ao modelo

            return "editarNinja"; // Corresponde a editarNinja.html
        }
        return "redirect:/ninjas/ui/listar";
    }

    // EDITAR (POST): Processa o envio do formulário de edição
    @PostMapping("/atualizar/{id}")
    public String atualizarNinja(@PathVariable Long id, @ModelAttribute("ninja") NinjaDTO ninjaDTO, RedirectAttributes redirectAttributes) {
        if (ninjaDTO.getMissoes() != null && ninjaDTO.getMissoes().getId() == null) {
            ninjaDTO.setMissoes(null);
        }
        ninjaService.atualizarNinja(id, ninjaDTO);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Ninja atualizado com sucesso!");
        return "redirect:/ninjas/ui/listar";
    }

    // EXCLUIR: Deleta um ninja
    @PostMapping("/excluir/{id}") // Alterado para @PostMapping para seguir as boas práticas de requisições que alteram estado
    public String deletarNinja(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (ninjaService.listarNinjasPorId(id) != null) {
            ninjaService.deletarNinja(id);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Ninja com ID " + id + " foi excluído com sucesso.");
        } else {
            redirectAttributes.addFlashAttribute("mensagemErro", "Falha ao excluir: Ninja com ID " + id + " não encontrado.");
        }
        return "redirect:/ninjas/ui/listar";
    }
}