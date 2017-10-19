package com.santosh.miniredditapp.di.scope;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * This scope is used instead of @Singleton.
 * @Singleton scope implies, it is the singleton for the entire application like a normal java singleton.
 * It is only a singleton within the instance we create. It should be tied to the component.
 */
@Scope
@Retention(RetentionPolicy.CLASS)
public @interface ApplicationScope {
}
