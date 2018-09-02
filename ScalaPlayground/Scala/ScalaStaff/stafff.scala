package ScalaStaff

object staff {
  def main(args: Array[String]): Unit = {
    val x = for(i <-0 until 10) yield {
      i*2
    }
    println(x)
    val y = for(i <- List(1,2,3,4)) yield {
      i*2
    }
    println(y)
    val z = List(1,2,3,4,5).map({i => i*2})
    println(z)
  }
}
