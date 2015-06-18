package br.edu.unisep.vo;

import java.time.LocalDate;

public class NotaVO {
	
	private Integer id;
	private Double valorTotal;
	private LocalDate data;
	private Double qtdTotal;
	
	public Double getQtdTotal() {
		return qtdTotal;
	}
	public void setQtdTotal(Double qtdTotal) {
		this.qtdTotal = qtdTotal;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public String toString(){
		return "R$ "+ valorTotal + " -- " + qtdTotal + " Iten(s)";
	}
}
