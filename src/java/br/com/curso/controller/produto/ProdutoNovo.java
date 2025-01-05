/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.curso.controller.produto;

import br.com.curso.dao.ComposicaoDAO;
import br.com.curso.dao.CorDAO;
import br.com.curso.dao.MarcaDAO;
import br.com.curso.dao.TipoPagamentoDAO;
import br.com.curso.model.Composicao;
import br.com.curso.model.Produto;
import br.com.curso.model.TipoPagamento;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USUARIO
 */
@WebServlet(name = "ProdutoNovo", urlPatterns = {"/ProdutoNovo"})
public class ProdutoNovo extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=iso-8859-1");
        Produto oProduto = new Produto();
        request.setAttribute("produto", oProduto);
        
        CorDAO oCorDAO = new CorDAO();
        request.setAttribute("cores", oCorDAO.listar());
        
        MarcaDAO oMarcaDAO = new MarcaDAO();
        request.setAttribute("marcas", oMarcaDAO.listar());
        
        ComposicaoDAO oComposicaoDAO = new ComposicaoDAO();
        request.setAttribute("composicoes", oComposicaoDAO.listar());
        
        TipoPagamentoDAO oTipoPagamentoDAO = new TipoPagamentoDAO();
        request.setAttribute("tipos", oTipoPagamentoDAO.listar());

        request.getRequestDispatcher("/cadastros/produto/produtoCadastrar.jsp").forward(request, response);
        
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ProdutoNovo.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ProdutoNovo.class.getName()).log(Level.SEVERE, null, ex);
        }
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
