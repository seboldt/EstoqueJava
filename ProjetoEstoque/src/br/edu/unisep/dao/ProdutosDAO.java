package br.edu.unisep.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unisep.vo.GondolaVO;
import br.edu.unisep.vo.ProdutosVO;

public class ProdutosDAO extends DAOGenerico {
	
	public ProdutosDAO() {
		super("db_estoque");
	}
	
	public void inserirProduto(ProdutosVO produto)
	{
		try {
			Connection con = obterConexao();
			
			PreparedStatement ps = con.prepareStatement("insert into schsqlestoque.tab_produtos (id_gondola, ds_produto) "
					+ "values (?,?)");
			
			ps.setInt(1, produto.getGondola().getId());
			ps.setString(2, produto.getNome());
			
			ps.execute();
			ps.close();
			con.close();			
			
		} catch (SQLException e) {
			System.out.println("Erro de SQL : " + e.getMessage());
			e.printStackTrace();
		}
	}
	public List<ProdutosVO> listar(){
		List<ProdutosVO> listaProdutos = new ArrayList<ProdutosVO>();
		
		try {
			Connection con = obterConexao();
			
			PreparedStatement ps = con.prepareStatement("select * from schsqlestoque.tab_produtos as p"
					+ " inner join schsqlestoque.tab_gondola as g on (p.id_gondola = g.id_gondola)");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				ProdutosVO produtos = new ProdutosVO();
				
				Integer id =rs.getInt("id_produto");
				produtos.setId(id);
				
				String nome = rs.getString("ds_produto");
				produtos.setNome(nome);
				
				GondolaVO gondola = new GondolaVO();
				
				String gond = rs.getString("ds_gondola");
				gondola.setGondola(gond);
				
				Integer prateleira = rs.getInt("num_prateleira");
				gondola.setPrateleira(prateleira);
				
				
				produtos.setGondola(gondola);
				listaProdutos.add(produtos);
				
			}
			rs.close();
			ps.close();
			con.close();
			
		} catch (SQLException e) {
			System.out.println("Erro de SQL" + e.getMessage());
			e.printStackTrace();
		}
		return listaProdutos;
	}
	
	public void removerProduto(ProdutosVO produto){
		try {
			Connection con = obterConexao();
			
			PreparedStatement ps = con.prepareStatement("delete from schsqlestoque.tab_produtos where id_produto=?");
			
			ps.setInt(1, produto.getId());
			
			ps.execute();
			ps.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("ERRO EXCLUSAO " + e.getMessage());
			e.printStackTrace();
		}
	}
}
