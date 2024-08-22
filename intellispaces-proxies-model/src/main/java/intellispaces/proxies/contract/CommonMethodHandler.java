package intellispaces.proxies.contract;

import java.lang.reflect.Method;

@FunctionalInterface
public interface CommonMethodHandler {

  Object handle(Object object, Method method, Object[] arguments) throws Exception;
}
