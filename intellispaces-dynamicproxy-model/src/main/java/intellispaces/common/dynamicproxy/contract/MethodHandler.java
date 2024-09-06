package intellispaces.common.dynamicproxy.contract;

@FunctionalInterface
public interface MethodHandler {

  Object handle(Object object, Object[] arguments) throws Exception;
}
