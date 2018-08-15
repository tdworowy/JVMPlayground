package functional;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomasz on 03/04/2017.
 */
public class Customer4 {
    private static ArrayList<Customer4> allCustomers = new ArrayList<>();
    private int id = 0;
    private String name;
    private String address;
    private String state;
    private String primaryContact;
    private String domain;
    private boolean enabled = true;


    public Customer4(String name,String address,String state, String primaryContact , String domain){
        this.id +=1;
        this.name = name;
        this.address = address;
        this.state = state;
        this.primaryContact = primaryContact;
        this.domain = domain;
    }
    private interface Function1<A1,B>{
        B call(A1 in1);
    }

    private interface Function2<A1,A2,B>{
        B call(A1 in1, A2 in2);
    }

    public Customer4(){}
    //same as customer 3 but with lambda
    public static List<String> getEnabledCustomerAddresses(){
        return Customer4.getEnabledCustomersField(customer -> customer.address);
    }
    public static List<String> getEnabledCustomerNames(){
        return Customer4.getEnabledCustomersField(customer -> customer.name);
    }
    public static List<String> getEnabledCustomerStates(){
        return Customer4.getEnabledCustomersField(customer -> customer.state);
    }
    public static List<String> getEnabledCustomerPrimaryContacts(){
        return Customer4.getEnabledCustomersField(customer -> customer.primaryContact);
    }
    public static List<String> getEnabledCustomerDomains(){
        return Customer4.getEnabledCustomersField(customer -> customer.domain);
    }


    public static <B> List<B> getEnabledCustomersField(Function1<Customer4,B> func){
        List<B> outLiSt = new ArrayList<B>();
        for(Customer4 customer : Customer4.allCustomers){
            if (customer.enabled){
                outLiSt.add(func.call(customer));
            }
        }
        return outLiSt;
    }

    public static void main(String[] args){
        Customer4 customer1= new Customer4("Rick1","add1","state","primaryContact","domain");
        Customer4 customer2= new Customer4("Rick2","add2","state","primaryContact","domain");
        Customer4.allCustomers.add(customer1);
        Customer4.allCustomers.add(customer2);

        System.out.println(getEnabledCustomerAddresses());
        System.out.println(getEnabledCustomerNames());
        System.out.println(getEnabledCustomerStates());
        System.out.println(getEnabledCustomerPrimaryContacts());
        System.out.println(getEnabledCustomerDomains());


    }

}


