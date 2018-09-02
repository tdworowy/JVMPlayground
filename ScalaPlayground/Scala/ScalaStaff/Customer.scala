package ScalaStaff

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
    if (ids.size <= 0) {
      initialids
    } else if (initialids.size <= 0) {
      List()
    } else {
      val precust = initialids.find(cust => cust.customer_id == ids(0))
      val cust = if (precust.isEmpty) {
        List()
      } else {
        List(cls(precust.get))
      }
      cust ::: updateCustomerByIdList(
        initialids.filter(cust => cust.customer_id == ids(0)),
        ids.drop(1),
        cls
      )
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
    if (customers.isEmpty) {
      sum
    } else {
      val addition = if (customers.head.enabled && customers.head.contacts.exists({ contact => contact.enabled })) {
        1
      } else {
        0
      }

      countEnabledCustomerWithEnabledContacts(customers.tail, addition + sum)
    }
  }
}

class Customer(val customer_id :Integer,
               val name : String,
               val state : String,
               val domain : String,
               val enabled : Boolean,
               val contract : Contract,
               val contacts: List[Contact]){}

