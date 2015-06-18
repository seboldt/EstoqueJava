package br.edu.unisep.vo;

public class GondolaVO {

	private Integer id;
	private String gondola;
	private Integer prateleira;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGondola() {
		return gondola;
	}
	public void setGondola(String gondola) {
		this.gondola = gondola;
	}
	public Integer getPrateleira() {
		return prateleira;
	}
	public void setPrateleira(Integer prateleira) {
		this.prateleira = prateleira;
	}	
	
	public String toString(){
		return gondola + " - " + prateleira;
	}
	
}
