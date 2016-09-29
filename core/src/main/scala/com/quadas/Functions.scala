package com.quadas

import com.github.ghik.silencer.silent

import scala.annotation.tailrec

/**
  * Collection of important functions.
  */
trait Functions {

  /**
    * Calculates signum of a given number.
    *
    * @param n number
    * @return -1 if number is less than zero, 1 if number is greater than zero, 0 if number is zero
    */
  def sgn[T](n: T)(implicit N: Numeric[T]): Int = N.signum(n)

  /**
    * Implementation of factorial function using (tail) recursion.
    *
    * @param n integral value
    * @return factorial as `BigInt`
    */
  def factRec[T](n: T)(implicit I: Integral[T]): BigInt = {
    import I._

    @tailrec
    def factRec0(n: T, agg: BigInt): BigInt = {
      if (equiv(n, zero))
        agg
      else
        factRec0(minus(n, one), agg * toLong(n))
    }

    factRec0(n, BigInt(1))

  }

  /**
    * Function specialized for Int values.
    * @param n Int value
    * @return factorial as `BigInt`
    */
  def factRecInt(n: Int): BigInt = {
    def factRec0(n: Int, agg: BigInt): BigInt = {
      if (n == 0)
        agg
      else
        factRec0(n - 1, agg * BigInt(n))
    }

    factRec0(n, BigInt(1))
  }

  /**
    * Implementation of factorial function using folding.
    *
    * @param n integral value
    * @return factorial as `BigInt`
    */
  def factFold[T](n: T)(implicit I: Integral[T]): BigInt = {
    import I._

    (BigInt(1) to BigInt(toLong(n))).product
  }

  /**
    * Implementation of factorial function using mutable state.
    *
    * @param n integral value
    * @return factorial as `BigInt`
    */
  def factMut[T](n: T)(implicit I: Integral[T]): BigInt = {
    import I._

    /*
     Compiler would throw an error here because the following line emits two warnings:
     – unused value
     – type argument infers `Any` (type of badugly would be `List[Any]`)

     In a rare case this is intended and alright, one can use annotation `@silent` to suppress warning.
     And example of a justified case is deprecated function in dependency that cannot be rewritten.
    */
    @silent val badugly = List("hello", 100L, true)

    var agg = BigInt(1)

    for (i <- 1L to toLong(n)) {
      agg = agg * i
    }

    agg

  }

}

object Functions extends Functions

object Main {

  def main(args: Array[String]): Unit = {
    println("Hello, mind having some implementation of factorial?")
  }

}
