import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.StringJoiner;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoPoint {

    private InformationPackage informationPackage;

    public String encode() throws Exception {
        return AesAlgorithm.encrypt(getInformationToString());
    }


    public static String decode(String hashPassword) throws Exception {
        return AesAlgorithm.decrypt(hashPassword);
    }

    private String getInformationToString(){
        StringJoiner stringJoiner = new StringJoiner(",");

        for (Payload payload : this.informationPackage.getPayloads())
            stringJoiner.add(payload.getPayload());
        stringJoiner.add(this.informationPackage.getResult().getResult());

        return stringJoiner.toString();
    }

    public static class InfoBuilder{
        private InformationPackage informationPackage;

        public InfoBuilder() {
            this.informationPackage = new InformationPackage();
        }

        public InfoBuilder addPayload(Payload payload){
            this.informationPackage.getPayloads().add(payload);
            return this;
        }

        public InfoBuilder addPayloads(List<Payload> payloads){
            this.informationPackage.getPayloads().addAll(payloads);
            return this;
        }

        public InfoBuilder addResult(Result result){
            this.informationPackage.setResult(result);
            return this;
        }

        public InfoPoint build(){
            return new InfoPoint(informationPackage);
        }
    }

}
