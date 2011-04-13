package org.jboss.weld.compliance.impl.scenarios.interceptor.tests;

import javax.inject.Inject;
import org.jboss.weld.compliance.exception.ComplianceException;
import org.jboss.weld.compliance.impl.AbstractTest;
import org.jboss.weld.compliance.impl.scenarios.interceptor.util.InterceptedClass;

/**
 * This test occurs after the InitializationTest. Verifies if the class
 * interception went well.
 * Should be run after InitializationTest and before MethodInterceptionTest.
 * @author Matthieu Clochard
 */
public class ClassInterceptionTest extends AbstractTest {

    private InterceptedClass interceptedClass;

    @Inject
    public ClassInterceptionTest(InterceptedClass interceptedClass) {
        this.interceptedClass = interceptedClass;
    }

    @Override
    public void run() throws ComplianceException {
        int expectedState = 0x00001111;
        int returnState = interceptedClass.intercept1();
        int currentState = interceptedClass.getState();
        if(expectedState != currentState) {
            if(currentState == 0x00000111) {
                throw new ComplianceException("error after class interception :"
                        + " the business method never called");
            }
            if(currentState == 0x00001000) {
                throw new ComplianceException("error during class interception "
                        + ": the interceptors never called (none of them)");
            }
            if(currentState == 0x00000000) {
                throw new ComplianceException("error during class interception "
                        + ": neither interceptors or business method was "
                        + "called");
            }
            if((currentState & 0x11110000) != 0x00000000) {
                throw new ComplianceException("error during class interception "
                        + ": wrong interceptors or business method was "
                        + "calledd");
            }
            throw new ComplianceException("error during class interception : "
                    + "not all interceptors or business method was calledd");
        }
        if(currentState != returnState) {
            throw new ComplianceException("error after class interception : the"
                    + " result of the bussiness method call was wrong");
        }
    }
}
