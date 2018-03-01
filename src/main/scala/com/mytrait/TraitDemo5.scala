package com.mytrait

/**
  * @author zhangxin
  *         Created on 18/3/1.
  */
object TraitDemo5 {
  def main(args: Array[String]) {
    val realBeyoncé = new VerifiedTweeter("Beyoncé")
    realBeyoncé.tweet("Just spilled my glass of lemonade")
  }
}

trait User {
  def username: String
}

trait Tweeter {
  this: User =>
  // reassign this
  def tweet(tweetText: String) = println(s"$username: $tweetText")
}

class VerifiedTweeter(val username_ : String) extends Tweeter with User {
  // We mixin User because Tweeter required it
  def username = s"real $username_"
}
