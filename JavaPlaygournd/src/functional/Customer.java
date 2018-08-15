package functional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomasz on 03/04/2017.
 */
class Customer {
    static public ArrayList<Customer> allCustomers =new ArrayList<>();
    public int id =0;
    public String name;
    public String address;
    public String state;
    public String primaryContact;
    public String domain;
    public boolean enabled =true;


    public Customer(String name,String address,String state, String primaryContact , String domain){
        this.id +=1;
        this.name = name;
        this.address = address;
        this.state = state;
        this.primaryContact = primaryContact;
        this.domain = domain;
    }

    private interface ConversionFunction{
        public String call(Customer customer);
    }
    static private class CustomerAddress implements ConversionFunction{
        public String call(Customer customer){ return  customer.address;}
    }

    public Customer(){}




    public static List<String> getEnabledCustomersField(ConversionFunction func){
        List<String> outLiSt = new ArrayList<>();
        for(Customer customer : Customer.allCustomers){
            if (customer.enabled){
                outLiSt.add(func.call(customer));
            }
        }
        return outLiSt;
    }



    public static List<String> getEnabledCustomerAddresses(){
        List <String> outList =new ArrayList<>();
        for(Customer customer : Customer.allCustomers){
            if(customer.enabled){
                outList.add(customer.address);
            }
        }
        return outList;
    }

    public static List<String> getEnabledCustomrNames(){
        List <String> outList =new ArrayList<>();
        for(Customer customer : Customer.allCustomers){
            if(customer.enabled){
                outList.add(customer.name);
            }
        }
        return outList;
    }
    public static List<String> getEnabledCustomrStates(){
        List <String> outList =new ArrayList<>();
        for(Customer customer : Customer.allCustomers){
            if(customer.enabled){
                outList.add(customer.state);
            }
        }
        return outList;
    }

    public static List<String> getEnabledCustomrPrimaryContacts(){
        List <String> outList =new ArrayList<>();
        for(Customer customer : Customer.allCustomers){
            if(customer.enabled){
                outList.add(customer.primaryContact);
            }
        }
        return outList;
    }

    public static List<String> getEnabledCustomrDomain(){
        List <String> outList =new ArrayList<>();
        for(Customer customer : Customer.allCustomers){
            if(customer.enabled){
                outList.add(customer.domain);
            }
        }
        return outList;
    }


    public static void main(String[] args){
        Customer customer1= new Customer("Rick1","add1","state","primaryContact","domain");
        Customer customer2= new Customer("Rick2","add2","state","primaryContact","domain");
        Customer.allCustomers.add(customer1);
        Customer.allCustomers.add(customer2);

        System.out.println(getEnabledCustomerAddresses());
        System.out.println(getEnabledCustomrNames());
        System.out.println(getEnabledCustomrStates());
        System.out.println(getEnabledCustomrPrimaryContacts());
        System.out.println(getEnabledCustomrDomain());


    }

}


