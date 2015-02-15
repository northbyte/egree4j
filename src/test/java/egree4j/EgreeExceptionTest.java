package egree4j;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class EgreeExceptionTest {

    @Test
    public void testMessageInheritance() {
        EgreeServiceException ese = new EgreeServiceException(
                "EX404", "Failed to find", 404);
        EgreeException ee = (EgreeException)ese;
        
        assertThat(ee.getMessage(), is("Failed to find"));
        assertThat(ese.getErrorMessage(), is("Failed to find"));
    }
}
