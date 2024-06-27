package tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.After;
import org.junit.Test;

import dao.CarroDAO;
import dao.ICarroDAO;
import dao.IMarcaDAO;
import dao.MarcaDAO;
import domain.Carro;
import domain.Marca;

public class CarroTest {

	private ICarroDAO carroDAO;
	private IMarcaDAO marcaDAO;
	
	public CarroTest() {
		this.carroDAO = new CarroDAO();
		this.marcaDAO = new MarcaDAO();
	}
	
	@After
	public void end() {
		List<Carro> carros = carroDAO.buscarTodos();
		List<Marca> marcas = marcaDAO.buscarTodos();
		
		for (Carro c : carros) {
			carroDAO.excluir(c);
		}
		
		for (Marca m : marcas) {
			marcaDAO.excluir(m);
		}
	}
	
	@Test
	public void cadastrar() {
		Carro carro = instanciarCarro("C1", "Onix", 2020l, "prata");
		Marca marca = instanciarMarca("M1", "Chevrolet", "EUA");
		carro.setMarca(marca);
		carroDAO.cadastrar(carro);
		assertNotNull(carro.getId());
		
		Carro carroConsulta = carroDAO.buscar(carro.getId());
		assertNotNull(carroConsulta.getId());
		assertEquals(carro.getId(), carroConsulta.getId());
		
		carroDAO.excluir(carro);
		marcaDAO.excluir(marca);
		
		carroConsulta = carroDAO.buscar(carro.getId());
		Marca marcaConsulta = marcaDAO.buscar(marca.getId());
		
		assertNull(carroConsulta);
		assertNull(marcaConsulta);
	}
	
	@Test
	public void buscarTodos() {
		Carro c1 = instanciarCarro("C1", "Onix", 2020l, "prata");
		Carro c2 = instanciarCarro("C2", "Tracker", 2024l, "branco");
		Carro c3 = instanciarCarro("C3", "Corolla", 2023l, "preto");
		Marca m1 = instanciarMarca("M1", "Chevrolet", "EUA");
		Marca m2 = instanciarMarca("M2", "Toyota", "Japão");
		c1.setMarca(m1);
		c2.setMarca(m1);
		c3.setMarca(m2);
		
		carroDAO.cadastrar(c1);
		carroDAO.cadastrar(c2);
		carroDAO.cadastrar(c3);
		
		List<Carro> listCarros = carroDAO.buscarTodos();
		List<Marca> listMarcas = marcaDAO.buscarTodos();
		assertEquals(listCarros.size(), 3);
		assertEquals(listMarcas.size(), 2);
	
		carroDAO.excluirTodos(listCarros);
		marcaDAO.excluirTodos(listMarcas);
		
		listCarros = carroDAO.buscarTodos();
		listMarcas = marcaDAO.buscarTodos();
		assertEquals(listCarros.size(), 0);
		assertEquals(listMarcas.size(), 0);
	}

	private Carro instanciarCarro(String codigo, String modelo, Long ano, String cor) {
		Carro carro = new Carro();
		
		carro.setCodigo(codigo);
		carro.setModelo(modelo);
		carro.setAno(ano);
		carro.setCor(cor);
		
		return carro;
	}

	private Marca instanciarMarca(String codigo, String nome, String nacionalidade) {
		Marca marca = new Marca();
		
		marca.setCodigo(codigo);
		marca.setNome(nome);
		marca.setNacionalidade(nacionalidade);
		return marcaDAO.cadastrar(marca);
	}
}
