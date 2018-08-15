package functional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomasz on 03/04/2017.
 */
public class Customer2 {
    private static ArrayList<Customer2> allCustomers = new ArrayList<>();
    private int id = 0;
    private String name;
    private String address;
    private String state;
    private String primaryContact;
    private String domain;
    private boolean enabled = true;


    public Customer2(String name,String address,String state, String primaryContact , String domain){
        this.id +=1;
        this.name = name;
        this.address = address;
        this.state = state;
        this.primaryContact = primaryContact;
        this.domain = domain;
    }

    private interface Function1<A1,B>{
        public B call(A1 in1);
    }

    private interface Function2<A1,A2,B>{
        public B call(A1 in1,A2 in2);
    }
    static private class CustomerAddress implements Function1<Customer2 ,String>{
        public String call(Customer2 customer){ return  customer.address;}
    }
    static private class CustomerName implements Function1<Customer2 ,String>{
        public String call(Customer2 customer){ return  customer.name;}
    }
    static private class CustomerState implements Function1<Customer2 ,String>{
        public String call(Customer2 customer){ return  customer.state;}
    }
    static private class CustomerPrimaryContact implements Function1<Customer2 ,String>{
        public String call(Customer2 customer){ return  customer.primaryContact;}
    }
    static private class CustomerDomain implements Function1<Customer2 ,String>{
        public String call(Customer2 customer){ return  customer.domain;}
    }

    static private class CustomerAsCustomer implements Function1<Customer2,Customer2>{
        public Customer2 call(Customer2 customer) {return  customer;}
    }

    public Customer2(){}




    public static List<String> getEnabledCustomerAddresses(){
        return Customer2.getEnabledCustomersField(new CustomerAddress());
    }
    public static List<String> getEnabledCustomerNames(){
        return Customer2.getEnabledCustomersField(new CustomerName());
    }
    public static List<String> getEnabledCustomerStates(){
        return Customer2.getEnabledCustomersField(new CustomerState());
    }
    public static List<String> getEnabledCustomerPrimaryContacts(){
        return Customer2.getEnabledCustomersField(new CustomerPrimaryContact());
    }
    public static List<String> getEnabledCustomerDomains(){
        return Customer2.getEnabledCustomersField(new CustomerDomain());
    }






    public static <B> List<B> getEnabledCustomersField(Function1<Customer2,B> func){
        List<B> outLiSt = new ArrayList<B>();
        for(Customer2 customer : Customer2.allCustomers){
            if (customer.enabled){
                outLiSt.add(func.call(customer));
            }
        }
        return outLiSt;
    }


    public static void main(String[] args){
        Customer2 customer1= new Customer2("Rick1","add1","state","primaryContact","domain");
        Customer2 customer2= new Customer2("Rick2","add2","state","primaryContact","domain");
        Customer2.allCustomers.add(customer1);
        Customer2.allCustomers.add(customer2);

        System.out.println(getEnabledCustomerAddresses());
        System.out.println(getEnabledCustomerNames());
        System.out.println(getEnabledCustomerStates());
        System.out.println(getEnabledCustomerPrimaryContacts());
        System.out.println(getEnabledCustomerDomains());


    }

}


