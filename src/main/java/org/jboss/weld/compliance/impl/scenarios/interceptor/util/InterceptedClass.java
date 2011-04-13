package org.jboss.weld.compliance.impl.scenarios.interceptor.util;

import javax.enterprise.context.ApplicationScoped;

/**
 * Used by the InterceptorScenarioReporter.
 * @author Matthieu Clochard
 */
@ApplicationScoped
@Interception(true)
public class InterceptedClass {

    private int state;

    public InterceptedClass() {
        state = 0x00000000;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void addState(int state) {
        int newState = this.state | state;
        if(newState != this.state) {
            this.state = newState;
        }
        else {
            this.state &= -state;
        }
    }

    public int intercept1() {
        addState(0x00001000);
        return state;
    }

    @Interception(false)
    public int intercept2() {
        addState(0x10000000);
        return state;
    }

}
