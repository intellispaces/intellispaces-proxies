package tech.intellispaces.commons.proxy.contract;

@FunctionalInterface
public interface MethodHandler {

  Object handle(Object object, Object[] arguments) throws Exception;
}
