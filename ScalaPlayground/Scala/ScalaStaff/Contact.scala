package ScalaStaff

object Contact{
  def setNameEndEmailForContactAndCustomer(
                                            customer_id : Integer,
                                            contact_id : Integer,
                                            name : String,
                                            email: String
                                          ) : List[Customer] ={
    Customer.updateContactForCustomerContact(
      customer_id,
      contact_id,
      {contact => new contact(
        contact.contact_id,
        contact.firstName,
        name,
        email,
        contact.enabled
      )}
    )
  }

}
class Contact(val contact_id : Integer,
              val firstName : String,
              val lastName : String,
              val email : String,
              val enabled : Boolean){
  def sendEmail(): Unit ={
    print("Email was send")
  }
}
