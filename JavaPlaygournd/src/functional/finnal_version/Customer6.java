package functional.finnal_version;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomasz on 03/04/2017.
 */
public class Customer6 {
    public static ArrayList<Customer6> allCustomers = new ArrayList<>();
    public int id =0;
    private String name;
    private String address;
    private String state;
    private String primaryContact;
    private String domain;
    public Boolean enabled = true;
    public Contract contract;



    public Customer6(String name, String address, String state, String primaryContact , String domain){
        this.id +=1;
        this.name = name;
        this.address = address;
        this.state = state;
        this.primaryContact = primaryContact;
        this.domain = domain;
    }


    public static void foreach(List<Customer6> inList, Foreach1<Customer6> func){
        for(Customer6 customer: inList){
            func.call(customer);
        }
    }
    static final public Function1 <Customer6, Boolean> EnableCustomer =
            customer -> customer.enabled;

    public Customer6(){}

    public static List<String> getEnabledCustomerAddresses(){
        return Customer6.getField(customer -> customer.enabled, customer -> customer.address);
    }
    public static List<String> getEnabledCustomerNames(){
        return Customer6.getField(customer -> customer.enabled, customer -> customer.name);
    }
    public static List<String> getEnabledCustomerStates(){
        return Customer6.getField(customer -> customer.enabled, customer -> customer.state);
    }
    public static List<String> getEnabledCustomerPrimaryContacts(){
        return Customer6.getField(customer -> customer.enabled, customer -> customer.primaryContact);
    }
    public static List<String> getEnabledCustomerDomains(){
        return Customer6.getField(customer -> customer.enabled, customer -> customer.domain);
    }


    public static List<String> getEnabledCustomersBossesEmail() {
        return Customer6.getField(customer -> customer.enabled, customer -> "boss@"+customer.domain);
    }
    public static List<String> getEnabledCustomersSomeoneEmail(final  String someone) {
        return Customer6.getField(customer -> customer.enabled, customer -> someone+"@"+customer.domain);
    }


    public static List<String> getDisabledCustomerAddresses(){
        return Customer6.getField(customer -> !customer.enabled, customer -> customer.address);
    }
    public static List<String> getDisabledCustomerNames(){
        return Customer6.getField(customer -> !customer.enabled, customer -> customer.name);
    }
    public static List<String> getDisabledCustomerStates(){
        return Customer6.getField(customer -> !customer.enabled, customer -> customer.state);
    }
    public static List<String> getDisabledCustomerPrimaryContacts(){
        return Customer6.getField(customer -> !customer.enabled, customer -> customer.primaryContact);
    }
    public static List<String> getDisabledCustomerDomains(){
        return Customer6.getField(customer -> !customer.enabled, customer -> customer.domain);
    }


    public static List<String> getDisabledCustomersBossesEmail() {
        return Customer6.getField(customer -> !customer.enabled,customer -> "boss@"+customer.domain);
    }
    public static List<String> getDisabledCustomersSomeoneEmail(final  String someone) {
        return Customer6.getField(customer -> !customer.enabled,customer -> someone+"@"+customer.domain);
    }

    public static <B> List<B> getField(Function1 <Customer6, Boolean> test, Function1<Customer6,B> func){
        List<B> outLiSt = new ArrayList<B>();
        for(Customer6 customer : Customer6.filter(Customer6.allCustomers,test))
            outLiSt.add(func.call(customer));
        return outLiSt;
    }

    public static List<Customer6> filter(List<Customer6> initList,Function1 <Customer6, Boolean> test){
        List<Customer6> outLiSt = new ArrayList<>();
        for(Customer6 customer : initList) {
            if (test.call((customer))){
                outLiSt.add(customer);
            }
        }
    return outLiSt;
    }

    public static List<Customer6> getCustomerById(List<Customer6> initList,final Integer customer_id){
        return Customer6.filter(initList,customer -> customer.id == customer_id);
    }

    public static void main(String[] args){
        Customer6 customer1= new Customer6("Rick1","add1","state","primaryContact","domain");
        Customer6 customer2= new Customer6("Rick2","add2","state","primaryContact","domain");
        Customer6.allCustomers.add(customer1);
        Customer6.allCustomers.add(customer2);

        System.out.println(getEnabledCustomerAddresses());
        System.out.println(getEnabledCustomerNames());
        System.out.println(getEnabledCustomerStates());
        System.out.println(getEnabledCustomerPrimaryContacts());
        System.out.println(getEnabledCustomerDomains());


    }


    }


