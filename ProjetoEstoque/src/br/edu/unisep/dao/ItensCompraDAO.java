package br.edu.unisep.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unisep.vo.ItensCompraVO;
import br.edu.unisep.vo.NotaVO;
import br.edu.unisep.vo.ProdutosVO;

public class ItensCompraDAO extends DAOGenerico {
	
	public ItensCompraDAO(){
		super("db_estoque");
	}
	
	public void listaItens(ItensCompraVO itens){
		try {
			Connection con = obterConexao();
			
			PreparedStatement ps = con.prepareStatement("insert into schsqlestoque.tab_itenscompra "
					+ "(id_produto, id_nota,vl_produto, qtd_produto) "
					+ "values (?, ?,?,?)");
			
			ps.setInt(1, itens.getProduto().getId());
			ps.setInt(2, itens.getNota().getId());
			ps.setDouble(3, itens.getValor());
			ps.setDouble(4, itens.getQuantidade());
			
			ps.execute();
			ps.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro de SQL : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public List<ItensCompraVO> listarItens(){
		List<ItensCompraVO> listaItens = new ArrayList<ItensCompraVO>();
		ItensCompraVO itens = new ItensCompraVO();
		try {
			Connection con = obterConexao();
			
			PreparedStatement ps = con.prepareStatement("select * from schsqlestoque.tab_itenscompra it inner join "
					+ "schsqlestoque.tab_produtos p on (it.id_produto = p.id_produto) where id_nota = ?");
			
			ps.setInt(1, itens.getNota().getId());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Integer id = rs.getInt("id_itenscompra");
				itens.setId(id);
				
				Double valor = rs.getDouble("vl_produto");
				itens.setValor(valor);
				
				Double quantidade = rs.getDouble("qtd_produto");
				itens.setQuantidade(quantidade);
				
				ProdutosVO produto = new ProdutosVO();
				
				String prod = rs.getString("ds_produto");
				produto.setNome(prod);
				
				NotaVO nota = new NotaVO();
				
				Integer n = rs.getInt("id_nota");
				nota.setId(n);
				
				itens.setNota(nota);
				itens.setProduto(produto);
				
				listaItens.add(itens);
			}
			
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro de SQL" + e.getMessage());
			e.printStackTrace();
		}
			return listaItens;	
	}
	
	public void excluirIten(ItensCompraVO itens){
		try {
			Connection con = obterConexao();
			
			PreparedStatement ps = con.prepareStatement("delete from schsqlestoque.tab_itenscompra where id_itenscompra = ?");
			
			ps.setInt(1, itens.getId());
			
			ps.execute();
			ps.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("ERRO EXCLUSAO " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
}
