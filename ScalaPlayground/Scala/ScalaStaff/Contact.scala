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
      {contact => new Contact(
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

def sendEmail() = {
  new Email(this.email, "Subject", "Body", true, this.firstName).send()
}
}


case class Email(val address : String,
                 val subject : String,
                 val body : String,
                 val idDearReader : Boolean,
                 val name : String){
  def send() : Boolean = Email.send(this)
}

object Email{
  def send(address : String, subject : String, body : String) : Boolean ={
    println("To: " + address + "\nSubject: "+ subject+"\nContent: "+body)
    true
  }
  def send(msg : Email) : Boolean = {
    msg match {
      case Email(address, subject, body, true, name) =>
        send(address, subject, "Welcome, "+name + "\n"+ body)
      case Email(address, subject, body, _, _) =>
        send(address, subject, body)
    }
    true
  }
}