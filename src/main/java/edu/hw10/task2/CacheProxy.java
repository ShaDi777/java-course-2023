package edu.hw10.task2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;

public final class CacheProxy implements AutoCloseable {
    private CacheHandler handler;

    private CacheProxy() {
    }

    public static <T> T create(T instance, Class<? extends T> instanceClass, Path cacheDir) {
        return (T) Proxy.newProxyInstance(
            instanceClass.getClassLoader(),
            instanceClass.getInterfaces(),
            new CacheHandler(instance, cacheDir)
        );
    }

    @Override
    public void close() throws Exception {

    }

    private static final class CacheHandler implements InvocationHandler {
        private final Object original;
        private final Path cacheDir;
        private final Map<String, HashMap<List<Object>, Object>> cachedRuntime = new HashMap<>();

        private CacheHandler(Object original, Path cacheDir) {
            this.original = original;
            this.cacheDir = cacheDir;

            for (var method : original.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(Cache.class)) {
                    cachedRuntime.putIfAbsent(method.getName(), new HashMap<>());
                }
            }
            loadFromDisk();
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] argsArray) throws Throwable {
            method.setAccessible(true);

            List<Object> args = Arrays.stream(argsArray).toList();
            if (method.isAnnotationPresent(Cache.class)) {
                var cachedResults = cachedRuntime.get(method.getName());

                if (cachedResults.containsKey(args)) {
                    return cachedResults.get(args);
                }

                Object result = method.invoke(original, argsArray);
                cachedResults.put(args, result);

                // TODO fix this
                saveOnDisk();

                return result;
            }

            return method.invoke(original, argsArray);
        }

        private void saveOnDisk() throws IOException {
            Map<String, HashMap<List<Object>, Object>> cachedPersist = new HashMap<>();
            for (var method : original.getClass().getDeclaredMethods()) {
                var persistAnnotation = method.getAnnotation(Cache.class);
                if (persistAnnotation != null && persistAnnotation.persist()) {
                    cachedPersist.put(method.getName(), cachedRuntime.get(method.getName()));
                }
            }

            if (!cachedPersist.isEmpty()) {
                FileOutputStream fos = new FileOutputStream(getMapName());
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(cachedPersist);
                oos.close();
            }
        }

        private void loadFromDisk() {
            try {
                FileInputStream fis = new FileInputStream(getMapName());
                ObjectInputStream ois = new ObjectInputStream(fis);
                var cachedPersist = (Map<String, HashMap<List<Object>, Object>>) ois.readObject();
                cachedRuntime.putAll(cachedPersist);
                ois.close();
            } catch (IOException ignore) {
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        private String getMapName() {
            String name = cacheDir.resolve(
                original.getClass().getName() + original.hashCode() + ".map"
                ).toString();
            LogManager.getLogger().trace(name);
            return name;
        }
    }
}
