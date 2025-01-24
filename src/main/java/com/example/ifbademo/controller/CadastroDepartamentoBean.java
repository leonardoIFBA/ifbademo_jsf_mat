package com.example.ifbademo.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.ifbademo.model.Departamento;
import com.example.ifbademo.service.DepartamentoService;

import lombok.*;

@Component
@Scope("view")
public class CadastroDepartamentoBean {

    @Getter @Setter
    private Departamento departamento = new Departamento();
    @Autowired
    private DepartamentoService departamentoService;

    public void salvar(){
        FacesContext context = FacesContext.getCurrentInstance();
        departamentoService.salvar(departamento);
        departamento = new Departamento();


        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO,
                      "Cadastro efetuado.", 
                      "Departamento cadastro com sucesso.");
        context.addMessage(null, mensagem);
        
    }

    public void prepararCadastro(){
        departamento = departamentoService.buscarPorId(departamento.getId());
    }
    
}
