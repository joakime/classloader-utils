package org.example;

import java.net.URL;
import java.util.Enumeration;

public class ClassLoaderUtil
{
    public void dump()
    {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        System.out.printf("Thread.current.contextClassLoader = %s%n", cl);
        String indent = "  ";
        ClassLoader parent = cl.getParent();
        while (parent != null)
        {
            System.out.printf("%s.parent = %s%n", indent, parent);
            parent = parent.getParent();
            indent += "  ";
        }
    }

    public void locate(String name)
    {
        locate(Thread.currentThread().getContextClassLoader(), name);
    }

    public void locate(ClassLoader cl, String resourceName)
    {
        try
        {
            Enumeration<URL> urls = cl.getResources(resourceName);
            System.out.printf("Looking for: %s%n", resourceName);
            int count = 0;
            while (urls.hasMoreElements())
            {
                URL url = urls.nextElement();
                System.out.printf(" - Found: %s%n", url.toExternalForm());
                count++;
            }
            System.out.printf(" Found %d times%n", count);
        }
        catch (Throwable t)
        {
            System.out.printf("Whoops: cannot locate: %s%n", resourceName);
            t.printStackTrace();
        }
    }
}
