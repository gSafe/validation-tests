package org.gsafe.Stories;

import java.util.Locale;

import org.gsafe.step.ControlerSteps;

public class ControlerStories extends LocalizedStories{

    @Override
    protected Locale locale() {
        return new Locale("fr");
    }

    @Override
    protected String storyPattern() {
        return "**/controler.histoire";
    }

    @Override
    protected Object localizedSteps() {
        return new ControlerSteps();
    }

}
