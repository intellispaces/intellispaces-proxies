package intellispaces.proxies.contract;

@FunctionalInterface
public interface MethodHandler {

  Object handle(Object object, Object[] arguments) throws Exception;
}
