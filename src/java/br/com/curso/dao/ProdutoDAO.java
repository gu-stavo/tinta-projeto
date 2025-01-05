package br.com.curso.dao;

import br.com.curso.model.Composicao;
import br.com.curso.model.Cor;
import br.com.curso.model.Marca;
import br.com.curso.model.Produto;
import br.com.curso.model.TipoPagamento;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO implements GenericDAO{

    private Connection conexao;
    
    public ProdutoDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }
    @Override
    public Boolean cadastrar(Object objeto) {
        Produto oProduto = (Produto) objeto;
        Boolean retorno = false;
        if (oProduto.getIdProduto() == 0){
            retorno = this.inserir(oProduto);
        }else{
            retorno = this.alterar(oProduto);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Produto oProduto = (Produto) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into produto (nomeproduto,situacao,preco,idcor,idmarca,idcomposicao,idtipo) values (?,?,?,?,?,?,?)";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oProduto.getNomeProduto());
            stmt.setString(2, oProduto.getSituacao());
            stmt.setDouble(3, oProduto.getPreco());
            stmt.setInt(4, oProduto.getCor().getIdCor());
            stmt.setInt(5, oProduto.getMarca().getIdMarca());
            stmt.setInt(6, oProduto.getComposicao().getIdComposicao());
            stmt.setInt(7, oProduto.getTipoPagamento().getIdTipo());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try {
                System.out.println("Problemas ao cadastrar Produto! Erro: "+ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            }catch (SQLException e) {
                System.out.println("Erro: "+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean alterar(Object objeto) {
        Produto oProduto = (Produto) objeto;
        PreparedStatement stmt = null;
        String sql = "update produto set nomeproduto=?,situacao=?,preco=?,idcor=?,idmarca=?,idcomposicao=?,idtipo=? where idproduto=?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oProduto.getNomeProduto());
            stmt.setString(2, oProduto.getSituacao());
            stmt.setDouble(3, oProduto.getPreco());
            stmt.setInt(4, oProduto.getCor().getIdCor());
            stmt.setInt(5, oProduto.getMarca().getIdMarca());
            stmt.setInt(6, oProduto.getComposicao().getIdComposicao());
            stmt.setInt(7, oProduto.getTipoPagamento().getIdTipo());
            stmt.setInt(8, oProduto.getIdProduto());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try{
                System.out.println("Problemas ao alterar o Produto! Erro:  "+ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            }catch (SQLException e){
                System.out.println("Erro: "+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean excluir(int numero) {
        int idProduto = numero;
        PreparedStatement stmt = null;
        String sql = "update produto set situacao=? where idproduto=?";
        try{
            Produto oProduto = (Produto) this.carregar(idProduto);
            stmt = conexao.prepareStatement(sql);
            if(oProduto.getSituacao().equals("A"))
                stmt.setString(1, "I");
            else stmt.setString(1, "A");
            stmt.setInt(2, idProduto);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao excluir Produto! Erro: "+ex.getMessage());
            try {
                conexao.rollback();
            } catch (SQLException e){
                System.out.println("Erro rollback "+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Object carregar(int numero) {
        int idProduto = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Produto oProduto = null;
        String sql = "select * from produto where idproduto=?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idProduto);
            rs = stmt.executeQuery();
            while(rs.next()){
                oProduto = new Produto();
                oProduto.setIdProduto(rs.getInt("idProduto"));
                oProduto.setNomeProduto(rs.getString("nomeproduto"));
                oProduto.setSituacao(rs.getString("situacao"));
                oProduto.setPreco(rs.getDouble("preco"));
                
                CorDAO oCorDAO = new CorDAO();
                oProduto.setCor((Cor) oCorDAO.carregar(rs.getInt("idcor")));
                
                MarcaDAO oMarcaDAO = new MarcaDAO();
                oProduto.setMarca((Marca) oMarcaDAO.carregar(rs.getInt("idmarca")));
                
                ComposicaoDAO oComposicaoDAO = new ComposicaoDAO();
                oProduto.setComposicao((Composicao) oComposicaoDAO.carregar(rs.getInt("idcomposicao")));
                
                TipoPagamentoDAO oTipoPagamentoDAO = new TipoPagamentoDAO();
                oProduto.setTipoPagamento((TipoPagamento) oTipoPagamentoDAO.carregar(rs.getInt("idtipo")));
            }
            return oProduto;
        }catch(Exception ex){
            System.out.println("Problemas ao carregar Produto! Erro:"+ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from produto order by nomeproduto";
        try{
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Produto oProduto = new Produto();
                oProduto.setIdProduto(rs.getInt("idProduto"));
                oProduto.setNomeProduto(rs.getString("nomeproduto"));
                oProduto.setSituacao(rs.getString("situacao"));
                oProduto.setPreco(rs.getDouble("preco"));
                
                CorDAO oCorDAO = null;
                try {
                    oCorDAO = new CorDAO();
                } catch (Exception ex) {
                    System.out.println("Erro buscar cor "+ex.getMessage());
                    ex.printStackTrace();
                }
                oProduto.setCor((Cor) oCorDAO.carregar(rs.getInt("idcor")));
                
                MarcaDAO oMarcaDAO = null;
                try {
                    oMarcaDAO = new MarcaDAO();
                } catch (Exception ex) {
                    System.out.println("Erro buscar cor "+ex.getMessage());
                    ex.printStackTrace();
                }
                oProduto.setMarca((Marca) oMarcaDAO.carregar(rs.getInt("idmarca")));
                
                ComposicaoDAO oComposicaoDAO = null;
                try {
                    oComposicaoDAO = new ComposicaoDAO();
                } catch (Exception ex) {
                    System.out.println("Erro buscar cor "+ex.getMessage());
                    ex.printStackTrace();
                }
                oProduto.setComposicao((Composicao) oComposicaoDAO.carregar(rs.getInt("idcomposicao")));
                
                TipoPagamentoDAO oTipoPagamentoDAO = null;
                try {
                    oTipoPagamentoDAO = new TipoPagamentoDAO();
                } catch (Exception ex) {
                    System.out.println("Erro buscar cor "+ex.getMessage());
                    ex.printStackTrace();
                }
                oProduto.setTipoPagamento((TipoPagamento) oTipoPagamentoDAO.carregar(rs.getInt("idtipo")));
                resultado.add(oProduto);
            }
        }catch (SQLException ex) {
            System.out.println("Problemas ao Listar Produto! Erro:"
                    +ex.getMessage());
        }
        return resultado;
    }

}
