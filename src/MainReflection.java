import ru.javawebinar.basejava.model.Resume;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume resume = new Resume();
        Field field = resume.getClass().getDeclaredFields()[0];
          field.setAccessible(true);
          System.out.println(field.get(resume));
          field.set(resume, "new uuid");
        System.out.println(field.get(resume));

        Method method = resume.getClass().getDeclaredMethod("toString");
        method.setAccessible(true);
        field.set(resume, "uuid");
        System.out.println(method.invoke(resume));
        System.out.println();





    }

}
