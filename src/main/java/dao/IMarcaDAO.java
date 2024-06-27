package dao;

import java.util.List;

import domain.Marca;

public interface IMarcaDAO {
	
	Marca cadastrar(Marca marca);
	Marca buscar(Long id);
	List<Marca> buscarTodos();
	void excluir(Marca marca);
	void excluirTodos(List<Marca> list);
}
