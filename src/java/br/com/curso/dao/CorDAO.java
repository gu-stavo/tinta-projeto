package br.com.curso.dao;

import br.com.curso.model.Cor;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CorDAO implements GenericDAO{

    private Connection conexao;
    
    public CorDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }
    
    @Override
    public Boolean cadastrar(Object objeto) {
       Cor oCor = (Cor) objeto;
       Boolean retorno=false;
       if (oCor.getIdCor()== 0) {
           retorno = this.inserir(oCor);
       }else{
           retorno = this.alterar(oCor);
       }
       return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
       Cor oCor = (Cor)   objeto;
       PreparedStatement stmt = null;
       String sql = "insert into cor (nomecor) values (?)";
       try {
           stmt = conexao.prepareStatement(sql);
           stmt.setString(1, oCor.getNomeCor());
           stmt.execute();
           conexao.commit();
           return true;
        } catch (Exception ex) {
           try {
               System.out.println("Problemas ao cadastar a Cor! Erro: "+ex.getMessage());
               ex.printStackTrace();
               conexao.rollback();
            } catch (SQLException e) {
                System.out.println("Erro:" +e.getMessage());
                e.printStackTrace();
            }
           return false;
        }
    }

    @Override
    public Boolean alterar(Object objeto) {
       Cor oCor = (Cor) objeto;
       PreparedStatement stmt = null;
       String sql = "update cor set nomecor=? where idcor=?";
       try {
           stmt = conexao.prepareStatement(sql);
           stmt.setString(1, oCor.getNomeCor());
           stmt.setInt(2, oCor.getIdCor());
           stmt.execute();
           conexao.commit();
           return true;
       } catch (Exception ex) {
           try {
               System.out.println("Problemas ao alterar a Cor! Erro: "+ex.getMessage());
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
        int idCor = numero;
        PreparedStatement stmt= null;
        
        String sql = "delete from cor where idcor=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idCor);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao excluir a Cor! Erro: "
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
        int idCor = numero;
        PreparedStatement stmt = null;
        ResultSet rs= null;
        Cor oCor = null;
        String sql="select * from cor where idCor=?";
        
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idCor);
            rs=stmt.executeQuery();
            while (rs.next()) {
                oCor = new Cor();
                oCor.setIdCor(rs.getInt("idCor"));
                oCor.setNomeCor(rs.getString("nomecor"));
            }
            return oCor;
        } catch (Exception ex) {
            System.out.println("Problemas ao carregar Cor! Erro:"+ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from cor order by idCor";        
        try {
            stmt = conexao.prepareStatement(sql);
            rs=stmt.executeQuery();
            while (rs.next()) {
                Cor oCor = new Cor();
                oCor.setIdCor(rs.getInt("idCor"));
                oCor.setNomeCor(rs.getString("nomecor"));
                resultado.add(oCor);
            }   
        }catch (SQLException ex) {
            System.out.println("Problemas ao listar Cor! Erro: "
                    +ex.getMessage());
        }
        return resultado;
       
    }

    
}
