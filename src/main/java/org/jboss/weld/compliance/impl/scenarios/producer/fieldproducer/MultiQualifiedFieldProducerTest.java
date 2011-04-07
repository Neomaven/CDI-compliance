package org.jboss.weld.compliance.impl.scenarios.producer.fieldproducer;

import javax.inject.Inject;
import org.jboss.weld.compliance.api.Test;
import org.jboss.weld.compliance.exception.ComplianceException;
import org.jboss.weld.compliance.impl.scenarios.producer.util.FieldProducedClass;
import org.jboss.weld.compliance.impl.scenarios.producer.util.MultiQualified1;
import org.jboss.weld.compliance.impl.scenarios.producer.util.MultiQualified2;

/**
 * Test the compliance of multiqualified field producer. Try to inject a
 * multiqualified field using a producer field of an external class.
 * @author Matthieu Clochard
 */
public class MultiQualifiedFieldProducerTest implements Test {

    @Inject
    @MultiQualified1
    @MultiQualified2
    private FieldProducedClass fieldProduced;

    @Override
    public void run() throws ComplianceException {
        if(fieldProduced == null) {
            throw new ComplianceException("the injected value was null (not produced)");
        }
        if(!fieldProduced.getName().equals("MultiQualifiedFieldProducedClass")) {
            throw new ComplianceException("the injected value was wrong (produced elsewhere)");
        }
    }

    @Override
    public String getResult() {
        try {
            run();
        } catch (ComplianceException ex) {
            return "Multiqualified field producer uncompliant : " + ex.getMessage();
        }
        return "Multiqualified field producer compliant";
    }

}
