package com.my.javafeatures.common.annotation;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;
import org.springframework.lang.NonNull;

public class AnnotationTest {

    @Test
    void annotationProcessorTest() {
        Person onePerson = new Person(1L, "One");
        assertThat(AnnotationProcessor.canBeProcessed(onePerson), is(Boolean.TRUE));

        Person zeroPerson = new Person(0L, "zero");
        assertThrows(IllegalArgumentException.class, () -> AnnotationProcessor.canBeProcessed(zeroPerson));

        User oneUser = new User(1L, "one");
        assertThat(AnnotationProcessor.canBeProcessed(oneUser), is(Boolean.FALSE));
    }


    private static final class AnnotationProcessor {

        private static final List<String> PROCESSED_ENT_NAMES = List.of("person", "user", "account");

        @FunctionalInterface
        private interface NotZeroValue {
            boolean isFieldZero(Field field, Object instance);
        }

        static boolean canBeProcessed(Object entity) {
            if (entity == null) {
                return false;
            }

            Class<?> entityClass = entity.getClass();
            ProcessedEntity processedEntityAnnotation = entityClass.getAnnotation(ProcessedEntity.class);
            if (processedEntityAnnotation != null) {
                boolean contains = PROCESSED_ENT_NAMES.contains(processedEntityAnnotation.value());
                checkNotZeroValues(entity);
                return contains;
            } else {
                return false;
            }
        }

        static private void checkNotZeroValues(@NonNull Object instance) {
            Field[] entityFields = instance.getClass().getDeclaredFields();
            if (ArrayUtils.isNotEmpty(entityFields)) {
                List<Field> emptyRequiredFields = Arrays.stream(entityFields)
                        .filter(field -> field.isAnnotationPresent(NotZero.class))
                        .filter(field -> ((NotZeroValue) (fld, inst) -> {
                            try {
                                Object instValue = fld.get(inst);
                                return instValue instanceof Long && ((Long) instValue == 0L);
                            } catch (Exception exception) {
                                System.err.println("Internal error");
                                return false;
                            }
                        }).isFieldZero(field, instance))
                        .collect(Collectors.toList());
                if (!emptyRequiredFields.isEmpty()) {
                    throw new IllegalArgumentException(String.format("Empty fields: %s", emptyRequiredFields));
                }
            }
        }
    }

    @ProcessedEntity("person")
    private record Person(@NotZero Long id, String name) {
    }

    @ProcessedEntity("any")
    private record User(long id, String name) {

    }
}
