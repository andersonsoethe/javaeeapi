package com.gutosoethe.rest;

import javax.ws.rs.Path;

import com.gutosoethe.bo.DepartamentoBo;
import com.gutosoethe.dto.DepartamentoDTO;
import com.gutosoethe.model.Departamento;
import com.gutosoethe.vo.DepartamentoVo;

@Path("/departamentos")
public class DepartamentoRest extends GenericsRest<DepartamentoVo, DepartamentoDTO, Departamento, Long, DepartamentoBo>{

    public DepartamentoRest() {
        super(DepartamentoVo.class, DepartamentoDTO.class);
    }
}
