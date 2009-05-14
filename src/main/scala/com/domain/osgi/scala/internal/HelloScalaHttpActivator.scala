package com.domain.osgi.scala.internal

import org.osgi.framework._
import org.osgi.service.http._

import org.scalamodules.core.RichBundleContext.toRichBundleContext

import java.util.Hashtable

class HelloScalaHttpActivator extends BundleActivator {

  
    def start(context: BundleContext) {
    	context getOne classOf[HttpService] andApply { httpService =>
    	val httpContext = httpService.createDefaultHttpContext()
    	val initParams = new Hashtable[String, String]()
    	initParams.put("from", "HttpService")
    	httpService.registerServlet("/helloworld/hs",
    			new HelloWorldServlet("/helloworld/hs"),
    			initParams,
    			httpContext)
    	httpService.registerServlet("/",
    			new HelloWorldServlet("/"),
    			initParams,
    			httpContext)
    	httpService.registerResources("/images","/images",httpContext)
    	Console.println("STARTING")
    	}
    	Console.println("STARTED com.domain.osgi.scala")
    }
   
    def stop(context: BundleContext) {
    	Console.println("STOPPED com.domain.osgi.scala")
    }
}
