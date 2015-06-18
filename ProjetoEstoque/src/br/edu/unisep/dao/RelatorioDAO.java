package br.edu.unisep.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.edu.unisep.vo.NotaVO;
import br.edu.unisep.vo.RelatorioVO;

public class RelatorioDAO extends DAOGenerico {

	public RelatorioDAO(){
		super("db_estoque");
	}
	
	public List<NotaVO> listarRelatorio(RelatorioVO relatorio){
		List<NotaVO> listaR = new ArrayList<NotaVO>();
		
		try {
			Connection con = obterConexao();
			
			PreparedStatement ps = con.prepareStatement("select * from schsqlestoque.tab_nota as n "
					+ "where n.dt_compra between ? and ? ");
			
			Date dataInicial = Date.valueOf(relatorio.getDataInicial());
			ps.setDate(1, dataInicial);
			
			Date dataFinal = Date.valueOf(relatorio.getDataFinal());
			ps.setDate(2, dataFinal);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				NotaVO nota = new NotaVO();
				
				Integer id = rs.getInt("id_nota");
				nota.setId(id);
				
				Date data = rs.getDate("dt_compra");
				nota.setData(data.toLocalDate());
				
				Double valor = rs.getDouble("vl_total");
				nota.setValorTotal(valor);
				
				listaR.add(nota);
			}
			
			rs.close();
			ps.close();
			con.close();
			
		} catch (SQLException e) {
			System.out.println("Erro de SQL" + e.getMessage());
			e.printStackTrace();
		}
		
		return listaR;
	}
}
