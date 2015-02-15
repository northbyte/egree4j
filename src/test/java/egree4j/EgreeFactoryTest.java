package egree4j;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class EgreeFactoryTest {

    @Test
    public void testImplCreation() throws EgreeException {
        Egree impl = EgreeFactory.getInstance();
        assertTrue(impl instanceof EgreeImpl);
    }
}
