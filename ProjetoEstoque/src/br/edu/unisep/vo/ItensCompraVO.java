package br.edu.unisep.vo;

public class ItensCompraVO {
	private Integer id;
	private ProdutosVO produto;
	private NotaVO nota;
	private Double valor;
	private Double quantidade;
	private GondolaVO gond;
	
	public GondolaVO getGond() {
		return gond;
	}
	public void setGond(GondolaVO gond) {
		this.gond = gond;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ProdutosVO getProduto() {
		return produto;
	}
	public void setProduto(ProdutosVO produto) {
		this.produto = produto;
	}
	public NotaVO getNota() {
		return nota;
	}
	public void setNota(NotaVO nota) {
		this.nota = nota;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
}
