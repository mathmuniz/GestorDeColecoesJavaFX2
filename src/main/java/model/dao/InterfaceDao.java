/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.util.List;

/**
 *
 * @author mmuni
 */
public interface InterfaceDao<T> {
    
    public abstract void incluir(T entidade) throws Exception;
    public abstract void editar(T entidade) throws Exception;
    public abstract void excluir(T entidade) throws Exception;
    public abstract  T pesquisarPorId(int id) throws Exception;
    public abstract List<T> listar(String filtro) throws Exception;
    
}