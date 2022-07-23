package com.example.progettolaboratorio;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import DAO.*;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet(name = "ServletError", value = "/ServletError") //questa è la value che verrà usata nell'ancora del HTML

public class ServletError extends HttpServlet {
private String message;
public void init() {
        //registrazione del driver
        DAO.registerDriver();
        }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ScriptException, NoSuchMethodException {
        try(PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
            Gson gson = new Gson();
            //continua qui
        }
        /* VECCHIO SCRIPT QUA SOTTO... DA COMPLETARE
        message=request.getParameter("message");
        PrintWriter out=response.getWriter();
        response.setContentType("text/plain");
        System.out.println("1");
        System.out.println(message);

         */

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request,response);
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request,response);
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
