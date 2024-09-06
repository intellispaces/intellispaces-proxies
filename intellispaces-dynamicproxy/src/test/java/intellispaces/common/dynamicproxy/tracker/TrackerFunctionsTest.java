package intellispaces.common.dynamicproxy.tracker;

import intellispaces.common.dynamicproxy.Proxies;
import intellispaces.common.dynamicproxy.sample.InterfaceSample;
import intellispaces.common.dynamicproxy.sample.InterfaceSampleTracked;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import intellispaces.common.base.reflection.ReflectionFunctions;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

/**
 * Tests for {@link TrackerFunctions} class.
 */
public class TrackerFunctionsTest {

  @BeforeEach
  public void init() throws Exception {
    ReflectionFunctions.getStaticField(TrackerFunctions.class, "TRACKED_CLASSES_CACHE", Map.class).clear();
  }

  @Test
  public void testGetOrCreateTrackedClass() {
    try (MockedStatic<Proxies> dynamicProxy = mockStatic(Proxies.class)) {
      // Given
      dynamicProxy.when(() -> Proxies.getTrackedClass(eq(InterfaceSample.class)))
          .thenReturn(InterfaceSampleTracked.class);

      // When
      Class<InterfaceSample> answer1 = TrackerFunctions.getOrCreateTrackedClass(InterfaceSample.class);
      Class<InterfaceSample> answer2 = TrackerFunctions.getOrCreateTrackedClass(InterfaceSample.class);

      // Then
      assertThat(answer1).isEqualTo(InterfaceSampleTracked.class);
      assertThat(answer2).isEqualTo(InterfaceSampleTracked.class);
      dynamicProxy.verify(() -> Proxies.getTrackedClass(eq(InterfaceSample.class)), times(1));
    }
  }

  @Test
  public void testCreateTrackedObject() {
    try (MockedStatic<Proxies> dynamicProxy = mockStatic(Proxies.class)) {
      // Given
      Tracker tracker = TrackerBuilder.build();
      dynamicProxy.when(() -> Proxies.getTrackedClass(eq(InterfaceSample.class)))
          .thenReturn(InterfaceSampleTracked.class);

      // When
      InterfaceSample trackedObject = TrackerFunctions.createTrackedObject(InterfaceSample.class, tracker);

      // Then
      assertThat(trackedObject).isNotNull();
      assertThat(trackedObject).isInstanceOf(InterfaceSampleTracked.class);
      Assertions.assertThat(((InterfaceSampleTracked) trackedObject).getTracker()).isSameAs(tracker);
    }
  }
}
