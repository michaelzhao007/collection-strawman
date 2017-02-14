package strawman.collection.immutable

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._

import scala.{Any, AnyRef, Int, Unit}

@BenchmarkMode(scala.Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
@Warmup(iterations = 12)
@Measurement(iterations = 12)
@State(Scope.Benchmark)
class NumericRangeBenchmark {

  @Param(scala.Array("8", "64", "512", "4096", "32768", "262144"/*, "2097152"*/))
  var size: Int = _

  var xs: NumericRange[Int] = _

  @Setup(Level.Trial)
  def initData(): Unit = {
    xs = NumericRange(0, size, 1)
  }

  @Benchmark
  def uncons(): Any = xs.tail

  @Benchmark
  def concat(): Any = xs ++ xs

  @Benchmark
  def foreach(): Any = {
    var n = 0
    xs.foreach(x => if (x == 0) n += 1)
    n
  }

  @Benchmark
  def lookup(): Any = xs(size - 1)

  @Benchmark
  def map(): Any = xs.map(x => if (x == 0) "foo" else "bar")

}
