package com.sean.a28_lib_processor;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;

public class BindProcessor extends AbstractProcessor{

    @Override
    public synchronized void init(ProcessingEnvironment environment) {
        super.init(environment);
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment environment) {
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {

        return super.getSupportedAnnotationTypes();
    }
}
