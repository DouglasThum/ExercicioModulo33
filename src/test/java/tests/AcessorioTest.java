package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import dao.AcessorioDAO;
import dao.CarroDAO;
import dao.IAcessorioDAO;
import dao.ICarroDAO;
import dao.IMarcaDAO;
import dao.MarcaDAO;
import domain.Acessorio;
import domain.Carro;
import domain.Marca;

public class AcessorioTest {

	private IAcessorioDAO acessorioDAO;
	private ICarroDAO carroDAO;
	private IMarcaDAO marcaDAO;
	
	public AcessorioTest() {
		this.acessorioDAO = new AcessorioDAO();
		this.carroDAO = new CarroDAO();
		this.marcaDAO = new MarcaDAO();
	}
	
	@After
	public void end() {
		List<Acessorio> acessorios = acessorioDAO.buscarTodos();
		List<Carro> carros = carroDAO.buscarTodos();
		List<Marca> marcas = marcaDAO.buscarTodos();
		
		for (Acessorio a : acessorios) {
			acessorioDAO.excluir(a);
		}
		
		for (Carro c : carros) {
			carroDAO.excluir(c);
		}
		
		for (Marca m : marcas) {
			marcaDAO.excluir(m);
		}
	}
	
	@Test
	public void cadastrar() {
		List<Acessorio> list = new ArrayList<>();
		Acessorio acessorio = instanciarAcessorio("A1", "Ar condicionado");
		list.add(acessorio);
		Marca marca = instanciarMarca("M1", "Chevrolet", "EUA");
		Carro carro = instanciarCarro("C1", "Onix", 2020l, "prata", list);
		carro.setMarca(marca);
		carroDAO.cadastrar(carro);
		assertNotNull(acessorio.getId());
		
		Acessorio acessorioConsulta = acessorioDAO.buscar(acessorio.getId());
		assertNotNull(acessorioConsulta.getId());
		assertEquals(acessorio.getId(), acessorioConsulta.getId());
		
		carro = carroDAO.buscar(carro.getId());
		carroDAO.excluir(carro);
		acessorioDAO.excluir(acessorio);
		marcaDAO.excluir(marca);
		
		acessorioConsulta = acessorioDAO.buscar(acessorio.getId());
		Carro carroConsulta = carroDAO.buscar(carro.getId());
		Marca marcaConsulta = marcaDAO.buscar(marca.getId());
		
		assertNull(acessorioConsulta);
		assertNull(carroConsulta);
		assertNull(marcaConsulta);
	}
	
	private Carro instanciarCarro(String codigo, String modelo, Long ano, String cor, List<Acessorio> list) {
		Carro carro = new Carro();
		
		carro.setCodigo(codigo);
		carro.setModelo(modelo);
		carro.setAno(ano);
		carro.setCor(cor);
		
		for(Acessorio a: list) {
			carro.addAcessorio(a);
		}
		
		return carro;
	}

	private Marca instanciarMarca(String codigo, String nome, String nacionalidade) {
		Marca marca = new Marca();
		
		marca.setCodigo(codigo);
		marca.setNome(nome);
		marca.setNacionalidade(nacionalidade);
		return marcaDAO.cadastrar(marca);
	}
	
	private Acessorio instanciarAcessorio(String codigo, String nome) {
		Acessorio acessorio = new Acessorio();
		acessorio.setCodigo(codigo);
		acessorio.setNome(nome);
		return acessorio;
	}
}
