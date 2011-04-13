package org.jboss.weld.compliance.impl.scenarios.interceptor.tests;

import javax.inject.Inject;
import org.jboss.weld.compliance.exception.ComplianceException;
import org.jboss.weld.compliance.impl.AbstractTest;
import org.jboss.weld.compliance.impl.scenarios.interceptor.util.InterceptedClass;

/**
 * This test occurs right after intercepted class injection. Verifies if the
 * injection went right : injected class exists and had the proper state.
 * Should be run before ClassInterceptionTest and MethodInterceptionTest.
 * @author Matthieu Clochard
 */
public class InitializationTest extends AbstractTest {

    private InterceptedClass interceptedClass;

    @Inject
    public InitializationTest(InterceptedClass interceptedClass) {
        this.interceptedClass = interceptedClass;
    }

    @Override
    public void run() throws ComplianceException {
        int expectedState = 0x00000000;
        int currentState = this.interceptedClass.getState();
        if(this.interceptedClass == null) {
            throw new ComplianceException("error during injection : the injected"
                    + " class was null (check your producer compliance)");
        }
        if(currentState != expectedState) {
            throw new ComplianceException("error during injection : the injected "
                    + "class state was not correctly initialized (perhaps "
                    + "interceptor was called too soon)");
        }
    }

}
