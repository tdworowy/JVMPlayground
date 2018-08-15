package functional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomasz on 03/04/2017.
 */
public class Customer5 {
    static public ArrayList<Customer5> allCustomers =new ArrayList<>();
    public int id =0;
    public String name;
    public String address;
    public String state;
    public String primaryContact;
    public String domain;
    public boolean enabled =true;



    public Customer5(String name,String address,String state, String primaryContact , String domain){
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

    public Customer5(){}

    public static List<String> getEnabledCustomerAddresses(){
        return Customer5.getEnabledCustomersField(new Function1<Customer5, String>() {
            @Override
            public String call(Customer5 customer) { return customer.address;}
        });
    }
    public static List<String> getEnabledCustomerNames(){
        return Customer5.getEnabledCustomersField(new Function1<Customer5, String>() {
            @Override
            public String call(Customer5 customer) { return customer.name;}
        });
    }
    public static List<String> getEnabledCustomerStates(){
        return Customer5.getEnabledCustomersField(new Function1<Customer5, String>() {
            @Override
            public String call(Customer5 customer) { return customer.state;}
        });
    }
    public static List<String> getEnabledCustomerPrimaryContacts(){
        return Customer5.getEnabledCustomersField(new Function1<Customer5, String>() {
            @Override
            public String call(Customer5 customer) { return customer.primaryContact;}
        });
    }
    public static List<String> getEnabledCustomerDomains(){
        return Customer5.getEnabledCustomersField(new Function1<Customer5, String>() {
            @Override
            public String call(Customer5 customer) { return customer.domain;}
        });
    }


    public static <B> List<B> getEnabledCustomersField(Function1<Customer5,B> func){
        List<B> outLiSt = new ArrayList<B>();
        for(Customer5 customer : Customer5.allCustomers){
            if (customer.enabled){
                outLiSt.add(func.call(customer));
            }
        }
        return outLiSt;
    }
    public static List<String> getEnabledCustomersBossesEmail() {
       return Customer5.getEnabledCustomersField(new Function1<Customer5, String>() {
           public String call(Customer5 customer) {
               return"boss@"+customer.domain;
           }
       });
    }
    public static List<String> getEnabledCustomersSomeoneEmail(final  String someone) {
        return Customer5.getEnabledCustomersField(new Function1<Customer5, String>() {
            public String call(Customer5 customer) {
                return someone+"b@"+customer.domain;
            }
        });
    }

    public static void main(String[] args){
        Customer5 customer1= new Customer5("Rick1","add1","state","primaryContact","domain");
        Customer5 customer2= new Customer5("Rick2","add2","state","primaryContact","domain");
        Customer5.allCustomers.add(customer1);
        Customer5.allCustomers.add(customer2);

        System.out.println(getEnabledCustomerAddresses());
        System.out.println(getEnabledCustomerNames());
        System.out.println(getEnabledCustomerStates());
        System.out.println(getEnabledCustomerPrimaryContacts());
        System.out.println(getEnabledCustomerDomains());


    }


    }


