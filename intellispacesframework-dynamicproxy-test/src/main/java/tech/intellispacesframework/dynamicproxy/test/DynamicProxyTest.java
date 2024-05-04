package tech.intellispacesframework.dynamicproxy.test;

import tech.intellispacesframework.commons.exception.UnexpectedViolationException;
import tech.intellispacesframework.dynamicproxy.factory.DynamicProxyFactory;
import tech.intellispacesframework.dynamicproxy.proxy.contract.ProxyContract;
import tech.intellispacesframework.dynamicproxy.proxy.contract.ProxyContractBuilder;
import tech.intellispacesframework.dynamicproxy.test.samples.AbstractClass;
import tech.intellispacesframework.dynamicproxy.test.samples.TrackedInterface;
import tech.intellispacesframework.dynamicproxy.tracker.Tracker;
import tech.intellispacesframework.dynamicproxy.tracker.TrackerBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public interface DynamicProxyTest {

  static void testCreateTrackedClass_whenInterface(DynamicProxyFactory factory) {
    // Given
    final TrackedInterface trackedObject;
    Tracker tracker = TrackerBuilder.build();
    try {
      Class<TrackedInterface> trackedClass = factory.createTrackedClass(TrackedInterface.class);
      trackedObject = trackedClass.getConstructor(Tracker.class).newInstance(tracker);
    } catch (Exception e) {
      throw UnexpectedViolationException.withCauseAndMessage(e, "Failed to create tracked class");
    }
    assertThat(trackedObject).isInstanceOf(TrackedInterface.class);
    assertThat(tracker.getInvokedMethods()).isEmpty();

    // When
    trackedObject.method1();

    // Then
    assertThat(tracker.getInvokedMethods()).hasSize(1);
    assertThat(tracker.getInvokedMethods().get(0).getName()).isEqualTo("method1");

    // When
    trackedObject.method2(0);

    // Then
    assertThat(tracker.getInvokedMethods()).hasSize(2);
    assertThat(tracker.getInvokedMethods().get(0).getName()).isEqualTo("method1");
    assertThat(tracker.getInvokedMethods().get(1).getName()).isEqualTo("method2");

    // When
    trackedObject.method1();

    // Then
    assertThat(tracker.getInvokedMethods()).hasSize(3);
    assertThat(tracker.getInvokedMethods().get(0).getName()).isEqualTo("method1");
    assertThat(tracker.getInvokedMethods().get(1).getName()).isEqualTo("method2");
    assertThat(tracker.getInvokedMethods().get(2).getName()).isEqualTo("method1");

    // When
    tracker.reset();

    // Then
    assertThat(tracker.getInvokedMethods()).isEmpty();
  }

  static void testCreateTrackedClass_whenAbstractClass(DynamicProxyFactory factory) {
    // Given
    final AbstractClass trackedObject;
    Tracker tracker = TrackerBuilder.build();
    try {
      Class<AbstractClass> trackedClass = factory.createTrackedClass(AbstractClass.class);
      trackedObject = trackedClass.getConstructor(Tracker.class).newInstance(tracker);
    } catch (Exception e) {
      throw UnexpectedViolationException.withCauseAndMessage(e, "Failed to create tracked class");
    }
    assertThat(trackedObject).isInstanceOf(AbstractClass.class);
    assertThat(tracker.getInvokedMethods()).isEmpty();

    // When
    trackedObject.method1(0);

    // Then
    assertThat(tracker.getInvokedMethods()).hasSize(1);
    assertThat(tracker.getInvokedMethods().get(0).getName()).isEqualTo("method1");

    // When
    trackedObject.method2("");

    // Then
    assertThat(tracker.getInvokedMethods()).hasSize(2);
    assertThat(tracker.getInvokedMethods().get(0).getName()).isEqualTo("method1");
    assertThat(tracker.getInvokedMethods().get(1).getName()).isEqualTo("method2");

    // When
    trackedObject.method1(0);

    // Then
    assertThat(tracker.getInvokedMethods()).hasSize(3);
    assertThat(tracker.getInvokedMethods().get(0).getName()).isEqualTo("method1");
    assertThat(tracker.getInvokedMethods().get(1).getName()).isEqualTo("method2");
    assertThat(tracker.getInvokedMethods().get(2).getName()).isEqualTo("method1");

    // When
    tracker.reset();

    // Then
    assertThat(tracker.getInvokedMethods()).isEmpty();
  }

  static void testCreateProxyClass_whenAbstractClass_andAbstractMethodHandler(DynamicProxyFactory factory) {
    // Given
    final AbstractClass proxy;
    try {
      ProxyContract<AbstractClass> contract = ProxyContractBuilder.get()
          .className(AbstractClass.class.getCanonicalName() + "Proxy")
          .type(AbstractClass.class)
          .abstractMethodHandler((object, method, arguments) -> arguments[0])
          .build();
      Class<AbstractClass> proxyClass = factory.createProxyClass(contract);
      proxy = proxyClass.getConstructor().newInstance();
    } catch (Exception e) {
      throw UnexpectedViolationException.withCauseAndMessage(e, "Failed to create proxy");
    }

    // Then
    assertThat(proxy.getClass()).isAssignableTo(AbstractClass.class);
    assertThat(proxy.getClass().getCanonicalName()).isEqualTo(AbstractClass.class.getCanonicalName() + "Proxy");

    assertThat(proxy.method1(1)).isEqualTo(1);
    assertThat(proxy.method1(2)).isEqualTo(2);

    assertThat(proxy.method2("a")).isEqualTo("a");
    assertThat(proxy.method2("b")).isEqualTo("b");
  }

  static void testCreateProxyClass_whenAbstractClass_andSpecificMethodHandler(DynamicProxyFactory factory) {
    // Given
    final AbstractClass proxy;
    try {
      ProxyContract<AbstractClass> contract = ProxyContractBuilder.get()
          .className(AbstractClass.class.getCanonicalName() + "Proxy")
          .type(AbstractClass.class)
          .whenCall(AbstractClass::method1, 0).then(i -> i * 2)
          .build();
      Class<AbstractClass> proxyClass = factory.createProxyClass(contract);
      proxy = proxyClass.getConstructor().newInstance();
    } catch (Exception e) {
      throw UnexpectedViolationException.withCauseAndMessage(e, "Failed to create proxy");
    }

    // Then
    assertThat(proxy.getClass()).isAssignableTo(AbstractClass.class);
    assertThat(proxy.getClass().getCanonicalName()).isEqualTo(AbstractClass.class.getCanonicalName() + "Proxy");

    assertThat(proxy.method1(1)).isEqualTo(2);
    assertThat(proxy.method1(2)).isEqualTo(4);

    assertThatThrownBy(() -> proxy.method2("a"))
        .isExactlyInstanceOf(UnexpectedViolationException.class)
            .hasMessage("Interceptor of abstract proxy method 'method2' is not defined. Class " + AbstractClass.class.getCanonicalName());
  }
}
