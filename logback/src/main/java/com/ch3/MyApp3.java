package com.ch3;
/**
 * Demonstrates programmatic invocation of Joran.
 *
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;

public class MyApp3 {
    final static Logger logger = LoggerFactory.getLogger(MyApp3.class);

    public static void main(String[] args) {
        System.out.println(MyApp3.class);
        // assume SLF4J is bound to logback in the current environment
//        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
//        try {
//            JoranConfigurator configurator = new JoranConfigurator();
//            configurator.setContext(lc);
//        // the context was probably already configured by default
//        // configuration rules
//            lc.reset();
//            configurator.doConfigure(args[0]);
//        } catch (JoranException je) {
//        // StatusPrinter will handle this
//        }
//        StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
        logger.info("Entering application.");

        logger.info("Exiting application.");

        logger.debug("I am debug.");
    }
}