package ntellispaces.dynamicproxy.object;

import intellispaces.dynamicproxy.object.BaseWatcher;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Tests for {@link BaseWatcher} class.
 */
public class BaseWatcherTest {

  @Test
  public void test() {
    BaseWatcher watcher = new BaseWatcher();
    assertThat(watcher.invokedMethods()).isEmpty();

    Method method1 = mock(Method.class);
    Method method2 = mock(Method.class);
    watcher.addInvokedMethod(method1);
    watcher.addInvokedMethod(method2);
    assertThat(watcher.invokedMethods()).containsExactly(method1, method2);

    watcher.reset();;
    assertThat(watcher.invokedMethods()).isEmpty();
  }
}
