package intellispaces.common.dynamicproxy.bytebuddy.factory;

import intellispaces.common.base.type.TypeFunctions;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.This;

import java.lang.reflect.Method;

public class TrackerMethodInterceptor {

  TrackerMethodInterceptor() {}

  @RuntimeType
  public Object intercept(@This TrackedObject trackedObject, @Origin Method method, @AllArguments Object[] arguments) {
    trackedObject.___tracker().addInvokedMethod(method);
    return TypeFunctions.getDefaultValueOf(method.getReturnType());
  }
}
