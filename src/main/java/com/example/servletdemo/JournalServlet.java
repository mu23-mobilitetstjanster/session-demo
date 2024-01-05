package com.example.servletdemo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/journal")
public class JournalServlet extends HttpServlet {

  //private List<String> entries = new ArrayList<>();

  private Map<String, List<String>> entries = new HashMap<>();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession(true);
    String username = (String) session.getAttribute("username");

    if(username == null) {
      resp.sendRedirect("/login.jsp");
    } else {
      req.getRequestDispatcher("/index.jsp").include(req, resp);

      List<String> userEntries = entries.get(username);

      if(userEntries != null) {

        PrintWriter out = resp.getWriter();
        for(String entry : userEntries) {
          out.println(entry);
        }
      }
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession(true);
    String username = (String) session.getAttribute("username");

    if(username == null) {
      resp.sendRedirect("/login.jsp");
    } else {
      String title = req.getParameter("title");
      String content = req.getParameter("content");

      String entry = "Title: " + title + "<br>Content: " + content + "<br>";

      if(entries.get(username) == null) {
        entries.put(username, new ArrayList<>());
      }

      entries.get(username).add(entry);

      resp.sendRedirect(req.getServletPath());
    }
  }
}
