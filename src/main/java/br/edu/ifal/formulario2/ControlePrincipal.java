package br.edu.ifal.formulario2;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ControlePrincipal{


    @RequestMapping("/")
    public ModelAndView novoAluno(){
        return new ModelAndView("Formulario.html");
    }
    
    @RequestMapping("/novo_aluno")
    public ModelAndView cadastroNovoAluno(String nome, String email, String cpf,
        String sexo, String modulo, List<String> areas, String senha){
        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setEmail(email);
        aluno.setCpf(cpf);
        aluno.setSexo(sexo);
        aluno.setModulo(modulo);
        aluno.setAreas(areas);
        aluno.setSenha(senha);
        ModelAndView reposta = new ModelAndView();
        reposta.addObject("mansage", aluno.getNome() + "Foi cadastrado com sucesso");
        return reposta;
    }
}