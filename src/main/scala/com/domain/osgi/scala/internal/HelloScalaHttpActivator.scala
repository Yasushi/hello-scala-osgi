package com.domain.osgi.scala.internal

import org.osgi.framework._
import org.osgi.service.http._

import org.scalamodules.core.RichBundleContext.toRichBundleContext

import java.util.Hashtable

class HelloScalaHttpActivator extends BundleActivator {

	var httpServiceRef:ServiceReference = _
  
    def start(context: BundleContext) {
      	httpServiceRef = context.getServiceReference(classOf[HttpService].getName)
      	httpServiceRef match {
      	  case null => Console.println("HttpService reference not found.")
      	  case ref =>
      	    httpServiceRef = ref
      	    context.getService(httpServiceRef) match {
      	      case null => Console.println("HttpService not found.")
      	      case httpService:HttpService =>
      	      val httpContext = httpService.createDefaultHttpContext()
      	      val initParams = new Hashtable[String, String]()
      	      initParams.put("from", "HttpService")
      	      httpService.registerServlet("/helloworld/hs",
      	    		  new HelloWorldServlet("/helloworld/hs"),
      	    		  initParams,
      	    		  httpContext);
      	      httpService.registerServlet("/",
      	    		  new HelloWorldServlet("/"),
      	    		  initParams,
      	    		  httpContext);
      	      httpService.registerResources("/images","/images",httpContext)
      	    }
      	}
    	Console.println("STARTED com.domain.osgi.scala")
    }
   
    def stop(context: BundleContext) {
    	if (httpServiceRef != null)
    		context.ungetService(httpServiceRef)
    	Console.println("STOPPED com.domain.osgi.scala")
    }
}
