package org.gsafe.Stories;

import java.util.Locale;

import org.gsafe.step.DetruireSteps;

public class DetruireStories extends LocalizedStories {

    @Override
    protected Locale locale() {
        return new Locale("fr");
    }

    @Override
    protected String storyPattern() {
        return "**/detruire.histoire";
    }

    @Override
    protected Object localizedSteps() {
        return new DetruireSteps();
    }

}
