package intellispaces.dynamicproxy.factory;

public interface DynamicProxyFactory {

  <T> Class<T> createTrackerClass(Class<T> aClass);
}
