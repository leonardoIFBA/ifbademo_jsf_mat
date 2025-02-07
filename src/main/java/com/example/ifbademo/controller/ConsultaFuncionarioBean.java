package com.example.ifbademo.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.ifbademo.model.Funcionario;
import com.example.ifbademo.service.FuncionarioService;

import lombok.*;

@Component
@Scope("view")
public class ConsultaFuncionarioBean {
    @Getter
    private List<Funcionario> funcionarios; 
    @Getter @Setter
    private Funcionario funcionarioSelecionado;
    @Autowired
    private FuncionarioService funcionarioService;

    @PostConstruct
    public void init(){
        funcionarios = funcionarioService.buscarTodos();
    }
    
}
