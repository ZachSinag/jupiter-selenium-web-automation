package jupiter.utilities;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;

public class Log {

    private static org.apache.logging.log4j.Logger log = LogManager.getLogger();

    public static synchronized void startTest(String testCaseName) {
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        File file = new File("./src/test/resources/configFiles/log4j2.xml");
        context.setConfigLocation(file.toURI());
        info("\n\n************** Execution Started : " + testCaseName + " **************\n");
    }


    public static void endTest(String testCaseName) {
        info("\n\n************** Execution End : " + testCaseName + " **************\n");
    }

    public static org.apache.logging.log4j.Logger getCurrentLog() {
        return log;
    }

    public static String getCallInfo() {

        String callInfo;
        String className = Thread.currentThread().getStackTrace()[3].getClassName();
        String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
        int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();

        callInfo = className + ":" + methodName + " " + "(" + lineNumber + ")" + " --> ";
        return callInfo;

    }

    public static void error(Object message) {

        getCurrentLog().error(getCallInfo() + message);
    }

    public static void info(Object message) {

        getCurrentLog().info(getCallInfo() + message);
    }



}
