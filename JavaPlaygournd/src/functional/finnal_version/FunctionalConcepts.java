package functional.finnal_version;

import java.util.ArrayList;
import java.util.List;

public class FunctionalConcepts {

    public static <A1, B> List<B> map(List<A1> inList, Function1<A1, B> func) {
        List<B> outList = new ArrayList<>();
        for (A1 obj : inList) {
            outList.add(func.call(obj));
        }
        return outList;
    }

    public static <A>void foreach(List<A> inList, Foreach1<A> func) {
        for (A obj : inList) {
            func.call(obj);
        }
    }

    public static <A>List<A> filter(List<A> initList,Function1 <A, Boolean> test){
        List<A> outLiSt = new ArrayList<>();
        for(A obj : initList) {
            if (test.call((obj))){
                outLiSt.add(obj);
            }
        }
        return outLiSt;
    }


}