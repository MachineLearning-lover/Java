import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class InformationPackageTest {

    private Payload payload1 = new Payload("payload1");
    private Payload payload2 = new Payload("payload2");
    private List<Payload> payloads = Arrays.asList(payload1, payload2);

    @Test
    public void create_information_with_builder_with_payload_and_result(){

        Result result = new Result("true");

        InformationPackage information = new InformationPackage.InformationBuilder()
                .addPayload(payload1)
                .addPayload(payload2)
                .addResult(result)
                .build();

        assertNotNull(information.getPayloads());
        for (int index = 0; index < information.getPayloads().size(); index++) {
            assertEquals(information.getPayloads().get(index), payloads.get(index));
        }
        assertEquals(information.getResult(), result);

        System.out.println(information.toString());
    }

    @Test
    public void create_information_with_builder_with_payload_and_no_result(){

        Result defaultResult = new Result("No reason given");

        InformationPackage information = new InformationPackage.InformationBuilder()
                .addPayloads(payloads)
                .build();

        assertNotNull(information.getResult());
        for (int index = 0; index < information.getPayloads().size(); index++) {
            assertEquals(information.getPayloads().get(index), payloads.get(index));
        }

        assertEquals(information.getResult(), defaultResult);

        System.out.println(information.toString());
    }


}