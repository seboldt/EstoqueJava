package br.edu.unisep.vo;

public class ProdutosVO {
	private Integer id;
	private GondolaVO gondola;
	private String nome;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public GondolaVO getGondola() {
		return gondola;
	}
	public void setGondola(GondolaVO gondola) {
		this.gondola = gondola;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toString(){
		return nome;
	}
}
