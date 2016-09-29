package com.quadas

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._

@State(Scope.Thread)
@Warmup(iterations = 10, batchSize = 1500)
@Measurement(iterations = 10, batchSize = 1500)
@Fork(5)
@BenchmarkMode(Array(Mode.SingleShotTime))
@OutputTimeUnit(TimeUnit.MILLISECONDS)
class FactorialBench {

  import Functions._

  // http://hg.openjdk.java.net/code-tools/jmh/file/tip/jmh-samples/src/main/java/org/openjdk/jmh/samples/JMHSample_26_BatchSize.java
  // http://shipilev.net/blog/2014/nanotrusting-nanotime/#_steady_state_considerations

  var n: Int = 0

  @Benchmark
  def factorialRecursive: BigInt = {
    n = n + 1
    factRec(n)
  }

  @Benchmark
  def factorialRecursiveInt: BigInt = {
    n = n + 1
    factRecInt(n)
  }

  @Benchmark
  def factorialFold: BigInt = {
    n = n + 1
    factFold(n)
  }

  @Benchmark
  def factorialMutableState: BigInt = {
    n = n + 1
    factMut(n)
  }

  @Setup(Level.Iteration)
  def setup() = {
    n = 0
  }

}
