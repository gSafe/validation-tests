package org.gsafe.Stories;

import java.util.Locale;

import org.gsafe.step.LireSteps;
import org.gsafe.step.ListerSteps;

public class ListerStories extends LocalizedStories {

    @Override
    protected Locale locale() {
        return new Locale("fr");
    }

    @Override
    protected String storyPattern() {
        return "**/lister.histoire";
    }

    @Override
    protected Object localizedSteps() {
        return new ListerSteps();
    }

}
