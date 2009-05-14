package com.domain.osgi.scala.internal

import com.domain.osgi.scala._
class HelloScalaServiceImpl extends HelloScalaService {
  def hello() = {
    Console.print("Hello, from Scala")
  }
}
