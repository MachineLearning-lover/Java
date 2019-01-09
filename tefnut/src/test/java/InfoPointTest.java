import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class InfoPointTest {

    private Payload payload1 = new Payload("payload1");
    private Payload payload2 = new Payload("payload2");
    private Payload payload3 = new Payload("payload3");
    private List<Payload> payloads = Arrays.asList(payload1, payload2, payload3);
    Result trueResult = new Result("true");
    Result falseResult = new Result("false");
    Logger logger = Logger.getLogger("testing logger");

    @Test
    public void test_text_encryption_and_decryption() throws Exception {

        InformationPackage information = new InformationPackage.InformationBuilder().addPayloads(payloads).addResult(trueResult).build();

        InfoPoint informationPoint = new InfoPoint(information);

        String encoded = informationPoint.encode();
        System.out.println(encoded);
        String decode = informationPoint.decode(encoded);

        assertEquals("payload1,payload2,payload3,true", decode);
    }

    @Test
    public void test_builder(){
        InfoPoint infoPoint = new InfoPoint.InfoBuilder().addPayload(payload1).addResult(trueResult).build();
        assertNotNull(infoPoint.getInformationPackage());
        System.out.println(infoPoint);
    }

    @Test
    public void test_text_encryption() throws Exception {
        List<Payload> payloads1 = Arrays.asList(new Payload("s1"), new Payload(""));
        List<Payload> payloads2 = Arrays.asList(new Payload("s2"), new Payload(""));
        List<Payload> payloads3 = Arrays.asList(new Payload("s3"), new Payload(""));


        List<InfoPoint> infoPoints = new ArrayList<>();

        infoPoints.add(new InfoPoint.InfoBuilder().addPayloads(payloads1).addResult(trueResult).build());
        infoPoints.add( new InfoPoint.InfoBuilder().addPayloads(payloads2).addResult(trueResult).build());
        infoPoints.add( new InfoPoint.InfoBuilder().addPayloads(payloads3).addResult(trueResult).build());

        for(InfoPoint info : infoPoints){
            logger.info(info.encode());
        }
    }
}