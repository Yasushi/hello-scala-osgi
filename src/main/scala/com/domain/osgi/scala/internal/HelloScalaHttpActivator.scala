package com.domain.osgi.scala.internal

import org.osgi.framework._
import org.osgi.service.http._

import org.scalamodules.core.RichBundleContext.toRichBundleContext
import org.scalamodules.core._

import java.util.Hashtable

class HelloScalaHttpActivator extends BundleActivator {

    var httpServiceRef:ServiceReference = _
    var tracker:Track[HttpService] = _

    private def registerServlets(httpService:HttpService) {
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
      Console.println("REGISTERED servlets")
    }
 
    def start(context: BundleContext) {
        tracker = context track classOf[HttpService] on {
          case Adding(service, _) => registerServlets(service)
          case Modified(service, _) =>
          case Removed(service, _) =>
        }
        Console.println("STARTED com.domain.osgi.scala")
    }
   
    def stop(context: BundleContext) {
        tracker.stop()
        Console.println("STOPPED com.domain.osgi.scala")
    }
}
