package com.example.progettolaboratorio;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import DAO.*;

import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static DAO.DAO.loginUtente;
import static java.lang.System.out;

@WebServlet(name = "ServletError", value = "/ServletError") //questa è la value che verrà usata nell'ancora del HTML

public class ServletError extends HttpServlet {
private String message;
public void init() {
        //registrazione del driver
        DAO.registerDriver();
        }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out.println("Sono nell errore0");
        message=request.getParameter("message");
        out.println("Sono nell errore1");
        PrintWriter out=response.getWriter();
        System.out.println("Sono nell errore2");
        response.setContentType("text/plain");
        System.out.println("Sono nell errore3");
        //Da modificare in seguito
        System.out.println("Sono nell errore4");
        // out.println("<script>");
        // out.println("<Alert("+message+")>");
        // out.println("</script>");
        String massage = "errore";
        out.println("<html><script><a href=\"file:///C:\\Users\\matti\\OneDrive\\Desktop\\Uni\\Terzo_anno\\Ium Tweb 2\\Progetto\\EsercizioVue\\src\\main\\webapp\\WEB-INF\\alertNonRegistrato.js\">Link 1</a></script><head>");
         //
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
}
