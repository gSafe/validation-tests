package org.gsafe.Stories;

import java.util.Locale;

import org.gsafe.step.ControlerSteps;
import org.gsafe.step.DeposerSteps;

public class DeposerStories extends LocalizedStories {

    @Override
    protected Locale locale() {
        return new Locale("fr");
    }

    @Override
    protected String storyPattern() {
        return "**/deposer.histoire";
    }

    @Override
    protected Object localizedSteps() {
        return new DeposerSteps();
    }

}
