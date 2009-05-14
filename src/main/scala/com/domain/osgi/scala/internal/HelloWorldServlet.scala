package com.domain.osgi.scala.internal

import java.io.IOException
import java.io.PrintWriter
import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class HelloWorldServlet(registrationPath:String) extends HttpServlet {

  override def doGet(request:HttpServletRequest, response:HttpServletResponse) {
	  val body = <html>
	  <body align='center'>
	  <h1>Hello World</h1>
	  <img src='/images/logo.png' border='0' />
	  <h1>{getServletConfig().getInitParameter("from")}</h1>
	  <p>
	  Served by servlet registered at: {registrationPath} <br />
	  Servlet Path: {request.getServletPath()} <br />
	  Path Info: {request.getPathInfo()} <br />
	  </p>
	  </body></html>
	  response.setContentType("text/html")
      response.getWriter().println(body.mkString)
  }
}
