package com.santosh.miniredditapp.di.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Qualifier
@Retention(RUNTIME)
public @interface ForApplication {
}
