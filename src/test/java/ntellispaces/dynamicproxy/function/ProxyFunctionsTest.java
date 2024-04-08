package ntellispaces.dynamicproxy.function;

import intellispaces.commons.exception.UnexpectedViolationException;
import intellispaces.dynamicproxy.function.ProxyFunctions;
import intellispaces.dynamicproxy.model.Watcher;
import intellispaces.dynamicproxy.object.BaseWatcher;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link ProxyFunctions} class.
 */
public class ProxyFunctionsTest {

  @Test
  public void testResetWatcher_whenIsWatcher() {
    // Given
    Watcher watcher = spy(new BaseWatcher());

    // When
    ProxyFunctions.resetWatcher(watcher);

    // Then
    verify(watcher).reset();
  }

  @Test
  public void testInvokedMethodsOf_whenIsWatcher() {
    // Given
    Watcher watcher = spy(new BaseWatcher());

    List<Method> methods = new ArrayList<>();
    when(watcher.invokedMethods()).thenReturn(methods);

    // When
    List<Method> answer = ProxyFunctions.invokedMethodsOf(watcher);

    // Then
    assertThat(answer).isSameAs(methods);
    verify(watcher).invokedMethods();
  }

  @Test
  public void testResetWatcher_whenIsNotWatcher() {
    assertThatThrownBy(
        () -> ProxyFunctions.resetWatcher(new Object())
    ).isExactlyInstanceOf(UnexpectedViolationException.class);
  }

  @Test
  public void testInvokedMethodsOf_whenIsNotWatcher() {
    assertThatThrownBy(
        () -> ProxyFunctions.invokedMethodsOf(new Object())
    ).isExactlyInstanceOf(UnexpectedViolationException.class);
  }
}
