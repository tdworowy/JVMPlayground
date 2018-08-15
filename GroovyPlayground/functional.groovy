
import java.util.ArrayList
import java.util.List
import java.util.Calendar


public class Contract {
    public Contract setBegin_date(Calendar begin_date) {
        this.begin_date = begin_date
        return this
    }

    public Contract setEnd_date(Calendar end_date) {
        this.end_date = end_date
        return this
    }

    public Contract setEnabled(Boolean enabled) {
        this.enabled = enabled
        return this
    }

    public Calendar begin_date
    public Calendar end_date
    public Boolean enabled = true


    public Contract(Calendar begin_date){
        this.begin_date = begin_date
        this.end_date = Calendar.getInstance()
        this.end_date.setTimeInMillis(this.begin_date.getTimeInMillis())
        this.end_date.add(Calendar.YEAR,2)
    }
    public static List<Object> setContractForCustomer(Integer customer_id, final Boolean status){
        map(Customer.getCustomerById(Customer.allCustomers, customer_id), {customer -> customer.contract.enabled = status})
    }

    public static void display(Integer customer_id){
        foreach( setContractForCustomer(customer_id, true),{contract -> print(contract)})

    }

}


 class Customer {
     static ArrayList<Customer> allCustomers = new ArrayList<>()
    public int id = 0
    private  name
    private  address
    private  state

     Customer setId(id) {
        this.id = id
        return this

     }

     Customer setName(name) {
        this.name = name
        return this
    }

     Customer setAddress(address) {
        this.address = address
        return this
    }

     Customer setState(state) {
        this.state = state
        return this
    }

     Customer setPrimaryContact(primaryContact) {
        this.primaryContact = primaryContact
        return this
    }

     Customer setDomain(domain) {
        this.domain = domain
        return this
    }

     Customer setEnabled(enabled) {
        this.enabled = enabled
        return this
    }

     Customer setContract(contract) {
        this.contract = contract
        return this
    }

     String primaryContact
     String domain
     Boolean enabled = true
     Contract contract



     Customer(String name, String address, String state, String primaryContact , String domain){
        this.id +=1
        this.name = name
        this.address = address
        this.state = state
        this.primaryContact = primaryContact
        this.domain = domain
    }


    static def Closure<Boolean> EnabledCustomer ={ customer -> customer.enabled  == true}
    static def Closure<Boolean> DisabledCustomer ={ customer -> customer.enabled  == false}
     Customer(){}

     static def getEnabledCustomerAddresses(){
        Customer.allCustomers.findAll(EnabledCustomer).collect({customer ->customer.address})
    }
     static List<String> getEnabledCustomerNames(){
         Customer.allCustomers.findAll(EnabledCustomer).collect({customer -> customer.name})
    }
     static List<String> getEnabledCustomerStates(){
         Customer.allCustomers.findAll(EnabledCustomer).collect({customer -> customer.state})
    }
     static List<String> getEnabledCustomerPrimaryContacts(){
         Customer.allCustomers.findAll(EnabledCustomer).collect({customer -> customer.primaryContact})
    }
     static List<String> getEnabledCustomerDomains(){
         Customer.allCustomers.findAll(EnabledCustomer).collect({customer -> customer.domain})
    }


     static List<String> getEnabledCustomersBossesEmail() {
         Customer.allCustomers.findAll(EnabledCustomer).collect({customer -> "boss@"+customer.domain})
    }
     static List<String> getEnabledCustomersSomeoneEmail(final  String someone) {
         Customer.allCustomers.findAll(EnabledCustomer).collect({customer -> someone+"@"+customer.domain})
    }


     static List<String> getDisabledCustomerAddresses(){
         Customer.allCustomers.findAll(DisabledCustomer).collect({customer -> customer.address})
    }
     static List<String> getDisabledCustomerNames(){
         Customer.allCustomers.findAll(DisabledCustomer).collect({customer -> customer.name})
    }
     static List<String> getDisabledCustomerStates(){
        Customer.allCustomers.findAll(DisabledCustomer).collect({customer -> customer.state})
    }
     static List<String> getDisabledCustomerPrimaryContacts(){
         Customer.allCustomers.findAll(DisabledCustomer).collect({customer -> customer.primaryContact})
    }
     static List<String> getDisabledCustomerDomains(){
        Customer.allCustomers.findAll(DisabledCustomer).collect({customer -> customer.domain})
    }


     static List<String> getDisabledCustomersBossesEmail() {
        Customer.allCustomers.findAll(DisabledCustomer).collect({customer -> "boss@"+customer.domain})
    }
     static List<String> getDisabledCustomersSomeoneEmail(final  String someone) {
         Customer.allCustomers.findAll(DisabledCustomer).collect({customer -> someone+"@"+customer.domain})
    }


     static List<Customer> getCustomerById(List<Customer> initList,final Integer customer_id){
        initList.findAll({customer -> customer.id == customer_id})
    }

 }
        Customer customer1= new Customer("Rick1","add1","state",null,"domain")
        Customer customer2= new Customer("Rick2","add2","state",null,"domain")
        Customer.allCustomers.add(customer1)
        Customer.allCustomers.add(customer2)

        println(Customer.getEnabledCustomerAddresses())
        println(Customer.getEnabledCustomerNames())
        println(Customer.getEnabledCustomerStates())
        println(Customer.getEnabledCustomerPrimaryContacts())
        println(Customer.getEnabledCustomerDomains())








