package serializationUtils;

import serializationInterfaces.JsonProperty;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * @author Bulgakov
 * @since 20.01.2016
 */
public class WorkWithModifiersOfFields {
    /**
     * Method removes from list all fields with transient modifier and without annotation {@link serializationInterfaces.JsonProperty}.
     * If field have annotation {@link serializationInterfaces.JsonProperty}, it didn't removed from list.
     *
     * @param fields ArrayList of fields for checking on transient modifier
     * @return  list without fields with transient modifier.
     */
    public static ArrayList<Field> removeTransientFields(ArrayList<Field> fields) {
        for (int i = 0; i < fields.size(); i++) {
            if(Modifier.isTransient(fields.get(i).getModifiers()) && !(fields.get(i).isAnnotationPresent(JsonProperty.class))){
                fields.remove(i);
            }
        }
        return  fields;
    }

    /**
     * Method removes all fields without annotation from param from list.
     *
     * @param fields ArrayList of fields for checking
     * @param annotationName name of annotation
     * @return list of fields with annotation
     */
    public static ArrayList<Field> removeFieldsWithoutAnnotation(ArrayList<Field> fields, String annotationName) {
        if(annotationName == null){
            throw new IllegalArgumentException("Null");
        }
        for(int i = 0; i < fields.size(); i++){
            Annotation[] arrayAnnotations = fields.get(i).getAnnotations();
            for (Annotation annotation : arrayAnnotations) {
                if(!annotation.annotationType().getSimpleName().equals(annotationName)){
                    fields.remove(i);
                    continue;
                }
            }
        }
        return fields;
    }

    /**
     * Method removes all fields with annotation from param from list.
     *
     * @param fields ArrayList of fields for checking
     * @param annotationName name of annotation
     * @return list of fields without annotation
     */
    public static ArrayList<Field> removeFieldsWithAnnotation(ArrayList<Field> fields,String annotationName){
        if(annotationName == null){
            throw new IllegalArgumentException("Null");
        }
        for(int i = 0; i < fields.size(); i++){
            Annotation[] arrayAnnotations = fields.get(i).getAnnotations();
            for (Annotation annotation : arrayAnnotations) {
                if(annotation.annotationType().getSimpleName().equals(annotationName)){
                    fields.remove(i);
                    continue;
                }
            }
        }
        return fields;
    }

    /**
     * Method gets all public fields of class from param.
     *
     * @param currentClass class with fields
     * @return list of public fields of class from param.
     */
    public static ArrayList<Field> getPublicFieldsOfClass(Class currentClass) {
        ArrayList<Field> publicFields = new ArrayList<Field>();
        for (Field field : currentClass.getDeclaredFields()) {
            if (Modifier.isPublic(field.getModifiers())) {
                publicFields.add(field);
            }
        }
        return publicFields;
    }

    /**
     * Method gets all not public fields of class from param.
     *
     * @param currentClass class with fields
     * @return list of not public fields of class from param.
     */
    public static ArrayList<Field> getNotPublicFieldsOfClass(Class currentClass) {
        ArrayList<Field> notPublicFields = new ArrayList<Field>();
        for (Field field : currentClass.getDeclaredFields()) {
            if (!Modifier.isPublic(field.getModifiers())) {
                notPublicFields.add(field);
            }
        }
        return notPublicFields;
    }
}
