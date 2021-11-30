package controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Contacto;
import modelo.ContactoCRUD;

/**
 *
 * @author DAW-A
 */
@WebServlet(urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {
    private final int NUM_LINEAS_PAGINA = 5;
    private int pagina = 1;
    private int offset = 0;
    private int num_paginas = 0;
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String op = request.getParameter("op");
            if(op.equals("listar")){
            List<Contacto> listaContacto = ContactoCRUD.getContactos();
            /*Calculos para la paginación*/

            if(request.getParameter("pagina") != null){
                pagina = Integer.parseInt(request.getParameter("pagina"));
                offset = (pagina-1) * NUM_LINEAS_PAGINA;
            }
            num_paginas = (int) Math.ceil(listaContacto.size() / (double) NUM_LINEAS_PAGINA);
            listaContacto = ContactoCRUD.getContactosPaginado(offset, NUM_LINEAS_PAGINA);
            request.setAttribute("listado", listaContacto);
            request.setAttribute("pagina", pagina);
            request.setAttribute("num_paginas", num_paginas);
            request.getRequestDispatcher("listar.jsp").forward(request, response);
            
            }else if(op.equals("borrar")){
            int id = Integer.parseInt(request.getParameter("id"));
            if(ContactoCRUD.destroyContacto(id) != 0){
                request.setAttribute("mensaje", "Contacto con id " + id + " borrado");
            }else{
                request.setAttribute("mensaje", "No se ha borrado ningún contacto");
            }
            List<Contacto> listaContacto = ContactoCRUD.getContactos();
            request.setAttribute("listado", listaContacto);
            if(request.getParameter("pagina") != null){
                pagina = Integer.parseInt(request.getParameter("pagina"));
                offset = (pagina-1) * NUM_LINEAS_PAGINA;
            }
            num_paginas = (int) Math.ceil(listaContacto.size() / (double) NUM_LINEAS_PAGINA);
            listaContacto = ContactoCRUD.getContactosPaginado(offset, NUM_LINEAS_PAGINA);
            request.setAttribute("listado", listaContacto);
            request.setAttribute("pagina", pagina);
            request.setAttribute("num_paginas", num_paginas);
            request.getRequestDispatcher("listar.jsp").forward(request, response);
            }else if(op.equals("actualizar")){
            int id = Integer.parseInt(request.getParameter("id"));
            Contacto miContacto = ContactoCRUD.getContacto(id);
            request.setAttribute("contacto", miContacto);
            request.setAttribute("operacion", "actualizarDatos");
            request.getRequestDispatcher("actualizar.jsp").forward(request, response);
            }else if(op.equals("actualizarDatos")){
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String telefono = request.getParameter("telefono");
            Contacto miContacto = new Contacto(id, nombre, telefono);
            if(ContactoCRUD.actualizaContacto(miContacto) != 0){
                request.setAttribute("mensaje", "Contacto con id " + id + " actualizado");
            }else{
                request.setAttribute("mensaje", "No se ha actualizado ningún contacto");
            }
            List<Contacto> listaContactos = ContactoCRUD.getContactos();
            request.setAttribute("listado", listaContactos);
            if(request.getParameter("pagina") != null){
                pagina = Integer.parseInt(request.getParameter("pagina"));
                offset = (pagina-1) * NUM_LINEAS_PAGINA;
            }
            num_paginas = (int) Math.ceil(listaContactos.size() / (double) NUM_LINEAS_PAGINA);
            listaContactos = ContactoCRUD.getContactosPaginado(offset, NUM_LINEAS_PAGINA);
            request.setAttribute("listado", listaContactos);
            request.setAttribute("pagina", pagina);
            request.setAttribute("num_paginas", num_paginas);
            request.getRequestDispatcher("listar.jsp").forward(request, response);
            }else if(op.equals("insertar")){
            request.setAttribute("operacion", "insertarDatos");
            request.setAttribute("mensaje", "");
            request.getRequestDispatcher("actualizar.jsp").forward(request, response);
            }else if(op.equals("insertarDatos")){
            String nombre = request.getParameter("nombre");
            String telefono = request.getParameter("telefono");
            Contacto miContacto = new Contacto(nombre, telefono);
            ContactoCRUD.insertaContacto(miContacto);
            List<Contacto> listaProductos = ContactoCRUD.getContactos();
            request.setAttribute("listado", listaProductos);
            if(request.getParameter("pagina") != null){
                pagina = Integer.parseInt(request.getParameter("pagina"));
                offset = (pagina-1) * NUM_LINEAS_PAGINA;
            }
            num_paginas = (int) Math.ceil(listaProductos.size() / (double) NUM_LINEAS_PAGINA);
            listaProductos = ContactoCRUD.getContactosPaginado(offset, NUM_LINEAS_PAGINA);
            request.setAttribute("listado", listaProductos);
            request.setAttribute("pagina", pagina);
            request.setAttribute("num_paginas", num_paginas);
            request.getRequestDispatcher("listar.jsp").forward(request, response);
            request.getRequestDispatcher("listar.jsp").forward(request, response);
            }
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
