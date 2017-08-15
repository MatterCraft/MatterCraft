package nl.kingdev.mattercraft.reflection;

import java.lang.reflect.Field;

/**
 * Created by Jasper on 15-8-2017.
 */
public class ReflectionHelper {


    public static ReflectionHelper instance = new ReflectionHelper();


    public Field getFieldFromClass(Class<?> clazz, String fieldName) throws ExeptionFieldNotFound {
        Field[] classFields = clazz.getFields();
        boolean found = false;
        Field toReturn = classFields[0];

        for(Field f : classFields) {
            if(f.getName().equals(fieldName)) {
                toReturn = f;
                found = true;
                break;
            }
        }

        if(!found) {
            throw new ExeptionFieldNotFound(fieldName);
        }
    return toReturn;
    }

}
