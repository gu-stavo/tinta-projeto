package br.com.curso.dao;

import br.com.curso.model.TipoPagamento;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoPagamentoDAO implements GenericDAO{

    private Connection conexao;
    
    public TipoPagamentoDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }
    
    @Override
    public Boolean cadastrar(Object objeto) {
        TipoPagamento oTipoPagamento = (TipoPagamento) objeto;
        Boolean retorno=false;
        if (oTipoPagamento.getIdTipo()== 0) {
            retorno = this.inserir(oTipoPagamento);
        }else{
            retorno = this.alterar(oTipoPagamento);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        TipoPagamento oTipoPagamento = (TipoPagamento) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into tipopagamento (nometipo) values (?)";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oTipoPagamento.getNomeTipo());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try {
                System.out.println("Problemas ao cadastrar o Tipo de Pagamento! Erro: "+ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            } catch (SQLException e) {;
                System.out.println("Erro:"+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean alterar(Object objeto) {
        TipoPagamento oTipoPagamento = (TipoPagamento) objeto;
        PreparedStatement stmt = null;
        String sql = "update tipopagamento set nometipo=? where idtipo=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oTipoPagamento.getNomeTipo());
            stmt.setInt(2, oTipoPagamento.getIdTipo());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try {
                System.out.println("Problemas ao alterar o Tipo de Pagamento! Erro: "+ex.getMessage());
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
        int idTipo = numero;
        PreparedStatement stmt= null;
        
        String sql = "delete from tipopagamento where idtipo=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idTipo);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao excluir o Tipo de Pagamento! Erro: "
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
        int idTipo = numero;
        PreparedStatement stmt = null;
        ResultSet rs= null;
        TipoPagamento oTipoPagamento = null;
        String sql="select * from tipopagamento where idTipo=?";
        
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idTipo);
            rs=stmt.executeQuery();
            while (rs.next()) {
                oTipoPagamento = new TipoPagamento();
                oTipoPagamento.setIdTipo(rs.getInt("idTipo"));
                oTipoPagamento.setNomeTipo(rs.getString("nometipo"));
            }
            return oTipoPagamento;
        } catch (Exception ex) {
            System.out.println("Problemas ao carregar Tpo de Pagamento! Erro:"+ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from tipopagamento order by idtipo";
        try {
            stmt = conexao.prepareStatement(sql);
            rs=stmt.executeQuery();
            while (rs.next()) {
                TipoPagamento oTipoPagamento = new TipoPagamento();
                oTipoPagamento.setIdTipo(rs.getInt("idTipo"));
                oTipoPagamento.setNomeTipo(rs.getString("nometipo"));
                resultado.add(oTipoPagamento);
            }
            
        }catch (SQLException ex) {
            System.out.println("Problemas ao listar Tipo de Pagamento! Erro: "
                    +ex.getMessage());
        }
        return resultado;
    }

    
}
