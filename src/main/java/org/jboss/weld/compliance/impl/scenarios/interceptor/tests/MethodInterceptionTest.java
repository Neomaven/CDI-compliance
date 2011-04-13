package org.jboss.weld.compliance.impl.scenarios.interceptor.tests;

import javax.inject.Inject;
import org.jboss.weld.compliance.exception.ComplianceException;
import org.jboss.weld.compliance.impl.AbstractTest;
import org.jboss.weld.compliance.impl.scenarios.interceptor.util.InterceptedClass;

/**
 * This test occurs after the ClassInterceptionTest. Verifies if the method
 * interception went well.
 * Should be run after InitializationTest and ClassInterceptionTest.
 * @author Matthieu Clochard
 */
public class MethodInterceptionTest extends AbstractTest {

    private InterceptedClass interceptedClass;

    @Inject
    public MethodInterceptionTest(InterceptedClass interceptedClass) {
        this.interceptedClass = interceptedClass;
    }

    @Override
    public void run() throws ComplianceException {
        int expectedState = 0x11111111;
        int returnState = interceptedClass.intercept2();
        int currentState = interceptedClass.getState();
        if(expectedState != currentState) {
            if(currentState == 0x01111111) {
                throw new ComplianceException("error after method interception "
                        + ": the business method never called");
            }
            if(currentState == 0x10001111) {
                throw new ComplianceException("error during method interception"
                        + " : the interceptors never called (none of them)");
            }
            if(currentState == 0x00001111) {
                throw new ComplianceException("error during method interception"
                        + " : neither interceptors or business method was "
                        + "called");
            }
            if((currentState & 0x00001111) != 0x00001111) {
                throw new ComplianceException("error during method interception"
                        + " : wrong interceptors or business method was"
                        + " calledd");
            }
            throw new ComplianceException("error during method interception : "
                    + "not all interceptors or business method was calledd");
        }
        if(currentState != returnState) {
            throw new ComplianceException("error after method interception : "
                    + "the result of the bussiness method call was wrong");
        }
        interceptedClass.setState(0);
    }
}
