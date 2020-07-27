package com.nebula.nebula.basis;

import org.springframework.beans.BeanUtils;
import sun.misc.Unsafe;

import java.io.*;
import java.lang.reflect.Field;

/**
 * <p>
 *  Java中创建对象的方式
 * </p>
 * @author: zhu.chen
 * @date: 2020/7/27
 * @version: v1.0.0
 */
public class CreateObjectTest {

    public static void main(String[] args) throws CloneNotSupportedException, IllegalAccessException, InstantiationException, IOException, ClassNotFoundException {
        // 1：通过new创建对象
        Person newPerson = new Person("zhangsan", 1);
        System.out.println(newPerson);
        // 2：通过clone()：属性需要实现Cloneable接口，并重写Object类的clone()。
        Person clonePerson = (Person) newPerson.clone();
        System.out.println(clonePerson);
        // 3：通过序列化和反序列化：需要实现序列化接口Serializable
        Person serializablePerson = serializablePerson();
        System.out.println(serializablePerson);
        // 4：通过反射创建对象：通过newInstance()来创建时，必须有无参构造函数。
        Class clazz = Person.class;
        Person reflectionPerson = (Person) clazz.newInstance();
        reflectionPerson.setAge(2);
        reflectionPerson.setName("xiaoming");
        System.out.println(reflectionPerson);
        // 5：Unsafe.allocateInstance
        Person unsafePerson = (Person) unsafe.allocateInstance(Person.class);
        unsafePerson.setName("xiaobai");
        unsafePerson.setAge(5);
        System.out.println(unsafePerson);
        // 6：BeanUtils的拷贝
        Person beanPerson = new Person();
        BeanUtils.copyProperties(newPerson, beanPerson);
        System.out.println(beanPerson);
    }

    /**
     * 获取unsafe类
     */

    static Unsafe unsafe;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 序列化对象
     *
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private static Person serializablePerson() throws IOException, ClassNotFoundException {
        /**
         * 网络传输和磁盘IO会存在序列化对象来进行传输和存储。
         * 适用场景：IO(网络IO、磁盘IO)
         */
        // 序列化
        Person person = new Person("xiaohong", 4);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.txt"));
        oos.writeObject(person);
        // 反序列化
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.txt"));
        return (Person) ois.readObject();
    }

    private static class Person implements Cloneable, Serializable {

        private static final long serialVersionUID = -5463972443328267182L;

        private String name;

        private int age;

        public Person() {
        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
}
