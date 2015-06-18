package br.edu.unisep.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.unisep.dao.GondolaDAO;
import br.edu.unisep.dao.ProdutosDAO;
import br.edu.unisep.vo.GondolaVO;
import br.edu.unisep.vo.ProdutosVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProdutosController implements Initializable {

	@FXML private TextField txtProduto;
	@FXML private ComboBox<GondolaVO> cmbGondola;
	
	@FXML private TableView<ProdutosVO> tabProdutos;
	@FXML private TableColumn<ProdutosVO, Integer> colId;
	@FXML private TableColumn<ProdutosVO, String> colProduto;
	@FXML private TableColumn<GondolaVO, String> colGondola;
	@FXML private TableColumn<GondolaVO, Integer> colPrateleira;
	
	private ObservableList<ProdutosVO> listaProdutos;
	private ObservableList<GondolaVO> listaGondola;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		obterListaGondola();
		obterListaProdutos();
	}
	
	public void obterListaGondola(){
		GondolaDAO dao = new GondolaDAO();
		
		List<GondolaVO> lista = dao.listarGondolas();
		
		listaGondola = FXCollections.observableArrayList();
		listaGondola.setAll(lista);
		
		cmbGondola.setItems(listaGondola);
	}
	
	public void obterListaProdutos(){
		ProdutosDAO dao = new ProdutosDAO();
		
		List<ProdutosVO> lista = dao.listar();
		
		listaProdutos = FXCollections.observableArrayList();
		listaProdutos.setAll(lista);
		
		colId.setCellValueFactory(new PropertyValueFactory<ProdutosVO, Integer>("id"));
		colProduto.setCellValueFactory(new PropertyValueFactory<ProdutosVO, String>("nome"));
		colGondola.setCellValueFactory(new PropertyValueFactory<GondolaVO, String>("gondola"));
		
		tabProdutos.setItems(listaProdutos);
		
	}
	
	public void salvar(ActionEvent event){
		ProdutosVO produto = new ProdutosVO();
		
		produto.setNome(txtProduto.getText());
		produto.setGondola(cmbGondola.getValue());
		
		ProdutosDAO dao = new ProdutosDAO();
		
		dao.inserirProduto(produto);
		
		txtProduto.setText("");
		cmbGondola.setValue(null);
		
		List<ProdutosVO> lista = dao.listar();
		listaProdutos.setAll(lista);
	}
	
	@FXML
	public void excluir(ActionEvent event){
		ProdutosVO produto = tabProdutos.getSelectionModel().getSelectedItem();
		
		if(produto != null){
			ProdutosDAO dao = new ProdutosDAO();
			dao.removerProduto(produto);
			
			List<ProdutosVO> lista = dao.listar();
			listaProdutos.setAll(lista);
		}
	}

}
