package com.ch3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhangxin.zhang created on 16-4-29.
 */
public class MainTest {
    final static Logger logger = LoggerFactory.getLogger(MainTest.class);

    public static void main(String[] args) {

        System.out.println(MainTest.class);
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
