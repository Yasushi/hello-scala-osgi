package com.domain.osgi.scala.internal

import org.osgi.framework._
import org.osgi.service.http._
import com.domain.osgi.scala.HelloScalaService

class HelloScalaActivator extends BundleActivator {
  var serviceRegistration:ServiceRegistration = _
  var httpService:HttpService = _

  def start(context: BundleContext) {
    Console.println("STARTING com.domain.osgi.scala")
    serviceRegistration = context.registerService("com.domain.osgi.scala.HelloScalaService",
    		new HelloScalaServiceImpl(), null)
    Console.println("REGISTERED com.domain.osgi.scala.internal.HelloScalaService")
  }

  def stop(context: BundleContext) {
    Console.println("STOPPED com.domain.osgi.scala")
    if (serviceRegistration != null)
      serviceRegistration.unregister
    Console.println("UNREGISTERED com.domain.osgi.scala.internal.HelloScalaService")
  }
}
