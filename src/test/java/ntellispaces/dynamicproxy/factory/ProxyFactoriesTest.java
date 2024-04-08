package ntellispaces.dynamicproxy.factory;

import intellispaces.commons.exception.UnexpectedViolationException;
import intellispaces.dynamicproxy.factory.ProxyFactories;
import intellispaces.dynamicproxy.factory.ProxyFactory;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.Optional;
import java.util.ServiceLoader;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link ProxyFactories} class.
 */
public class ProxyFactoriesTest {

  @Test
  @SuppressWarnings("unchecked, rawtypes")
  public void testGet_whenServiceExists() {
    try (MockedStatic<ServiceLoader> mockStatic = mockStatic(ServiceLoader.class)) {
      // Given
      ServiceLoader<ProxyFactory> serviceLoader = mock(ServiceLoader.class);

      ProxyFactory proxyFactory = mock(ProxyFactory.class);
      when(serviceLoader.findFirst()).thenReturn(Optional.of(proxyFactory));

      mockStatic.when(() -> ServiceLoader.load(ProxyFactory.class)).thenReturn(serviceLoader);

      // When
      ProxyFactory answer = ProxyFactories.get();

      // Then
      assertSame(answer, proxyFactory);
    }
  }

  @Test
  @SuppressWarnings("unchecked, rawtypes")
  public void testGet_whenServiceNotExists() {
    try (MockedStatic<ServiceLoader> mockStatic = mockStatic(ServiceLoader.class)) {
      // Given
      ServiceLoader<ProxyFactory> serviceLoader = mock(ServiceLoader.class);

      when(serviceLoader.findFirst()).thenReturn(Optional.empty());

      mockStatic.when(() -> ServiceLoader.load(ProxyFactory.class)).thenReturn(serviceLoader);

      // Then
      assertThrows(UnexpectedViolationException.class, ProxyFactories::get);
    }
  }
}
