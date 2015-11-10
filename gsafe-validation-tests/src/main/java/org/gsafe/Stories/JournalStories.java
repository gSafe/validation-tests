package org.gsafe.Stories;

import java.util.Locale;

import org.gsafe.step.JournalSteps;
import org.gsafe.step.LireSteps;

public class JournalStories extends LocalizedStories {

    @Override
    protected Locale locale() {
        return new Locale("fr");
    }

    @Override
    protected String storyPattern() {
        return "**/journal.histoire";
    }

    @Override
    protected Object localizedSteps() {
        return new JournalSteps();
    }

}
