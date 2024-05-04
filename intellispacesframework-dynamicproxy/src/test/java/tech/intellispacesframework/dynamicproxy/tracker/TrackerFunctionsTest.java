package tech.intellispacesframework.dynamicproxy.tracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import tech.intellispacesframework.commons.reflection.ReflectionFunctions;
import tech.intellispacesframework.dynamicproxy.DynamicProxy;
import tech.intellispacesframework.dynamicproxy.sample.InterfaceSample;
import tech.intellispacesframework.dynamicproxy.sample.InterfaceSampleTracked;

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
    try (MockedStatic<DynamicProxy> dynamicProxy = mockStatic(DynamicProxy.class)) {
      // Given
      dynamicProxy.when(() -> DynamicProxy.createTrackedClass(eq(InterfaceSample.class)))
          .thenReturn(InterfaceSampleTracked.class);

      // When
      Class<InterfaceSample> answer1 = TrackerFunctions.getOrCreateTrackedClass(InterfaceSample.class);
      Class<InterfaceSample> answer2 = TrackerFunctions.getOrCreateTrackedClass(InterfaceSample.class);

      // Then
      assertThat(answer1).isEqualTo(InterfaceSampleTracked.class);
      assertThat(answer2).isEqualTo(InterfaceSampleTracked.class);
      dynamicProxy.verify(() -> DynamicProxy.createTrackedClass(eq(InterfaceSample.class)), times(1));
    }
  }

  @Test
  public void testCreateTrackedObject() {
    try (MockedStatic<DynamicProxy> dynamicProxy = mockStatic(DynamicProxy.class)) {
      // Given
      Tracker tracker = TrackerBuilder.build();
      dynamicProxy.when(() -> DynamicProxy.createTrackedClass(eq(InterfaceSample.class)))
          .thenReturn(InterfaceSampleTracked.class);

      // When
      InterfaceSample trackedObject = TrackerFunctions.createTrackedObject(InterfaceSample.class, tracker);

      // Then
      assertThat(trackedObject).isNotNull();
      assertThat(trackedObject).isInstanceOf(InterfaceSampleTracked.class);
      assertThat(((InterfaceSampleTracked) trackedObject).getTracker()).isSameAs(tracker);
    }
  }
}
