package ScalaStaff
import java.util.Calendar

object Customer {
  val allCustomers = List[Customer]()

  def EnabledCustomer(customer: Customer): Boolean = customer.enabled == true

  def DisabledCustomer(customer: Customer): Boolean = customer.enabled == false

  def getDisabledCustomerNames(): List[String] = {
    Customer.allCustomers.filter(DisabledCustomer).map({ customer => customer.name })
  }

  def getEnabledCustomerStates(): List[String] = {
    Customer.allCustomers.filter(EnabledCustomer).map({ customer => customer.state })
  }

  def getEnabledCustomerDomains(): List[String] = {
    Customer.allCustomers.filter(EnabledCustomer).map({ customer => customer.domain })
  }

  def getEnabledCustomerSomeoneEmail(someone: String): List[String] = {
    Customer.allCustomers.filter(EnabledCustomer).map({ customer => someone + "@" + customer.domain })
  }

  def getCustomerById(inList: List[Customer], customer_id: Integer): List[Customer] = {
    inList.filter(customer => customer.customer_id == customer_id)
  }

  def eachEnabledContact(cls: Contact => Unit) {
    Customer.allCustomers.filter({ customer => customer.enabled && customer.contract.enabled }).foreach({
      customer => customer.contacts.foreach(cls)
    })
  }

  def updateCustomerByIdList(initialids: List[Customer], ids: List[Integer], cls: Customer => Customer): List[Customer] = {
    (initialids, ids) match{
      case (List(), _) => initialids
      case (_, List()) => initialids
      case (_, id ::tailIds) =>{
        initialids.find(cust => cust.customer_id == id) match {
          case None => updateCustomerByIdList(initialids, tailIds, cls)
          case Some(cust) => updateCustomerByIdList(
            initialids.filter( cust => cust.customer_id ==id),tailIds, cls
          )
        }
      }
    }
  }

  def updateContactForCustomerContact(customer_id: Integer,
                                      contact_id: Integer,
                                      cls: Contact => Contact): List[Customer] = {
    updateCustomerByIdList(Customer.allCustomers, List(customer_id), {
      customer =>
        new Customer(
          customer.customer_id,
          customer.name,
          customer.state,
          customer.domain,
          customer.enabled,
          customer.contract,
          customer.contacts.map { contact =>
            if (contact.contact_id == contact_id) {
              cls(contact)
            } else {
              contact
            }
          }
        )
    })
  }

  def updateContractForCustomerList(ids: List[Integer], cls: Contract => Contract):
  List[Customer] = {
    updateCustomerByIdList(Customer.allCustomers, ids, {
      customer =>
        new Customer(
          customer.customer_id,
          customer.name,
          customer.state,
          customer.domain,
          customer.enabled,
          cls(customer.contract),
          customer.contacts)
    })
  }

  def countEnabledCustomerWithEnabledContacts(customers: List[Customer], sum: Int): Integer = {
    customers match {
      case List() => sum
      case Customer(_, _, _, _, true, _, List()) :: custs =>
        countEnabledCustomerWithEnabledContacts(custs, sum)
      case Customer(_, _, _, _, true, _, cont) :: custs
        if cont.exists({contact => contact.enabled}) =>
         countEnabledCustomerWithEnabledContacts(custs, sum + 1)
      case cust :: custs => countEnabledCustomerWithEnabledContacts(custs, sum)
    }
  }

  def createCustomer(name : String, state : String, domain : String) : Option[Customer] ={
    def error(message : String ) : Option[Customer] = {
      println(message)
      None
    }
    (name, state, domain) match{
      case("", _, _) => error("name can't be empty")
      case(_, "", _) => error("state can't be empty")
      case(_, _, "") => error("domain can't be empty")
      case _ => new Some(new Customer(
        0,
        name,
        state,
        domain,
        true,
        new Contract(Calendar.getInstance, true),
        List()
      ))
    }
  }

}



case class Customer(val customer_id :Integer,
               val name : String,
               val state : String,
               val domain : String,
               val enabled : Boolean,
               val contract : Contract,
               val contacts: List[Contact]){}


// Somethink not work
case class CommandLineOption(description : String, func : () => Unit)

object  CommandLine {

  val options : Map[String, CommandLineOption] = Map(
    "1" -> new CommandLineOption("Add customer", Customer.createCustomer),
    "2" -> new CommandLineOption("Display customers", Customer.list),
    "3" -> new CommandLineOption("Display enabled contacts for enabled customers",
      () => Customer.eachEnabledContact(contact => println(contact))),
    "w" -> new CommandLineOption("Exit", sys.exit)
  )
  def askForInput(question : String) : String = {
    print(question + ": ")
    readLine()
  }
  def prompt() = {
    options.foreach(option => println(option._1 + ") "+option._2.description))
    options.get(askForInput("Option").trim.toLowerCase) match{
      case Some(CommandLineOption(_, exec)) => exec()
      case _ => println("Incorrect input")
    }
  }
}