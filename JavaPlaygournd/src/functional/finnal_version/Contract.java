package functional.finnal_version;

import java.util.Calendar;


public class Contract {

    public Calendar begin_date;
    public Calendar end_date;
    public Boolean enabled = true;


   public Contract(Calendar begin_date){
       this.begin_date = begin_date;
       this.end_date = Calendar.getInstance();
       this.end_date.setTimeInMillis(this.begin_date.getTimeInMillis());
       this.end_date.add(Calendar.YEAR,2);
   }
   public static void setContractForCustomer(Integer customer_id, final Boolean status){
       Customer6.foreach(Customer6.getCustomerById(Customer6.allCustomers, customer_id), customer -> customer.contract.enabled = status);
    }

}
