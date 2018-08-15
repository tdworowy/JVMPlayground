package functional;

/**
 * Created by Tomasz on 16/04/2017.
 */
public class Closure {
    public String foo="";

    public static  Closure process(final  Closure t){
        System.out.println(t.toString() + " = "+ t.foo);
        t.foo= "bar";
        new Runnable(){
            public void run(){
                System.out.println(t.toString()+" = "+t.foo);
                t.foo="bazz";
            }
        }.run();
        System.out.println(t.toString()+" = "+t.foo);
        return t;
    }
    public static  Closure process2(final  Closure t){
        System.out.println(t.toString() + " = "+ t.foo);
        t.foo= "bar";
        ((Runnable) () -> {
            System.out.println(t.toString() + " = " + t.foo);
            t.foo = "bazz";
        }).run();
        System.out.println(t.toString()+" = "+t.foo);
        return t;
    }

    public static  Closure process3(final  Closure t){
        System.out.println(t.toString() + " = "+ t.foo);
        t.foo= "bar";
        System.out.println(t.toString() + " = " + t.foo);
        t.foo = "bazz";
        System.out.println(t.toString()+" = "+t.foo);
        return t;
    }


    public static void main(String[] args){
        process(new Closure());
        process2(new Closure());
        process3(new Closure());
    }
}
