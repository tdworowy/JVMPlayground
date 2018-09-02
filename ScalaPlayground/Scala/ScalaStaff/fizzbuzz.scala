//import collection.mutable.Stack
//import org.scalatest

package ScalaStaff



object FizzBuzz1{

  def getResult(number: Int): String ={
    var result = ""
    if (number % 3 == 0) result = "fizz"
    if (number % 5 == 0) result += "buzz"
    if (result == "") number.toString
    else result
  }
}

object FizzBuzz2{
  def getResult (number : Int) : String = (number % 3 , number % 5) match{
    case (0,0) => "fizzbuzz"
    case (0,_) => "fizz"
    case (0,_) => "buzz"
    case _ => number.toString
  }
}

//Class FizzBuzzTest extends  {
//
//  "FizzBuzz" should "return fizz if the number is dividable by 3" in {
//    FizzBuzz1.getResult(3) should be ("fizz")
//    FizzBuzz1.getResult(6) should be ("fizz")
//  }
//
//  it should "return buzz if the number is dividable by 5" in {
//    FizzBuzz1.getResult(5) should be ("buzz")
//    FizzBuzz1.getResult(10) should be ("buzz")
//  }
//
//  it should "return fizzbuzz if the number is dividable by 15" in {
//    FizzBuzz1.getResult(15) should be ("fizzbuzz")
//    FizzBuzz1.getResult(30) should be ("fizzbuzz")
//  }
//
//  it should "return the same number if no other requirement is fulfilled" in {
//    FizzBuzz1.getResult(1) should be ("1")
//    FizzBuzz1.getResult(2) should be ("2")
//    FizzBuzz1.getResult(4) should be ("4")
//  }
//  "FizzBuzz" should "return fizz if the number is dividable by 3" in {
//    FizzBuzz2.getResult(3) should be ("fizz")
//    FizzBuzz2.getResult(6) should be ("fizz")
//  }
//
//  it should "return buzz if the number is dividable by 5" in {
//    FizzBuzz2.getResult(5) should be ("buzz")
//    FizzBuzz2.getResult(10) should be ("buzz")
//  }
//
//  it should "return fizzbuzz if the number is dividable by 15" in {
//    FizzBuzz2.getResult(15) should be ("fizzbuzz")
//    FizzBuzz2.getResult(30) should be ("fizzbuzz")
//  }
//
//  it should "return the same number if no other requirement is fulfilled" in {
//    FizzBuzz2.getResult(1) should be ("1")
//    FizzBuzz2.getResult(2) should be ("2")
//    FizzBuzz2.getResult(4) should be ("4")
//  }
//
//}

