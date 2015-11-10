package org.gsafe.Stories;

import java.util.Locale;

import org.gsafe.step.LireSteps;
import org.gsafe.step.MetadataSteps;

public class MetadataStories extends LocalizedStories {

    @Override
    protected Locale locale() {
        return new Locale("fr");
    }

    @Override
    protected String storyPattern() {
        return "**/metadata.histoire";
    }

    @Override
    protected Object localizedSteps() {
        return new MetadataSteps();
    }

}
