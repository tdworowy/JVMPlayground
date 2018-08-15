package functional.finnal_version;

import java.util.Calendar;
import java.util.List;


public class Contract {
    public Contract setBegin_date(Calendar begin_date) {
        this.begin_date = begin_date;
        return this;
    }

    public Contract setEnd_date(Calendar end_date) {
        this.end_date = end_date;
        return this;
    }

    public Contract setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public Calendar begin_date;
    public Calendar end_date;
    public Boolean enabled = true;


   public Contract(Calendar begin_date){
       this.begin_date = begin_date;
       this.end_date = Calendar.getInstance();
       this.end_date.setTimeInMillis(this.begin_date.getTimeInMillis());
       this.end_date.add(Calendar.YEAR,2);
   }
   public static List<Object> setContractForCustomer(Integer customer_id, final Boolean status){
       return FunctionalConcepts.map(Customer6.getCustomerById(Customer6.allCustomers, customer_id), customer -> customer.contract.enabled = status);
    }

    public static void display(Integer customer_id){
        FunctionalConcepts.foreach(
                Contract.setContractForCustomer(customer_id, true),contract -> System.out.println(contract.toString())
        );
    }

}
