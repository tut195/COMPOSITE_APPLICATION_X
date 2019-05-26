package com.babenkovladimir.composite_application_x.java_theory;

import com.babenkovladimir.composite_application_x.java_theory.StaticTestClass.LocalClass;

class StaticTestClass {


  static class InnerStatic{}

  class LocalClass{}
}

class Test{


  @Override
  public int hashCode() {
    return super.hashCode();
  }

  {LocalClass c = new StaticTestClass().new LocalClass();
  }
}
