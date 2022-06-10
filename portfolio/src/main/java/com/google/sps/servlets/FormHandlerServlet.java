package com.google.sps.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {
    ArrayList<String> texts = new ArrayList<>();

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Get the value entered in the form.
    String textValue = request.getParameter("text-input");

    // Print the value so you can see it in the server logs.
    System.out.println("You submitted: " + textValue);

    // Write the value to the response so the user can see it.
    response.getWriter().println("You submitted: " + textValue);
    response.sendRedirect("https://ysun-sps-summer22.appspot.com/");
  }

  public ArrayList<String> doGet() {

    // Add words to texts
    texts.add("Moi");
    texts.add("Hey Hey");
    texts.add("Moi Moi");

    return texts;
  }
}