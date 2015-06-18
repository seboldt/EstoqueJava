package br.edu.unisep.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.unisep.dao.ItensCompraDAO;
import br.edu.unisep.dao.NotasDAO;
import br.edu.unisep.dao.ProdutosDAO;
import br.edu.unisep.vo.ItensCompraVO;
import br.edu.unisep.vo.NotaVO;
import br.edu.unisep.vo.ProdutosVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ItensCompraController implements Initializable {
	
	@FXML private ComboBox<ProdutosVO> cmbProduto;
	
	@FXML private TextField txtValor;
	@FXML private TextField txtQuantidade;
	
	@FXML private TableView<ItensCompraVO> tabItens;
	@FXML private TableColumn<ProdutosVO, String> colProduto;
	@FXML private TableColumn<ItensCompraVO, Double> colQuantidade;
	@FXML private TableColumn<ItensCompraVO, Double> colValor;
	@FXML private DatePicker dtCompra;
	
	private ObservableList<ProdutosVO> listaProdutos;
	private ObservableList<ItensCompraVO> listaItens;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//NotasDAO daoN = new NotasDAO();
		
		//daoN.criarNota();
				
		listaProdutso();
	}
	
	public void finalizarCompra(ActionEvent event){
		NotaVO nota = new NotaVO();
		
		nota.setData(dtCompra.getValue());
		//nota.setValorTotal(valorTotal);
		
		NotasDAO dao = new NotasDAO(); 
		
		dao.atualizarNota(nota);

	}
	
	public void adicionarItens(ActionEvent event){
		ItensCompraVO itens = new ItensCompraVO();
		
		itens.setProduto(cmbProduto.getValue());
		itens.setQuantidade(Double.parseDouble(txtQuantidade.getText()));
		itens.setValor(Double.parseDouble(txtValor.getText()));
		
		ItensCompraDAO dao = new ItensCompraDAO();
		
		dao.listaItens(itens);
		
		cmbProduto.setValue(null);
		txtQuantidade.setText("");
		txtValor.setText("");
		
		listarItensCompra();
	}
	
	public void listaProdutso(){
		ProdutosDAO dao = new ProdutosDAO();
		List<ProdutosVO> lista = dao.listar();
		
		listaProdutos = FXCollections.observableArrayList();
		listaProdutos.setAll(lista);
		
		cmbProduto.setItems(listaProdutos);
		
	}
	
	public void listarItensCompra(){
		
		ItensCompraDAO dao = new ItensCompraDAO();
		
		List<ItensCompraVO> lista = dao.listarItens();
		
		listaItens = FXCollections.observableArrayList();
		listaItens.setAll(lista);
		
		colProduto.setCellValueFactory(new PropertyValueFactory<ProdutosVO, String>("nome"));
		colQuantidade.setCellValueFactory(new PropertyValueFactory<ItensCompraVO, Double>("quantidade"));
		colValor.setCellValueFactory(new PropertyValueFactory<ItensCompraVO, Double>("valor"));
		
		tabItens.setItems(listaItens);
	}
	
	
}
