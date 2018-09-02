package ScalaStaff

class Customer(val id : Integer, val enabled : Boolean, val contracts : List[Double]){
  lazy val revenue : Double = CalculateRevenue(this.contracts)

  def CalculateRevenue(contracts: List[Double]) : Double = {
    var sum : Double = 0.0
    for(contract <- contracts){
      sum += contract
    }
    sum
  }
}
class CustomerContainer(val customers : List[Customer] = List()){
  val onlyEnabled = customers.filter{customer => customer.enabled}
  def addCustomer(c : Customer) : CustomerContainer = {
    new CustomerContainer(customers ::: List(c))
  }
  def removeCustomer(c: Customer) : CustomerContainer = {
    new CustomerContainer(customers.filter{customer => customer.id !=c.id})
  }
}
object example {
  def main(args: Array[String]): Unit = {
    var cc = new CustomerContainer()
    cc = cc.addCustomer(new Customer(1, true, List(100.0, 200.0, 300.0)))
    cc = cc.addCustomer(new Customer(2, false, List(150.0, 300.0, 400.0)))

    println(cc.customers)
    var sum: Double = 0.0
    for (customer <- cc.onlyEnabled) {
      sum += customer.revenue
    }
    println(s"revenue of active customers: ${sum}")
  }
}