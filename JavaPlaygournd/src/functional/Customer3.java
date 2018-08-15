package functional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomasz on 03/04/2017.
 */
public class Customer3 {
    private static ArrayList<Customer3> allCustomers = new ArrayList<>();
    private int id = 0;
    private String name;
    private String address;
    private String state;
    private String primaryContact;
    private String domain;
    private boolean enabled = true;

    public Customer3(String name,String address,String state, String primaryContact , String domain){
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

    public Customer3(){}

    public static List<String> getEnabledCustomerAddresses(){
        return Customer3.getEnabledCustomersField(new Function1<Customer3, String>() {
            @Override
            public String call(Customer3 customer) { return customer.address;}
        });
    }
    public static List<String> getEnabledCustomerNames(){
        return Customer3.getEnabledCustomersField(new Function1<Customer3, String>() {
            @Override
            public String call(Customer3 customer) { return customer.name;}
        });
    }
    public static List<String> getEnabledCustomerStates(){
        return Customer3.getEnabledCustomersField(new Function1<Customer3, String>() {
            @Override
            public String call(Customer3 customer) { return customer.state;}
        });
    }
    public static List<String> getEnabledCustomerPrimaryContacts(){
        return Customer3.getEnabledCustomersField(new Function1<Customer3, String>() {
            @Override
            public String call(Customer3 customer) { return customer.primaryContact;}
        });
    }
    public static List<String> getEnabledCustomerDomains(){
        return Customer3.getEnabledCustomersField(new Function1<Customer3, String>() {
            @Override
            public String call(Customer3 customer) { return customer.domain;}
        });
    }


    public static <B> List<B> getEnabledCustomersField(Function1<Customer3,B> func){
        List<B> outLiSt = new ArrayList<B>();
        for(Customer3 customer : Customer3.allCustomers){
            if (customer.enabled){
                outLiSt.add(func.call(customer));
            }
        }
        return outLiSt;
    }

    public static void main(String[] args){
        Customer3 customer1= new Customer3("Rick1","add1","state","primaryContact","domain");
        Customer3 customer2= new Customer3("Rick2","add2","state","primaryContact","domain");
        Customer3.allCustomers.add(customer1);
        Customer3.allCustomers.add(customer2);

        System.out.println(getEnabledCustomerAddresses());
        System.out.println(getEnabledCustomerNames());
        System.out.println(getEnabledCustomerStates());
        System.out.println(getEnabledCustomerPrimaryContacts());
        System.out.println(getEnabledCustomerDomains());


    }

}


