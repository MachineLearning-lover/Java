package sample.Misc;

import com.google.common.primitives.Bytes;

import java.util.ArrayList;
import java.util.List;

public class StringTrim {
    public static String stringTrim(String string){
        /**
         * This eliminates zero bytes from the string.
         */
        List<Byte> list = new ArrayList<>();
        for(Byte element:string.getBytes())
            if(element != 0)
                list.add(element);
        // using Guava
        return new String(Bytes.toArray(list));
    }
}
