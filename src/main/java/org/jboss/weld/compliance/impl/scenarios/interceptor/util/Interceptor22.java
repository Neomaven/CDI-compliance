package org.jboss.weld.compliance.impl.scenarios.interceptor.util;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * Used by the InterceptorScenarioReporter.
 * @author Matthieu Clochard
 */
@Interceptor
@Interception(false)
public class Interceptor22 {

    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception {
        InterceptedClass target = (InterceptedClass) ctx.getTarget();
        target.addState(0x00100000);
        return ctx.proceed();
    }

}
