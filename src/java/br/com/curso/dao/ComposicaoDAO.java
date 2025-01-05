package br.com.curso.dao;

import br.com.curso.model.Composicao;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComposicaoDAO implements GenericDAO{
    
    private Connection conexao;
    
    public ComposicaoDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Composicao oComposicao = (Composicao) objeto;
        Boolean retorno=false;
        if (oComposicao.getIdComposicao()== 0) {
            retorno = this.inserir(oComposicao);
        }else{
            retorno = this.alterar(oComposicao);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Composicao oComposicao = (Composicao) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into composicao (nomecomposicao) values (?)";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oComposicao.getNomeComposicao());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try {
                System.out.println("Problemas ao cadastrar a Composicao! Erro: "+ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            } catch (SQLException e) {
                System.out.println("Erro:"+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean alterar(Object objeto) {
        Composicao oComposicao = (Composicao) objeto;
        PreparedStatement stmt = null;
        String sql = "update composicao set nomecomposicao=? where idcomposicao=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oComposicao.getNomeComposicao());
            stmt.setInt(2, oComposicao.getIdComposicao());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try {
                System.out.println("Problemas ao alterar a Composicao! Erro: "+ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            } catch (SQLException e) {
                System.out.println("Erro:"+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean excluir(int numero) {
        int idComposicao = numero;
        PreparedStatement stmt= null;
        String sql = "delete from composicao where idcomposicao=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idComposicao);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao excluir a Composicao! Erro: "
                    +ex.getMessage());
            try {
                conexao.rollback();
            } catch (SQLException e) {
                System.out.println("Erro rolback "+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Object carregar(int numero) {
        int idComposicao = numero;
        PreparedStatement stmt = null;
        ResultSet rs= null;
        Composicao oComposicao = null;
        String sql="select * from composicao where idComposicao=?";
        
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idComposicao);
            rs=stmt.executeQuery();
            while (rs.next()) {
                oComposicao = new Composicao();
                oComposicao.setIdComposicao(rs.getInt("idComposicao"));
                oComposicao.setNomeComposicao(rs.getString("nomecomposicao"));
            }
            return oComposicao;
        } catch (Exception ex) {
            System.out.println("Problemas ao carregar Composicao! Erro:"+ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from composicao order by idComposicao";
        try {
            stmt = conexao.prepareStatement(sql);
            rs=stmt.executeQuery();
            while (rs.next()) {
                Composicao oComposicao = new Composicao();
                oComposicao.setIdComposicao(rs.getInt("idComposicao"));
                oComposicao.setNomeComposicao(rs.getString("nomecomposicao"));
                resultado.add(oComposicao);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar Composição! Erro: "
                    +ex.getMessage());
        }
        return resultado;
    }

    
}
