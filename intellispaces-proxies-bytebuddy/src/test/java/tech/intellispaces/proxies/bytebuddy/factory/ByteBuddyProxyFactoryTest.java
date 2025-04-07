package tech.intellispaces.proxies.bytebuddy.factory;

import org.junit.jupiter.api.Test;
import tech.intellispaces.proxies.test.DynamicProxyTest;

/**
 * Tests for {@link ByteBuddyDynamicProxyFactory} class.
 */
public class ByteBuddyProxyFactoryTest {
  private final ByteBuddyDynamicProxyFactory factory = new ByteBuddyDynamicProxyFactory();

  @Test
  public void testCreateTrackedClass_whenInterface() {
    DynamicProxyTest.testCreateTrackedClass_whenInterface(factory);
  }

  @Test
  public void testCreateTrackedClass_whenAbstractClass() {
    DynamicProxyTest.testCreateTrackedClass_whenAbstractClass(factory);
  }

  @Test
  public void testCreateProxyClass_whenAbstractClass_andAbstractMethodHandler() {
    DynamicProxyTest.testCreateProxyClass_whenAbstractClass_andAbstractMethodHandler(factory);
  }

  @Test
  public void testCreateProxyClass_whenAbstractClass_andSpecificMethodHandler() {
    DynamicProxyTest.testCreateProxyClass_whenAbstractClass_andSpecificMethodHandler(factory);
  }
}
