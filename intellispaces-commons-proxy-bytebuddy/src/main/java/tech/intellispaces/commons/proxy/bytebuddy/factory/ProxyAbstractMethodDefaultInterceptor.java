package tech.intellispaces.commons.proxy.bytebuddy.factory;

import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import tech.intellispaces.commons.exception.UnexpectedExceptions;

import java.lang.reflect.Method;

public class ProxyAbstractMethodDefaultInterceptor {

  ProxyAbstractMethodDefaultInterceptor() {}

  @RuntimeType
  public Object intercept(@Origin Method method) {
    throw UnexpectedExceptions.withMessage("Interceptor of abstract proxy method '{0}' is not defined. " +
            "Class {1}", method.getName(), method.getDeclaringClass().getCanonicalName());
  }
}
