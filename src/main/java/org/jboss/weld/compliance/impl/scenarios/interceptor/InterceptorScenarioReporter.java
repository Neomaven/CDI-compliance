package org.jboss.weld.compliance.impl.scenarios.interceptor;

import java.util.ArrayList;
import javax.inject.Inject;
import org.jboss.weld.compliance.api.Test;
import org.jboss.weld.compliance.impl.AbstractScenarioReporter;
import org.jboss.weld.compliance.impl.scenarios.interceptor.tests.ClassInterceptionTest;
import org.jboss.weld.compliance.impl.scenarios.interceptor.tests.InitializationTest;
import org.jboss.weld.compliance.impl.scenarios.interceptor.tests.MethodInterceptionTest;

/**
 * Tries to inject a intercepted bean. Then calls a method on this bean that
 * should be intercepted three time in a row by three different interceptor
 * classes. Then calls another method, intercepted by three other different
 * interceptors classes.
 * This scenario ensures that the injected class is correct, then that
 * interceptors are called rightly :
 *     * detecting IntercpetorBinding well
 *     * intercepting both type and method InterceptorBindind
 *     * calling the business method at last
 * @author Matthieu Clochard
 */
public class InterceptorScenarioReporter extends AbstractScenarioReporter {

    @Inject
    public InterceptorScenarioReporter(
            InitializationTest initializationTest,
            ClassInterceptionTest classInterceptionTest,
            MethodInterceptionTest methodInterceptionTest) {
        tests = new ArrayList<Test>();
        tests.add(initializationTest);
        tests.add(classInterceptionTest);
        tests.add(methodInterceptionTest);
    }

}
