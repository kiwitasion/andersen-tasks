package classLoader;

import java.io.*;
import java.net.URL;
import java.util.HashMap;

public class SpecialClassLoader extends ClassLoader {

    private final HashMap<String, Class<?>> classesCache = new HashMap<>();

    public final String[] classPath;

    public SpecialClassLoader(String[] classPath) {
        this.classPath = classPath;
    }

    @Override
    protected synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> result = findClass(name);
        if (resolve)
            resolveClass(result);
        return result;

    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> result = classesCache.get(name);
        if (result != null) {
            return result;
        }
        File f = findFile(name.replace('.', '/'), ".class");
        if (f == null) {
            return findSystemClass(name);
        }
        try {
            byte[] classBytes = loadFileAsBytes(f);
            result = defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Can not load class " + name + ": " + e);
        }
        classesCache.put(name, result);
        return result;
    }

    @Override
    protected URL findResource(String name) {
        File f = findFile(name, "");
        if (f == null)
            return null;
        try{
            return f.toURI().toURL();
        } catch (java.net.MalformedURLException e) {
            return null;
        }
    }

    private File findFile(String name, String extension) {
        File f;
        for (String s : classPath) {
            f = new File((new File(s)).getPath()
                    + File.separatorChar
                    + name.replace('/',
                    File.separatorChar)
                    + extension);
            if (f.exists())
                return f;
        }
        return null;
    }

    public byte[] loadFileAsBytes(File file) throws IOException{
        byte[] result = new byte[(int) file.length()];

        try (FileInputStream f = new FileInputStream(file)){
            f.read(result, 0, result.length);
        } catch (Exception ignored){}

        return result;
    }
}
