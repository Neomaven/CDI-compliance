package org.jboss.weld.compliance.impl.scenarios.interceptor.util;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * Used by the InterceptorScenarioReporter.
 * @author Matthieu Clochard
 */
@Interceptor
@Interception(true)
public class Interceptor11 {

    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception {
        if (ctx.getMethod().getName().equals("intercept1")) {
            InterceptedClass target = (InterceptedClass) ctx.getTarget();
            target.addState(0x00000001);
        }
        return ctx.proceed();
    }

}
