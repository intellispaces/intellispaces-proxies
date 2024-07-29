package tech.intellispaces.dynamicproxy.proxy.contract;

import java.lang.reflect.Method;
import java.util.function.Supplier;

public class ProxyContractInterimBuilderWhenCallMethod1<T, R> {
  private final ProxyContractBuilder<T> builder;
  private final Method method;

  ProxyContractInterimBuilderWhenCallMethod1(ProxyContractBuilder<T> builder, Method method) {
    this.builder = builder;
    this.method = method;
  }

  public ProxyContractBuilder<T> then(Supplier<R> supplier) {
    MethodHandler methodHandler = (object, arguments) -> supplier.get();
    builder.addMethodHandler(method, methodHandler);
    return builder;
  }
}
