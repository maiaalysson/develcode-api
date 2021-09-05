package com.gabrielmaia.develcode.domain.function;

import java.lang.reflect.Field;

import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
public class MergeObjects {

	public void objects(final Object source, final Object target) {
		ReflectionUtils.doWithFields(source.getClass(), new ReflectionUtils.FieldCallback() {
			@Override
			public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
				field.setAccessible(true);

				if (field.get(source) != null)
					field.set(target, field.get(source));
			}
		}, ReflectionUtils.COPYABLE_FIELDS);
	}
}