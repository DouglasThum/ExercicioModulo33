package dao;

import java.util.List;

import domain.Acessorio;

public interface IAcessorioDAO {
	
	Acessorio cadastrar(Acessorio acessorio);
	Acessorio buscar(Long id);
	List<Acessorio> buscarTodos();
	void excluir(Acessorio acessorio);
	void excluirTodos(List<Acessorio> list);
}
