package org.gsafe.Stories;

import java.util.Locale;

import org.gsafe.step.DetruireSteps;
import org.gsafe.step.LireSteps;

public class LireStories extends LocalizedStories {

    @Override
    protected Locale locale() {
        return new Locale("fr");
    }

    @Override
    protected String storyPattern() {
        return "**/lire.histoire";
    }

    @Override
    protected Object localizedSteps() {
        return new LireSteps();
    }

}
