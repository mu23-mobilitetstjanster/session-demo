package com.example.servletdemo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet("/time/date/*")
public class DateServlet extends HttpServlet {

  @Override
  protected void doGet(
          HttpServletRequest req,
          HttpServletResponse resp
  ) throws ServletException, IOException {

    PrintWriter out = resp.getWriter();

    out.print(new Date());

  }
}
