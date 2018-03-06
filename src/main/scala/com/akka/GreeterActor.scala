//#full-example

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}

//#greeter-companion
//#greeter-messages
object GreeterActor {
  //#greeter-messages 返回一个 actor
  def of(message: String, printerActor: ActorRef): Props = Props(new GreeterActor(message, printerActor))

  //#greeter-messages
  final case class WhoToGreet(who: String)

  case object Greet

}

//#greeter-messages
//#greeter-companion

//#greeter-actor
class GreeterActor(message: String, printerActor: ActorRef) extends Actor {

  var greeting = ""

  override def receive = {
    case GreeterActor.WhoToGreet(who) =>
      greeting = s"$message, $who"
    case GreeterActor.Greet =>
      //#greeter-send-message
      printerActor ! PrinterActor.Greeting(greeting)
    //#greeter-send-message
  }
}

//#greeter-actor

//#printer-companion
//#printer-messages
object PrinterActor {
  //#printer-messages
  def of: Props = Props[PrinterActor]

  //#printer-messages
  final case class Greeting(greeting: String)

}

//#printer-messages
//#printer-companion

//#printer-actor
class PrinterActor extends Actor with ActorLogging {
  var count: Int = 0

  import PrinterActor._

  def receive = {
    case Greeting(greeting) =>
      count = count + 1
      log.info(s"Greeting received (from ${sender()}): $greeting :$count")
  }
}

//#printer-actor

//#main-class
object AkkaQuickstart extends App {

  import GreeterActor._

  // Create the 'helloAkka' actor system
  val system: ActorSystem = ActorSystem("helloAkka")

  //#create-actors
  // Create the printer actor
  val printerActor: ActorRef = system.actorOf(PrinterActor.of, "printerActor")

  // Create the 'greeter' actors
  val howdyGreeter: ActorRef = system.actorOf(GreeterActor.of("Howdy", printerActor), "howdyActor")
  val helloGreeter: ActorRef = system.actorOf(GreeterActor.of("Hello", printerActor), "helloActor")
  val goodDayGreeter: ActorRef = system.actorOf(GreeterActor.of("Good day", printerActor), "goodDayActor")
  //#create-actors

  //#main-send-messages
  howdyGreeter ! WhoToGreet("Akka")
  howdyGreeter ! Greet

  howdyGreeter ! WhoToGreet("Lightbend")
  howdyGreeter ! Greet

  helloGreeter ! WhoToGreet("Scala")
  helloGreeter ! Greet

  goodDayGreeter ! WhoToGreet("Play")
  goodDayGreeter ! Greet
  //#main-send-messages
}

//#main-class
//#full-example
