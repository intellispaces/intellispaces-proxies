package intellispaces.common.dynamicproxy.contract;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

class ProxyContractImpl<T> implements ProxyContract<T> {
  private final String proxyClassName;
  private final Class<T> type;
  private final List<Class<?>> additionalInterfaces;
  private final Map<Method, MethodHandler> methodHandlers;
  private final CommonMethodHandler abstractMethodHandler;

  ProxyContractImpl(
      String proxyClassName,
      Class<T> type,
      List<Class<?>> additionalInterfaces,
      Map<Method, MethodHandler> methodHandlers,
      CommonMethodHandler abstractMethodHandler
  ) {
    Objects.requireNonNull(proxyClassName);
    Objects.requireNonNull(type);

    this.proxyClassName = proxyClassName;
    this.type = type;
    this.additionalInterfaces = additionalInterfaces == null ? List.of() : List.copyOf(additionalInterfaces);
    this.methodHandlers = methodHandlers == null ? Map.of() : Map.copyOf(methodHandlers);
    this.abstractMethodHandler = abstractMethodHandler;
  }

  @Override
  public String className() {
    return proxyClassName;
  }

  @Override
  public Class<T> type() {
    return type;
  }

  @Override
  public List<Class<?>> additionalInterfaces() {
    return additionalInterfaces;
  }

  @Override
  public Map<Method, MethodHandler> methodHandlers() {
    return methodHandlers;
  }

  @Override
  public Optional<CommonMethodHandler> abstractMethodHandler() {
    return Optional.ofNullable(abstractMethodHandler);
  }
}
