package com.foxety0f.proton.common.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;

import com.foxety0f.proton.modules.ProtonModules;

@Documented
@java.lang.annotation.Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = { java.lang.annotation.ElementType.CONSTRUCTOR, java.lang.annotation.ElementType.FIELD,
		java.lang.annotation.ElementType.LOCAL_VARIABLE, java.lang.annotation.ElementType.METHOD,
		java.lang.annotation.ElementType.PACKAGE, java.lang.annotation.ElementType.PARAMETER,
		java.lang.annotation.ElementType.TYPE })

public @interface PageAnnotation {
	
	String[] value();
	
	ProtonModules module();

}
