package com.domain.osgi.scala.internal

import org.osgi.framework._
import com.domain.osgi.scala.HelloScalaService

import org.scalamodules.core.RichBundleContext.toRichBundleContext

class HelloScalaActivator extends BundleActivator {
  var serviceRegistration:ServiceRegistration = _

  def start(context: BundleContext) {
    Console.println("STARTING com.domain.osgi.scala")
    serviceRegistration = context registerAs classOf[HelloScalaService] theService new HelloScalaServiceImpl()
    Console.println("REGISTERED com.domain.osgi.scala.internal.HelloScalaService")
  }

  def stop(context: BundleContext) {
    Console.println("STOPPED com.domain.osgi.scala")
    if (serviceRegistration != null)
      serviceRegistration.unregister
    Console.println("UNREGISTERED com.domain.osgi.scala.internal.HelloScalaService")
  }
}
