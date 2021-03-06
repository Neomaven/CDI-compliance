package org.jboss.weld.compliance.impl.scenarios.producer.methodproducer;

import javax.inject.Inject;
import org.jboss.weld.compliance.exception.ComplianceException;
import org.jboss.weld.compliance.impl.AbstractTest;
import org.jboss.weld.compliance.impl.scenarios.producer.util.MethodProducedClass;
import org.jboss.weld.compliance.impl.scenarios.producer.util.MultiQualified1;
import org.jboss.weld.compliance.impl.scenarios.producer.util.MultiQualified2;

/**
 * Test the compliance of multiqualified field producer. Try to inject a
 * multiqualified field using a producer method of an external class.
 * @author Matthieu Clochard
 */
public class MultiQualifiedMethodProducerTest extends AbstractTest {

    @Inject
    @MultiQualified1
    @MultiQualified2
    private MethodProducedClass fieldProduced;

    @Override
    public void run() throws ComplianceException {
        if(fieldProduced == null) {
            throw new ComplianceException("the injected value was null (not produced)");
        }
        if(!fieldProduced.getName().equals("MultiQualifiedMethodProducedClass")) {
            throw new ComplianceException("the injected value was wrong (produced elsewhere)");
        }
    }

}
