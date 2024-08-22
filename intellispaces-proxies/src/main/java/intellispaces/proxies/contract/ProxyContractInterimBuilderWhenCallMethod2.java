package intellispaces.proxies.contract;

import java.lang.reflect.Method;
import java.util.function.Function;
import java.util.function.Supplier;

public class ProxyContractInterimBuilderWhenCallMethod2<T, A, R> {
  private final ProxyContractBuilder<T> builder;
  private final Method method;

  ProxyContractInterimBuilderWhenCallMethod2(ProxyContractBuilder<T> builder, Method method) {
    this.builder = builder;
    this.method = method;
  }

  public ProxyContractBuilder<T> then(Supplier<R> supplier) {
    MethodHandler methodHandler = (object, arguments) -> supplier.get();
    builder.addMethodHandler(method, methodHandler);
    return builder;
  }

  @SuppressWarnings("unchecked")
  public ProxyContractBuilder<T> then(Function<A, R> function) {
    MethodHandler methodHandler = (object, arguments) -> function.apply((A) arguments[0]);
    builder.addMethodHandler(method, methodHandler);
    return builder;
  }
}
