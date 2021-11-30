package com.my.javafeatures.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class SingletonFactory {

    /**
     * Static field
     * + easy realization
     * + thread-safety
     * - not lazy initialization
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public class StaticFieldSingleton {
        public static final StaticFieldSingleton INSTANCE = new SingletonFactory().new StaticFieldSingleton();
    }

    /**
     * Enum
     * + funny
     * + thread-safety
     * - not lazy initialization
     */
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public enum EnumSingleton {
        INSTANCE(1L, "SOME");
        private Long id;
        private String name;
    }

    /**
     * Synchronized Accessor
     * + lazy initialization
     * - low performance
     */
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class SynchronizedAccessorSingleton {
        private static SynchronizedAccessorSingleton instance;

        public static synchronized SynchronizedAccessorSingleton getInstance() {
            if (instance == null) {
                instance = new SynchronizedAccessorSingleton();
            }
            return instance;
        }
    }

    /**
     * Double-checked locking & volatile
     * + lazy initialization
     * + high performance
     * - since JDK 1.5
     */
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class DCLSingleton {
        private static volatile DCLSingleton instance;

        public static DCLSingleton getInstance() {
            DCLSingleton localInstance = instance;
            if (localInstance == null) {
                synchronized (DCLSingleton.class) {
                    if (localInstance == null) {
                        instance = new DCLSingleton();
                    }
                }
            }
            return instance;
        }
    }

    /**
     * On Demand Holder
     * + lazy initialization
     * + high performance
     * - can't use for non-static class fields
     */
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class HolderSingleton {
        public static class SingletonHolder {
            public static final HolderSingleton HOLDER_INSTANCE = new HolderSingleton();
        }

        public static HolderSingleton getInstance() {
            return SingletonHolder.HOLDER_INSTANCE;
        }
    }

}
