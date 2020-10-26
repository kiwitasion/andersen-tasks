package classLoader;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Test {

    public static void main(String[] args) throws Exception {
        ClassLoader loader;
        while (true) {
            loader = new SpecialClassLoader(new String[]{"."});
            Class<?> clazz = Class.forName("TestModule", true, loader);
            Object object = clazz.getConstructor().newInstance();
            System.out.println(object);
            new BufferedReader(
                    new InputStreamReader(System.in)).readLine();
        }
    }
}
