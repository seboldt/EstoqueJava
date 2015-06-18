package br.edu.unisep.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unisep.vo.GondolaVO;

public class GondolaDAO extends DAOGenerico {

	public GondolaDAO() {
		super("db_estoque");
	}
	
	public List<GondolaVO> listarGondolas(){
		List<GondolaVO> listaGondolas = new ArrayList<GondolaVO>();
		
		try {
			Connection con = obterConexao();
			
			PreparedStatement ps = con.prepareStatement("select * from schsqlestoque.tab_gondola");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				GondolaVO gondola = new GondolaVO();
				
				Integer id = rs.getInt("id_gondola");
				gondola.setId(id);
				
				String gond = rs.getString("ds_gondola");
				gondola.setGondola(gond);
				
				Integer prateleira = rs.getInt("num_prateleira");
				gondola.setPrateleira(prateleira);
				
				listaGondolas.add(gondola);
				
			}
			
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro de SQL" + e.getMessage());
			e.printStackTrace();
		}
		return listaGondolas;
	} 
	
}
