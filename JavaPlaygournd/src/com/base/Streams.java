package com.base;

import javafx.scene.paint.Stop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Tomasz on 20/03/2017.
 */
public class Streams {

    public List<String> search(List<String> myList , String startWith) {

        List<String> newList = new ArrayList<>();
        myList.stream()
                .filter(s -> s.startsWith(startWith))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toCollection(() -> newList));

    return  newList;
    }


    public List<Integer> range(int start, int stop)
    {
        List<Integer> result = new ArrayList<>();

        for(int i = start; i< stop; i++)
            result.add(i);

        return result;
    }

  public void fizzbuzz(){

      List<String> newList = new ArrayList<>();

      IntStream.rangeClosed(0, 100).mapToObj(
              i -> i % 3 == 0 ?
                      (i % 5 == 0 ? "FizzBuzz" : "Fizz") :
                      (i % 5 == 0 ? "Buzz" : i))
              .forEach(System.out::println);

  }
//  public List<String> fizzNuzz(){
//      List<String> newList = new ArrayList<>();
//      List<Integer> range = range(1,100);
//
//      range.forEach()  //TODO
//  }

}
