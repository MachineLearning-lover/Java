import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InformationPackage {

    private List<Payload> payloads;

    private Result result;

    public InformationPackage() {
        this.payloads = new ArrayList<>();
    }

    public InformationPackage(InformationBuilder informationBuilder) {
        this.payloads = informationBuilder.payloads;
        this.result = informationBuilder.result;
    }

    public static class InformationBuilder {

        private List<Payload> payloads;
        private Result result;

        public InformationBuilder() {
            this.payloads = new ArrayList<>();
            this.result = new Result("No reason given");
        }

        public InformationBuilder addPayload(Payload payload){
            this.payloads.add(payload);
            return this;
        }

        public InformationBuilder addPayloads(List<Payload> payloads){
            this.payloads.addAll(payloads);
            return this;
        }

        public InformationBuilder addResult(Result result){
            this.result = result;
            return this;
        }

        public InformationPackage build(){
            return new InformationPackage(this);
        }
    }

}
