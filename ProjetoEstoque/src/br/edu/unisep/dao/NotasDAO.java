package br.edu.unisep.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.edu.unisep.vo.NotaVO;

public class NotasDAO extends DAOGenerico {
	public NotasDAO(){
		super("db_estoque");
	}
	
	public void criarNota(){
		try {
			Connection con = obterConexao();
			
			PreparedStatement ps = con.prepareStatement("insert into schsqlestoque.tab_nota(vl_total) values ('0')");
			
			ps.execute();
			ps.close();
			con.close();
			
		} catch (SQLException e) {
			System.out.println("Erro de SQL : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void atualizarNota(NotaVO nota){
		try {
			Connection con = obterConexao();
			
			PreparedStatement ps = con.prepareStatement("update schsqlestoque.tab_nota set dt_compra='?', "
					+ "vl_total = (select SUM(vl_produto) from schsqlestoque.tab_itenscompra where id_nota = ?) "
					+ "where id_nota = ?");
			
			Date data = Date.valueOf(nota.getData());
			ps.setDate(1, data);
			
			
			ps.setInt(2, nota.getId());	
			ps.setInt(3, nota.getId());
			
			ps.execute();
			ps.close();
			con.close();
			
		} catch (SQLException e) {
			System.out.println("Erro de SQL : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void excluirNota(NotaVO nota){
		try {
			Connection con = obterConexao();
			
			PreparedStatement ps = con.prepareStatement("delete from schsqlestoque.tab_nota where id_nota = ?");
			
			ps.setInt(1, nota.getId());
			
			ps.execute();
			ps.close();
			con.close();
			
		} catch (SQLException e) {
			System.out.println("Erro de SQL : " + e.getMessage());
			e.printStackTrace();
		}
	}
}
