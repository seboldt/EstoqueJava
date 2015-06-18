package br.edu.unisep.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import br.edu.unisep.dao.EstoqueDAO;
import br.edu.unisep.vo.ItensCompraVO;

public class EstoqueController implements Initializable {

	@FXML private TableView<ItensCompraVO> tabEstoque;
	
	@FXML private TableColumn<ItensCompraVO, String> colProduto;
	@FXML private TableColumn<ItensCompraVO, Double> colValor;
	@FXML private TableColumn<ItensCompraVO, String> colGond;
	
	private ObservableList<ItensCompraVO> listaItens;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EstoqueDAO dao = new EstoqueDAO();
		
		List<ItensCompraVO> lista = dao.listar();
		
		listaItens = FXCollections.observableArrayList();
		listaItens.setAll(lista);
		
		colProduto.setCellValueFactory(new PropertyValueFactory<ItensCompraVO, String>("produto"));
		colValor.setCellValueFactory(new PropertyValueFactory<ItensCompraVO, Double>("nota"));
		colGond.setCellValueFactory(new PropertyValueFactory<ItensCompraVO, String>("gond"));
		
		tabEstoque.setItems(listaItens);
		
	}

}
