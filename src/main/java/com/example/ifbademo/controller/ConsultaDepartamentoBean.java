package com.example.ifbademo.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.ifbademo.model.Departamento;
import com.example.ifbademo.service.DepartamentoService;

import lombok.Getter;
import lombok.Setter;

@Component
@Scope("view")
public class ConsultaDepartamentoBean {
    @Getter
    private List<Departamento> departamentos;
    @Getter @Setter
    private Departamento departamentoSelecionado;

    @Autowired
    private DepartamentoService departamentoService;
    
    @PostConstruct
    public void init(){
        departamentos = departamentoService.buscarTodos();
    }

    public void excluir(){
        FacesContext context = FacesContext.getCurrentInstance();
        if (departamentoService.excluir(departamentoSelecionado.getId())){
            context.addMessage(null, new FacesMessage("Exclusão",
            "Cargo excluído com sucesso!"));

            departamentos = departamentoService.buscarTodos();
        }else{
            context.addMessage(null, new FacesMessage(
               FacesMessage.SEVERITY_ERROR, "Erro na exclusão",
            "Existe Cargo vinculado a este departamento."));
        }
    }

}
