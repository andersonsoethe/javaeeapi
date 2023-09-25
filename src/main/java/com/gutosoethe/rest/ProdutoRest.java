package com.gutosoethe.rest;

import com.gutosoethe.bo.ProdutoBo;
import com.gutosoethe.dao.ProdutoJpaDao;
import com.gutosoethe.dto.ProdutoDTO;
import com.gutosoethe.model.Produto;
import com.gutosoethe.vo.ProdutoVo;

import javax.ws.rs.Path;

@Path("/produtos")
public class ProdutoRest extends GenericsRest<ProdutoVo, ProdutoDTO, Produto, Long, ProdutoJpaDao, ProdutoBo> {
    public ProdutoRest() {
        super(ProdutoVo.class, ProdutoDTO.class);
    }

}