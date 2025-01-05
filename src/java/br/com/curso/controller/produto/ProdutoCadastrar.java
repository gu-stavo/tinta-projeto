/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.curso.controller.produto;

import br.com.curso.dao.GenericDAO;
import br.com.curso.dao.ProdutoDAO;
import br.com.curso.model.Composicao;
import br.com.curso.model.Cor;
import br.com.curso.model.Marca;
import br.com.curso.model.Produto;
import br.com.curso.model.TipoPagamento;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USUARIO
 */
@WebServlet(name = "ProdutoCadastrar", urlPatterns = {"/ProdutoCadastrar"})
public class ProdutoCadastrar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        int idProduto = Integer.parseInt(request.getParameter("idproduto"));
        int idCor = Integer.parseInt(request.getParameter("idcor"));
        int idMarca = Integer.parseInt(request.getParameter("idmarca"));
        int idComposicao = Integer.parseInt(request.getParameter("idcomposicao"));
        int idTipo = Integer.parseInt(request.getParameter("idtipo"));
        String nomeProduto = request.getParameter("nomeproduto");
        String situacao = request.getParameter("situacao");
        Double preco = Double.parseDouble(request.getParameter("preco"));
        String mensagem = null;
        
        try{
            Produto oProduto = new Produto();
            oProduto.setIdProduto(idProduto);
            oProduto.setNomeProduto(nomeProduto);
            oProduto.setSituacao(situacao);
            oProduto.setPreco(preco);
            oProduto.setCor(new Cor(idCor,""));
            oProduto.setMarca(new Marca(idMarca,""));
            oProduto.setComposicao(new Composicao(idComposicao,""));
            oProduto.setTipoPagamento(new TipoPagamento(idTipo,""));
            
            GenericDAO dao = new ProdutoDAO();
            if (dao.cadastrar(oProduto)){
                mensagem = "Cidade cadastrado com sucesso!";
            } else {
                mensagem = "Problemas ao cadastrar Produto. Verifique os dados informados e tente novamente!";
            }
            request.setAttribute("mensagem", mensagem);
            response.sendRedirect("ProdutoListar");
        }catch (Exception ex){
            System.out.println("Problemas no Servlet ao cadastrar Produto! Erro: " +ex.getMessage());
            ex.printStackTrace();
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
