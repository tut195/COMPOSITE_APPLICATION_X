package com.babenkovladimir.composite_application_x.java_theory;

import java.lang.reflect.Field;

public class Interface {

  final String a = "";
  static int b = 10;

}

class Parent {

  final String a = "";

  class Child {

    void function() {
      String b =  a;
    }
  }

  class Victim {

    private int field = 42;
  }

  void aa() throws IllegalAccessException, NoSuchFieldException {
    //...
    Victim victim = new Victim();
    Field field = Victim.class.getDeclaredField("field");
    field.setAccessible(true);
    int fieldValue = (int) field.get(victim);
//...
  }

}

class Init {

  String a;
  static String aa = "";

  {
    a = "";
    System.out.println("dcvsdc");


  }

  void ddd(){


    StaticClass a =  new StaticClass();
  }

  static class StaticClass{
    String aaa;

    void a(){
      aa.split("");
    }
  }
}