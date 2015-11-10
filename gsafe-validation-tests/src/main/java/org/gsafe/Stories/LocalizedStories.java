package org.gsafe.Stories;

import org.gsafe.step.CompterSteps;
import org.gsafe.step.ControlerSteps;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.Keywords;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.model.ExamplesTableFactory;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.FilePrintStreamFactory;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.*;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.TXT;

public abstract class LocalizedStories extends JUnitStories {
    @Override
    public Configuration configuration() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL codeLocation = CodeLocations.codeLocationFromClass(this.getClass());
        Keywords keywords = new LocalizedKeywords(locale());
        Properties properties = new Properties();
        properties.setProperty("reports", "ftl/jbehave-reports.ftl");
        properties.setProperty("encoding", "UTF-8");
        Configuration configuration = new MostUsefulConfiguration()
                .useKeywords(keywords)
                .useStepCollector(new MarkUnmatchedStepsAsPending(keywords))
                .useStoryParser(new RegexStoryParser(keywords))
                .useStoryLoader(
                        new LoadFromClasspath(classLoader))
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withCodeLocation(codeLocation)
                        .withPathResolver(new FilePrintStreamFactory.ResolveToSimpleName())
                        .withDefaultFormats()
                        .withFormats(CONSOLE, TXT)
                        .withFailureTrace(false)
                        .withViewResources(properties)
                        .withKeywords(keywords))
                .useParameterConverters(
                        new ParameterConverters().addConverters(customConverters(keywords)));
        return configuration;
    }

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(codeLocationFromClass(this.getClass()), storyPattern(), "");
    }

    @Override
    public List<CandidateSteps> candidateSteps() {
        return new InstanceStepsFactory(configuration(), localizedSteps())
                .createCandidateSteps();
    }

    private ParameterConverters.ParameterConverter[] customConverters(Keywords keywords) {
        List<ParameterConverters.ParameterConverter> converters = new ArrayList<ParameterConverters.ParameterConverter>();
        converters.add(new ParameterConverters.NumberConverter(NumberFormat.getInstance(locale())));
        converters.add(new ParameterConverters.ExamplesTableConverter(new ExamplesTableFactory(keywords)));
        return converters.toArray(new ParameterConverters.ParameterConverter[converters.size()]);
    }

    protected abstract Locale locale();

    protected abstract String storyPattern();

    protected abstract Object localizedSteps();
}
