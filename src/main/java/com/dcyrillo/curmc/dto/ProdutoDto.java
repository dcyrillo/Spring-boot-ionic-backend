package com.dcyrillo.curmc.dto;

import java.io.Serializable;

import com.dcyrillo.curmc.domain.Produto;

public class ProdutoDto  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private double preco;
	
	public ProdutoDto() {
		
	}

	public ProdutoDto(Integer id, String nome, double preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}
	public ProdutoDto(Produto obj) {
		id=obj.getId();
		nome=obj.getNome();
		preco=obj.getPreco();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	
}
