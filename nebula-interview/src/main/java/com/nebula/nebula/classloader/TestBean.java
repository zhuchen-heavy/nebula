package com.nebula.nebula.classloader;

import org.springframework.core.OverridingClassLoader;

/**
 * <p>
 * spring中的类加载机制
 * </p>
 *
 * @author: zhu.chen
 * @date: 2020/8/3
 * @version: v1.0.0
 */
public class TestBean {

    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader appClassLoader = Thread.currentThread().getContextClassLoader();
        /**
         * 添加到 excludedPackages 或 excludedClasses 的类就不会被代理的 ClassLoader 加载，而会使用 JDK 默认的双亲委派机制
         * 因此 TestBean 不会被 OverridingClassLoader 重新加载，而 ITestBean 会重新加载
         *
         *  // 源码这里可以看到  ---> 重写了ClassLoader#loadClass(String name, boolean resolve)
         *  @Override
         * 	protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
         * 		if (isEligibleForOverriding(name)) {
         * 			Class<?> result = loadClassForOverriding(name);
         * 			if (result != null) {
         * 				if (resolve) {
         * 					resolveClass(result);
         * 				}
         * 				return result;
         * 			}
         * 		}
         * 		return super.loadClass(name, resolve);
         * 	}
         *
         *
         */
        OverridingClassLoader overridingClassLoader = new OverridingClassLoader(appClassLoader);
        overridingClassLoader.excludeClass(TestBean.class.getName());

        Class<?> excludedClazz1 = appClassLoader.loadClass(TestBean.class.getName());
        Class<?> excludedClazz2 = overridingClassLoader.loadClass(TestBean.class.getName());
        // true
        System.out.println(excludedClazz1 == excludedClazz2);
        Class<?> nonExcludedClazz1 = appClassLoader.loadClass(ITestBean.class.getName());
        Class<?> nonExcludedClazz2 = overridingClassLoader.loadClass(ITestBean.class.getName());
        // false
        System.out.println(nonExcludedClazz1 == nonExcludedClazz2);
    }

    public static class ITestBean {
    }

}
