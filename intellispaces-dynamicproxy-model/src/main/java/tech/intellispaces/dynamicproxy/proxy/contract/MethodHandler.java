package tech.intellispaces.dynamicproxy.proxy.contract;

@FunctionalInterface
public interface MethodHandler {

  Object handle(Object object, Object[] arguments) throws Exception;
}
