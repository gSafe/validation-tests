package org.gsafe.Stories;

import java.util.Locale;

import org.gsafe.step.CompterSteps;

public class CompterStories extends LocalizedStories {

    @Override
    protected Locale locale() {
        return new Locale("fr");
    }

    @Override
    protected String storyPattern() {
        return "**/compter.histoire";
    }

    @Override
    protected Object localizedSteps() {
        return new CompterSteps();
    }

}
