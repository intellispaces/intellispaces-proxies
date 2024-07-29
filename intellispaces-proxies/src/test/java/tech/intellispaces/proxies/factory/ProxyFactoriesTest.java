package tech.intellispaces.proxies.factory;

import org.mockito.Mockito;
import tech.intellispaces.commons.exception.UnexpectedViolationException;
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
 * Tests for {@link DynamicProxyFactories} class.
 */
public class ProxyFactoriesTest {

  @Test
  @SuppressWarnings("unchecked, rawtypes")
  public void testGet_whenServiceExists() {
    try (MockedStatic<ServiceLoader> mockStatic = mockStatic(ServiceLoader.class)) {
      // Given
      ServiceLoader<DynamicProxyFactory> serviceLoader = mock(ServiceLoader.class);

      DynamicProxyFactory proxyFactory = Mockito.mock(DynamicProxyFactory.class);
      when(serviceLoader.findFirst()).thenReturn(Optional.of(proxyFactory));

      mockStatic.when(() -> ServiceLoader.load(DynamicProxyFactory.class)).thenReturn(serviceLoader);

      // When
      DynamicProxyFactory answer = DynamicProxyFactories.get();

      // Then
      assertSame(answer, proxyFactory);
    }
  }

  @Test
  @SuppressWarnings("unchecked, rawtypes")
  public void testGet_whenServiceNotExists() {
    try (MockedStatic<ServiceLoader> mockStatic = mockStatic(ServiceLoader.class)) {
      // Given
      ServiceLoader<DynamicProxyFactory> serviceLoader = mock(ServiceLoader.class);

      when(serviceLoader.findFirst()).thenReturn(Optional.empty());

      mockStatic.when(() -> ServiceLoader.load(DynamicProxyFactory.class)).thenReturn(serviceLoader);

      // Then
      assertThrows(UnexpectedViolationException.class, DynamicProxyFactories::get);
    }
  }
}
