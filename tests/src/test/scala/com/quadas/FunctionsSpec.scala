package com.quadas

import org.scalacheck._
import org.scalatest.prop.PropertyChecks
import org.scalatest.{FlatSpec, Matchers}

// Property check using Scalacheck
object FunctionsCheckUsingScalacheck extends Properties("Functions") {

  import Prop.forAll

  property("sgn") = forAll { (l: Long) =>
    val sgn = Functions.sgn(l)

    if (l < 0)
      sgn == -1
    else if (l > 0)
      sgn == 1
    else
      sgn == 0
  }

}

// Property check using Scalatest
class FunctionsCheckUsingScalatest extends FlatSpec with Matchers with PropertyChecks {

  "Function sgn" should "return correct values" in {
    forAll { (l: Long) =>
      val sgn = Functions.sgn(l)

      if (l < 0)
        sgn should be (-1)
      else if (l > 0)
        sgn should be (1)
      else
        sgn should be (0)
    }
  }

}

// Scalatest spec
class FunctionsSpec extends FlatSpec with Matchers {

  "Factorial implementations" should "produce same results" in {

    import Functions._

    // WARNING: correctly, this should be also tested using property checks. It would
    // reveal that the implementation is wrong as it incorrectly handles negative numbers.
    List(factRec(10), factRecInt(10), factMut(10), factFold(10)) should contain only BigInt("3628800")
    List(factRec(20), factRecInt(20), factMut(20), factFold(20)) should contain only BigInt("2432902008176640000")
    List(factRec(30), factRecInt(30), factMut(30), factFold(30)) should contain only BigInt("265252859812191058636308480000000")

  }

}
