package com.example.progettolaboratorio;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.Reader;
import DAO.*;

import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ScriptException, NoSuchMethodException {
        message=request.getParameter("message");
        PrintWriter out=response.getWriter();
        response.setContentType("text/plain");
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        // read script file
        engine.eval(Files.newBufferedReader(Paths.get("C:/Scripts/Jsfunctions.js"), StandardCharsets.UTF_8));
        Invocable inv = (Invocable) engine;
        // call function from script file
        inv.invokeFunction("Error","message");
        //Da modificare in seguito
        // out.println("<script>");
        // out.println("<Alert("+message+")>");
        // out.println("</script>");
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
