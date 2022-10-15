package org.openjdk.btrace.runtime;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class LinkingFlag {
  private static final Map<String, ThreadLocal<Boolean>> linkedFlags = new ConcurrentHashMap<>();

  public static boolean isLinked() {
    return isLinked(BTraceRuntimes.getCurrent().getClassName());
  }

  public static boolean isLinked(String className) {
    ThreadLocal<Boolean> flag = linkedFlags.get(className);
    if (flag != null) {
      return flag.get();
    }
    return false;
  }

  public static void setLinked(String className, boolean value) {
    linkedFlags
        .computeIfAbsent(className, k -> ThreadLocal.withInitial(() -> Boolean.FALSE))
        .set(value);
  }
}
