package com.example.ifbademo.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.ifbademo.model.Cargo;
import com.example.ifbademo.service.CargoService;

@Component
@FacesConverter(value = "cargoConverter")
public class CargoConverter implements Converter{

    //Cria uma instancia do service para ter acesso ao metodo porId
    @Autowired
    private CargoService cargoService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Cargo retorno = null;

        //if(valor != null && !"".equals(valor)){
        if(value != null && ! value.isEmpty()){
            retorno = cargoService.buscarPorId(Long.valueOf(value));
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object o) {
        if(o != null){
            Cargo cargo = (Cargo) o;
            return cargo.getId() == null ? null : cargo.getId().toString();
        }
        return null;
    }
    
}
