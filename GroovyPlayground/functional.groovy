 class Contract {

    final Calendar begin_date
    final Calendar end_date
    final Boolean enabled

     Contract(Calendar begin_date, enabled){
        this.begin_date = begin_date
        this.end_date = Calendar.getInstance()
        this.end_date.setTimeInMillis(this.begin_date.getTimeInMillis())
        this.end_date.add(Calendar.YEAR,2)
        this.enabled = enabled
    }
    static List<Object> setContractForCustomer(List<Integer> ids, final Boolean status){
        Customer.updateContractForCustomerList(ids, {contract ->
            new Contract(contract.begin_date,status)
        })
    }


    static void display(List<Integer> ids){
        foreach( setContractForCustomer(ids, true),{contract -> print(contract)})

    }
}

 class Customer {
     static List<Customer> allCustomers = new ArrayList<>()
     final int id
     final name
     final address
     final state
     final String  primaryContact
     final String  domain
     final Boolean  enabled
     final Contract  contract

    static Closure<Boolean> EnabledCustomer ={ customer -> customer.enabled  == true}
    static Closure<Boolean> DisabledCustomer ={ customer -> customer.enabled  == false}

     Customer(int id,name, address, state,  primaryContact,  domain, Contract contract, enabled){
         this.name = name
         this.address = address
         this.state = state
         this.primaryContact = primaryContact
         this.domain = domain
         this.contract = contract
         this.id = id
         this.enabled = enabled
     }


     static List<Customer> updateContractForCustomerList(List<Integer>ids, cls){
         Customer.allCustomers.collect{ customer ->
             if(ids.indexOf(customer.id) >=0){
                 new Customer(
                         customer.id,
                         customer.name,
                         customer.address,
                         customer.state,
                         customer.primaryContact,
                         customer.domain,
                         cls(customer.contract),
                         customer.enabled)
             }else {
                 customer
             }
         }
     }
    //tail recurenction
     static int countEnabledCustomersWithNotEnabledContracts(List<Customer> customers, int sum){
         if (customers.isEmpty()){
             return sum
         }
         else {
             int addition =(customers.head().enabled && (! customers.head().contract.enabled)) ? 1:0
             return addition + countEnabledCustomersWithNotEnabledContracts(customers.tail(), sum+addition)
         }

     }

     static List<String> getEnabledCustomerAddresses(){
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

     static  eachEnableContact(cls){
         Customer.allCustomers.findAll{customer ->customer.enabled && customer.contract.enabled}.
                 each {customer -> customer.contract.
                         each {cls}}
     }

 }
        Customer customer1= new Customer(1,"Rick1", "add1", "state", null, "domain", null, null)
        Customer customer2= new Customer(1,"Rick2", "add2", "state", null, "domain", null, null)
        Customer.allCustomers.add(customer1)
        Customer.allCustomers.add(customer2)

        println(Customer.getEnabledCustomerAddresses())
        println(Customer.getEnabledCustomerNames())
        println(Customer.getEnabledCustomerStates())
        println(Customer.getEnabledCustomerPrimaryContacts())
        println(Customer.getEnabledCustomerDomains())








