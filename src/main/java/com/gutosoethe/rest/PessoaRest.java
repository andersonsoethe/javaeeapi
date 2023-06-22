package com.gutosoethe.rest;

import javax.ws.rs.Path;

import com.gutosoethe.bo.PessoaBo;
import com.gutosoethe.dto.PessoaDTO;
import com.gutosoethe.model.Pessoa;
import com.gutosoethe.vo.PessoaVo;

@Path("/pessoas")
public class PessoaRest extends GenericsRest<PessoaVo, PessoaDTO, Pessoa, Long, PessoaBo> {

    public PessoaRest() {
        super(PessoaVo.class, PessoaDTO.class);
    }

}