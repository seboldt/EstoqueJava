package br.edu.unisep.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class IndexController {
	
	@FXML private BorderPane corpo;
	
	@FXML
	public void abrirProdutos(MouseEvent envet)throws IOException{
		AnchorPane telaProdutos = (AnchorPane) FXMLLoader.load(getClass().getResource("Produtos.fxml"));
		
		corpo.getChildren().clear();
		
		corpo.setCenter(telaProdutos);
	}
	
	@FXML
	public void abrirItensCompra(MouseEvent event)throws IOException{
		AnchorPane telaItens = (AnchorPane) FXMLLoader.load(getClass().getResource("ItensCompra.fxml"));
		
		corpo.getChildren().clear();
		corpo.setCenter(telaItens);
	}
	
	@FXML
	public void abrirRelatorio(MouseEvent event)throws IOException{
		AnchorPane telaRelatorio = (AnchorPane) FXMLLoader.load(getClass().getResource("Relatorio.fxml"));
		
		corpo.getChildren().clear();
		corpo.setCenter(telaRelatorio);
	}
	
	@FXML
	public void abrirEstoque(MouseEvent event)throws IOException{
		AnchorPane telaEstoque = (AnchorPane) FXMLLoader.load(getClass().getResource("Estoque.fxml"));
		
		corpo.getChildren().clear();
		corpo.setCenter(telaEstoque);
	}
}
