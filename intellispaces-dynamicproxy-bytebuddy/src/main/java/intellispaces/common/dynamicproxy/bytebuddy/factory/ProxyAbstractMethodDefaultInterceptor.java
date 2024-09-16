package intellispaces.common.dynamicproxy.bytebuddy.factory;

import intellispaces.common.base.exception.UnexpectedViolationException;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;

import java.lang.reflect.Method;

public class ProxyAbstractMethodDefaultInterceptor {

  ProxyAbstractMethodDefaultInterceptor() {}

  @RuntimeType
  public Object intercept(@Origin Method method) {
    throw UnexpectedViolationException.withMessage("Interceptor of abstract proxy method ''{0}'' is not defined. " +
            "Class {1}", method.getName(), method.getDeclaringClass().getCanonicalName());
  }
}
