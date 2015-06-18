package br.edu.unisep.view;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import br.edu.unisep.dao.RelatorioDAO;
import br.edu.unisep.vo.NotaVO;
import br.edu.unisep.vo.RelatorioVO;

public class RelatorioController implements Initializable{
	
	@FXML private TableView<NotaVO> tabRelatorio;
	@FXML private TableColumn<NotaVO, Integer> colNota;
	@FXML private TableColumn<NotaVO, Date> colData;
	@FXML private TableColumn<NotaVO, Double> colValor;
	
	@FXML private DatePicker dtInicial;
	@FXML private DatePicker dtFinal;
	
	private ObservableList<NotaVO> listaNota;
	
	@FXML
	public void pesquisar(ActionEvent event){
		
		RelatorioVO relatorio = new RelatorioVO();
		relatorio.setDataInicial(dtInicial.getValue());
		relatorio.setDataFinal(dtFinal.getValue());	
		
		RelatorioDAO dao = new RelatorioDAO();
		
		List<NotaVO> listar = dao.listarRelatorio(relatorio);
		
		listaNota = FXCollections.observableArrayList();
		listaNota.setAll(listar);
		
		colNota.setCellValueFactory(new PropertyValueFactory<NotaVO, Integer>("id"));
		
		colData.setCellValueFactory(new PropertyValueFactory<NotaVO, Date>("data"));
		colValor.setCellValueFactory(new PropertyValueFactory<NotaVO, Double>("valorTotal"));
		
		tabRelatorio.setItems(listaNota);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DateTimeFormatter myDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
	}
}
