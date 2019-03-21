/**
 * 
 */
package com.rx.client.test;

import static org.junit.Assert.assertEquals;

import com.rx.client.util.ReflectUtils;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(UnitTest.class)
public class ReflectUtilTest {

  public class A<T> { }
  
  public abstract class B<T> extends A<T> {
    public void test(Class actual) {
      //System.out.println("generaticType = " + getTypeArgument().getName());
      assertEquals(actual.getName(),getTypeArgument().getName());
    }
    
    public Class<?> getTypeArgument() {
      return ReflectUtils.getTypeArguments(B.class, getClass()).get(0);
    }
  }
  
  public class C<T> extends B<T> { }
  
  public class D extends C<Integer> { }

  public class E extends C<String> { }
  
  @Test
  @Category(UnitTest.class)
  public void testWithSingleGenericType() {
    D d = new D();
    d.test(Integer.class);

    E e = new E();
    e.test(String.class);
  }
}
