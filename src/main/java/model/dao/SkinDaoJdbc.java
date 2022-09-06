/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Skin;

/**
 *
 * @author mmuni
 */
public class SkinDaoJdbc implements InterfaceDao<Skin> {

    private Connection conn;

    public SkinDaoJdbc() throws Exception {
        this.conn = ConnFactory.getConnection();
    }

    @Override
    public void incluir(Skin entidade) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO skin (nome, colecao, preco, status, foto) VALUES(?, ?, ?, ?, ?)");
            ps.setString(1, entidade.getNome());
            ps.setString(2, entidade.getColecao());
            ps.setString(3, entidade.getPreco());
            ps.setString(4, entidade.getStatus());
            ps.setString(5, entidade.getFoto());
            ps.execute();

        } finally {
            if (conn != null) {
                conn.close();
            }

        }

    }

    @Override
    public void editar(Skin entidade) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE skin SET nome=?, colecao=?, preco=?, status=?, foto=? WHERE id=?");
            ps.setString(1, entidade.getNome());
            ps.setString(2, entidade.getColecao());
            ps.setString(3, entidade.getPreco());
            ps.setString(4, entidade.getStatus());
            ps.setString(5, entidade.getFoto());
            ps.setInt(6, entidade.getId());
            ps.execute();

        } finally {
            if (conn != null) {
                conn.close();
            }

        }
    }

    @Override
    public void excluir(Skin entidade) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM skin WHERE id=?");
            ps.setInt(1, entidade.getId());
            ps.execute();

        } finally {
            if (conn != null) {
                conn.close();
            }

        }

    }

    @Override
    public Skin pesquisarPorId(int id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM skin WHERE id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Skin s = new Skin();
                s.setId(rs.getInt("id"));
                s.setNome(rs.getString("nome"));
                s.setColecao(rs.getString("colecao"));
                s.setPreco(rs.getString("preco"));
                s.setStatus(rs.getString("status"));
                s.setFoto(rs.getString("foto"));
                return s;

            } else {
                return null;
            }

        } finally {
            ps.close();
            rs.close();
        }
    }

    @Override
    public List<Skin> listar(String param) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if(param.equals(" ")) {
            ps = conn.prepareStatement("SELECT * FROM skin");
            }else{
                ps = conn.prepareStatement("SELECT * FROM skin WHERE nome LIKE '%"+ param + "%'");
            }
            rs = ps.executeQuery();
            List<Skin> lista = new ArrayList();
            while (rs.next()) {
                Skin s = new Skin();
                s.setId(rs.getInt("id"));
                s.setNome(rs.getString("nome"));
                s.setColecao(rs.getString("colecao"));
                s.setPreco(rs.getString("preco"));
                s.setStatus(rs.getString("status"));
                s.setFoto(rs.getString("foto"));
                lista.add(s);

            }
            return lista;
        } finally {
            if (conn != null) {
                rs.close();
            }
        }
    }
}
