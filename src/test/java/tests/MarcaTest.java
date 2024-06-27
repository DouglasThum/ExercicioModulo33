package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.After;
import org.junit.Test;

import dao.IMarcaDAO;
import dao.MarcaDAO;
import domain.Marca;

public class MarcaTest {
	private IMarcaDAO marcaDAO;
	
	public MarcaTest() {
		this.marcaDAO = new MarcaDAO();
	}
	
	@After
	public void end() {
		List<Marca> marcas = marcaDAO.buscarTodos();		
		for (Marca m : marcas) {
			marcaDAO.excluir(m);
		}
	}

	@Test
	public void cadastrar() {
		Marca marca = instanciarMarca("M1", "Chevrolet", "EUA");
		marcaDAO.cadastrar(marca);
		
		Marca marcaConsulta = marcaDAO.buscar(marca.getId());
		assertNotNull(marcaConsulta.getId());
		assertEquals(marca.getId(), marcaConsulta.getId());
		
		marcaDAO.excluir(marca);
		
		marcaConsulta = marcaDAO.buscar(marca.getId());
		
		assertNull(marcaConsulta);
	}
	
	@Test
	public void buscarTodos() {
		Marca m1 = instanciarMarca("M1", "Chevrolet", "EUA");
		Marca m2 = instanciarMarca("M2", "Toyota", "Japão");
		marcaDAO.cadastrar(m1);
		marcaDAO.cadastrar(m2);
		
		List<Marca> listMarcas = marcaDAO.buscarTodos();
		assertEquals(listMarcas.size(), 2);
	
		marcaDAO.excluirTodos(listMarcas);
		
		listMarcas = marcaDAO.buscarTodos();
		assertEquals(listMarcas.size(), 0);
	}
	
	private Marca instanciarMarca(String codigo, String nome, String nacionalidade) {
		Marca marca = new Marca();
		
		marca.setCodigo(codigo);
		marca.setNome(nome);
		marca.setNacionalidade(nacionalidade);
		return marca;
	}
}
