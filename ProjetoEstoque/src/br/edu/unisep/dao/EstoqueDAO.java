package br.edu.unisep.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.unisep.vo.GondolaVO;
import br.edu.unisep.vo.ItensCompraVO;
import br.edu.unisep.vo.NotaVO;
import br.edu.unisep.vo.ProdutosVO;

public class EstoqueDAO extends DAOGenerico {

	public EstoqueDAO(){
		super("db_estoque");
	}
	
	public List<ItensCompraVO> listar(){
		
		List<ItensCompraVO> listaEstoque = new ArrayList<ItensCompraVO>();
		
		try {
			Connection con = obterConexao();
			
			PreparedStatement ps = con.prepareStatement("select sum(it.vl_produto) as valor_total, "
					+ "sum(it.qtd_produto) as quantidade_total, p.ds_produto, "
					+ "g.ds_gondola, g.num_prateleira from schsqlestoque.tab_itenscompra"
					+ " it inner join schsqlestoque.tab_produtos p on (it.id_produto = p.id_produto) "
					+ "inner join schsqlestoque.tab_gondola g on (p.id_gondola = g.id_gondola)"
					+ " group by p.ds_produto, ds_gondola, num_prateleira");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				NotaVO nota = new NotaVO();
				
				Double total = rs.getDouble("valor_total");
				nota.setValorTotal(total);
				
				Double qtd = rs.getDouble("quantidade_total");
				nota.setQtdTotal(qtd);
				
				ProdutosVO produto = new ProdutosVO();
				
				String prod = rs.getString("ds_produto");
				produto.setNome(prod);
				
				GondolaVO gond = new GondolaVO();
				
				String gondola = rs.getString("ds_gondola");
				gond.setGondola(gondola);
				
				Integer prateleira = rs.getInt("num_prateleira");
				gond.setPrateleira(prateleira);
				
				ItensCompraVO itens = new ItensCompraVO();
				
				itens.setGond(gond);
				
				itens.setNota(nota);
				itens.setProduto(produto);
				
				listaEstoque.add(itens);
				
			}
			
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			System.out.println("Erro de SQL" + e.getMessage());
			e.printStackTrace();
		}
		
		return listaEstoque;
	
	}
	
}
