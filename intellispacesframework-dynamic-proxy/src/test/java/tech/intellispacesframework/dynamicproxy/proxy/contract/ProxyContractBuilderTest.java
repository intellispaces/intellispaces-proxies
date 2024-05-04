package tech.intellispacesframework.dynamicproxy.proxy.contract;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import tech.intellispacesframework.dynamicproxy.sample.InterfaceSample;
import tech.intellispacesframework.dynamicproxy.sample.InterfaceSampleTracked;
import tech.intellispacesframework.dynamicproxy.tracker.Tracker;
import tech.intellispacesframework.dynamicproxy.tracker.TrackerFunctions;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;

/**
 * Tests for {@link ProxyContractBuilder} class.
 */
public class ProxyContractBuilderTest {

  @Test
  public void testBuild_whenValid() throws Exception {
    try (MockedStatic<TrackerFunctions> trackerFunctions = mockStatic(TrackerFunctions.class)) {
      // Given
      String proxyClassName = "Proxy";
      Supplier<Integer> intSupplier = () -> 1;
      Function<Integer, Integer> biFunction = (Integer i) -> 2 * i;
      CommonMethodHandler abstractMethodHandler = (Object object, Method method, Object[] arguments) -> "";

      trackerFunctions.when(() -> TrackerFunctions.createTrackedObject(eq(InterfaceSample.class), any((Tracker.class))))
          .thenAnswer(invocation -> new InterfaceSampleTracked(invocation.getArgument(1, Tracker.class)));

      // When
      ProxyContract<InterfaceSample> contract = ProxyContractBuilder.get()
          .className(proxyClassName)
          .type(InterfaceSample.class)
          .additionalInterfaces(Serializable.class)
          .additionalInterfaces(List.of(Cloneable.class))
          .whenCall(InterfaceSample::method1a).then(intSupplier)
          .whenCall(InterfaceSample.class.getDeclaredMethod("method1b"), Integer.class).then(intSupplier)
          .whenCall(InterfaceSample::method2a, 0).then(intSupplier)
          .whenCall(InterfaceSample::method2b, 0).then(biFunction)
          .abstractMethodHandler(abstractMethodHandler)
          .build();

      // Then
      assertThat(contract.className()).isEqualTo(proxyClassName);
      assertThat(contract.type()).isEqualTo(InterfaceSample.class);
      assertThat(contract.additionalInterfaces()).containsExactly(Serializable.class, Cloneable.class);

      assertThat(contract.methodHandlers())
          .hasSize(4)
          .containsKey(InterfaceSample.class.getDeclaredMethod("method1a"))
          .containsKey(InterfaceSample.class.getDeclaredMethod("method1b"))
          .containsKey(InterfaceSample.class.getDeclaredMethod("method2a", int.class))
          .containsKey(InterfaceSample.class.getDeclaredMethod("method2b", int.class));
      assertThat(contract.abstractMethodHandler()).containsSame(abstractMethodHandler);
    }
  }

  @Test
  public void testBuild_whenProxyClassNameIsNotDefined() {
    assertThatThrownBy(() -> {
      ProxyContractBuilder.get()
          .type(InterfaceSample.class)
          .build();
    }).isExactlyInstanceOf(NullPointerException.class)
      .hasMessage("Proxy class name should be defined");
  }

  @Test
  public void testBuild_whenProxyTypeIsNotDefined() {
    assertThatThrownBy(() -> {
      ProxyContractBuilder.get()
          .className("Proxy")
          .build();
    }).isExactlyInstanceOf(NullPointerException.class)
        .hasMessage("Proxy type should be defined");
  }
}
