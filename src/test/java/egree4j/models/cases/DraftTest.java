package egree4j.models.cases;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class DraftTest {
    
    private Draft draft;
    
    @Test
    public void testDefaultDraftValues() {
        draft = new Draft("New case");
        
        assertThat(draft.getId(), is(not(nullValue())));
        assertThat(draft.getName(), is("New case"));
        assertThat(draft.getAllowedSignatureTypes().size(), is(2));
        assertThat(draft.getAllowedSignatureTypes(), 
                contains(SignatureType.TOUCH, SignatureType.SMS));
    }
    
    @Test
    public void testDefaultDraftValuesOnSignatures() {
        List<SignatureType> types = new ArrayList<>(
                Arrays.asList(SignatureType.ELECTRONIC_ID));
        draft = new Draft("Another case", types);

        assertThat(draft.getId(), is(not(nullValue())));
        assertThat(draft.getName(), is("Another case"));
        assertThat(draft.getAllowedSignatureTypes().size(), is(1));
        assertThat(draft.getAllowedSignatureTypes(), 
                contains(SignatureType.ELECTRONIC_ID));
    }

}
