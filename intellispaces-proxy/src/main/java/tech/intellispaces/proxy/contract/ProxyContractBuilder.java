package tech.intellispaces.proxy.contract;

import tech.intellispaces.general.exception.UnexpectedExceptions;
import tech.intellispaces.proxy.tracker.Tracker;
import tech.intellispaces.proxy.tracker.TrackerFunctions;
import tech.intellispaces.proxy.tracker.Trackers;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ProxyContractBuilder<T> {
  private Class<T> type;
  private String className;
  private CommonMethodHandler abstractMethodHandler;
  private final List<Class<?>> additionalInterfaces = new ArrayList<>();
  private final Map<Method, MethodHandler> methodHandlers = new HashMap<>();

  public static ProxyContractBuilder<Object> get() {
    return new ProxyContractBuilder<>();
  }

  public ProxyContractBuilder<T> className(String className) {
    Objects.requireNonNull(className);
    this.className = className;
    return this;
  }

  @SuppressWarnings("unchecked")
  public <N> ProxyContractBuilder<N> type(Class<N> type) {
    Objects.requireNonNull(type);
    if (this.type != null) {
      throw UnexpectedExceptions.withMessage("Proxy type has already been set");
    }
    this.type = (Class<T>) type;
    return (ProxyContractBuilder<N>) this;
  }

  public ProxyContractBuilder<T> additionalInterfaces(Class<?>... additionalInterfaces) {
    Objects.requireNonNull(additionalInterfaces);
    this.additionalInterfaces.addAll(Arrays.asList(additionalInterfaces));
    return this;
  }

  public ProxyContractBuilder<T> additionalInterfaces(List<Class<?>> additionalInterfaces) {
    Objects.requireNonNull(additionalInterfaces);
    this.additionalInterfaces.addAll(additionalInterfaces);
    return this;
  }

  public ProxyContractInterimBuilderWhenCallMethod1<T, Object> whenCall(Method method) {
    return whenCall(method, Object.class);
  }

  public <R> ProxyContractInterimBuilderWhenCallMethod1<T, R> whenCall(Method method, Class<R> returnClass) {
    Objects.requireNonNull(method);
    return new ProxyContractInterimBuilderWhenCallMethod1<>(this, method);
  }

  public <R> ProxyContractInterimBuilderWhenCallMethod1<T, R> whenCall(Function<T, R> methodReference) {
    Objects.requireNonNull(methodReference);
    return whenCall(type, methodReference);
  }

  public <A, R> ProxyContractInterimBuilderWhenCallMethod2<T, A, R> whenCall(BiFunction<T, A, R> methodReference, A argumentAnyValidValue) {
    Objects.requireNonNull(methodReference);
    return whenCall(type, methodReference, argumentAnyValidValue);
  }

  public <D, R> ProxyContractInterimBuilderWhenCallMethod1<T, R> whenCall(Class<D> declaringClass, Function<D, R> methodReference) {
    Objects.requireNonNull(declaringClass);
    Objects.requireNonNull(methodReference);

    Tracker tracker = Trackers.get();
    D trackedObject = TrackerFunctions.createTrackedObject(declaringClass, tracker);
    methodReference.apply(trackedObject);
    List<Method> methods = tracker.getInvokedMethods();
    if (methods.isEmpty()) {
      throw UnexpectedExceptions.withMessage("Desired method is not recognized");
    }
    return new ProxyContractInterimBuilderWhenCallMethod1<>(this, methods.get(0));
  }

  public <D, A, R> ProxyContractInterimBuilderWhenCallMethod2<T, A, R> whenCall(
      Class<D> declaringClass, BiFunction<D, A, R> methodReference, A argumentAnyValidValue
  ) {
    Objects.requireNonNull(declaringClass);
    Objects.requireNonNull(methodReference);

    Tracker tracker = Trackers.get();
    D trackedObject = TrackerFunctions.createTrackedObject(declaringClass, tracker);
    methodReference.apply(trackedObject, argumentAnyValidValue);
    List<Method> methods = tracker.getInvokedMethods();
    if (methods.isEmpty()) {
      throw UnexpectedExceptions.withMessage("Desired method is not recognized");
    }
    return new ProxyContractInterimBuilderWhenCallMethod2<>(this, methods.get(0));
  }

  public ProxyContractBuilder<T> abstractMethodHandler(CommonMethodHandler handler) {
    this.abstractMethodHandler = handler;
    return this;
  }

  public ProxyContract<T> build() {
    Objects.requireNonNull(className, "Proxy class name should be defined");
    Objects.requireNonNull(type, "Proxy type should be defined");
    return new ProxyContractImpl<>(
        className,
        type,
        additionalInterfaces,
        methodHandlers,
        abstractMethodHandler
    );
  }

  void addMethodHandler(Method method, MethodHandler methodHandler) {
    this.methodHandlers.put(method, methodHandler);
  }

  private ProxyContractBuilder() {}
}
