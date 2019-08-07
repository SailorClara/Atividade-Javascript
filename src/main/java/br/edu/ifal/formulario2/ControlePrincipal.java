package br.edu.ifal.formulario2;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class ControlePrincipal{
    @Autowired
    AlunoRepositorio alunoRepositorio;

    @RequestMapping("/")
    public ModelAndView novoAluno(){
        return new ModelAndView("index.html");
    }

    @RequestMapping("/cadastro")
    public ModelAndView form(Aluno a) {
        ModelAndView retorno = new ModelAndView("Formulario.html");
        retorno.addObject("aluno", a);
        
        return retorno;
    }
    
    @RequestMapping("/novo_aluno")
    public ModelAndView cadastroNovoAluno( Aluno aluno, RedirectAttributes redirect){
        ModelAndView reposta = new ModelAndView("redirect:/listar_alunos");
        alunoRepositorio.save(aluno);
        redirect.addFlashAttribute("mensagem", aluno.getNome() + " cadastrado com sucesso.");
        return reposta;
    }

    @RequestMapping("/listar_alunos")
    public ModelAndView listarAlunos() {
        ModelAndView retorno = new ModelAndView("lista_alunos.html");
        Iterable<Aluno> alunos = alunoRepositorio.findAll();
        retorno.addObject("alunos", alunos);
        return retorno;
    }

    @RequestMapping("/excluir_aluno/{idAluno}")
    public ModelAndView excluirAlunos(@PathVariable("idAluno") Long alunoID, RedirectAttributes redirect) {
        Optional<Aluno> opcao = alunoRepositorio.findById(alunoID);
        ModelAndView retorno = new ModelAndView("redirect:/listar_alunos");        
        if(opcao.isPresent()){
            Aluno a = opcao.get();
            alunoRepositorio.deleteById(a.getId());            
            redirect.addFlashAttribute("mensagem", a.getNome() + " excluído com sucesso.");
            return retorno;
        } else {
            redirect.addFlashAttribute("mensagem", "Aluno " + alunoID + " não existe no Banco de Dados.");
            return retorno;
        }
        
    }

    @RequestMapping("/atualizar_aluno/{idAluno}")
    public ModelAndView atualizar(@PathVariable("idAluno") Long alunoID) {
        Optional<Aluno> opcao = alunoRepositorio.findById(alunoID);
        ModelAndView retorno = new ModelAndView("Formulario.html");
        if(opcao.isPresent()){
            Aluno a = opcao.get();
            retorno.addObject("aluno", a);
            return retorno;
        }
        return retorno;
    }

}