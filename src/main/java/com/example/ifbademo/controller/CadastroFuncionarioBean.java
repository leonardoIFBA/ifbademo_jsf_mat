package com.example.ifbademo.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.ifbademo.model.Cargo;
import com.example.ifbademo.model.Endereco;
import com.example.ifbademo.model.Funcionario;
import com.example.ifbademo.model.UF;
import com.example.ifbademo.service.CargoService;
import com.example.ifbademo.service.FuncionarioService;

import lombok.Getter;
import lombok.Setter;

@Component
@Scope("view")
public class CadastroFuncionarioBean {
    @Getter@Setter
    private Funcionario funcionario = new Funcionario();
    @Autowired
    private FuncionarioService funcionarioService;
    @Autowired
    private CargoService cargoService;
    @Getter
    private List<Cargo> todosCargos;
    @Getter
    private UF[] ufs;

    @PostConstruct
    public void init() {
        funcionario = new Funcionario();
        funcionario.setEndereco(new Endereco());
        todosCargos = cargoService.buscarTodos();
    }

    /*public void consultar() {
        todosCargos = cargoService.buscarTodos();
        //System.out.println("entrou no post cronstructor");
    }*/

    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        funcionarioService.salvar(funcionario);
        //Prepara para cadastrar um novo lancamento
        funcionario = new Funcionario();
        funcionario.setEndereco(new Endereco());

        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro efetuado.",
                "Funcionario cadastrado com sucesso.");
        context.addMessage(null, mensagem);

    }

    public void prepararCadastro() {
        funcionario = funcionarioService.buscarPorId(funcionario.getId());
        
    }

    public UF[] getUfs(){
        return UF.values();
      }

}
