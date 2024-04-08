package intellispaces.dynamicproxy.factory;

public interface ProxyFactory {

  <T> Class<T> createWatcherClass(Class<T> aClass);
}
