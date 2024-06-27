package dao;

import java.util.List;

import domain.Carro;

public interface ICarroDAO {
	
	Carro cadastrar(Carro carro);
	Carro buscar(Long id);
	List<Carro> buscarTodos();
	void excluir(Carro carro);
	void excluirTodos(List<Carro> list);
}
