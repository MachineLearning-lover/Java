package enums;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.util.function.BiFunction;

import static org.mockito.Mockito.when;

class Movie {
    public enum Type {
        REGULAR(PriceService::computeRegularPrice),
        NEW_RELEASE(PriceService::computeNewReleasePrice),
        CHILDREN(PriceService::computeChildrenPrice);
        public final BiFunction<PriceService, Integer,Integer> priceAlgo;
        Type(BiFunction<PriceService, Integer,Integer> priceAlgo) {
            this.priceAlgo = priceAlgo;
        }
    }
}

interface NewReleasePriceRepo{
    double getFactor();
}

class PriceService {
    private final NewReleasePriceRepo repo;
    public PriceService(NewReleasePriceRepo repo) {
        this.repo = repo;
    }
    public int computeNewReleasePrice(int days) {
        return (int) (days * repo.getFactor());
    }
    public int computeRegularPrice(int days) {
        return days + 1;
    }
    public int computeChildrenPrice(int dayes) {
        return 5;
    }

    public int computePrice(Movie.Type type, int days) {
        return type.priceAlgo.apply(this, days);
    }
}

@RunWith(JUnit4.class)
public class EnumTest{

    @Test
    public void test(){
        NewReleasePriceRepo repo = Mockito.mock(NewReleasePriceRepo.class);
        when(repo.getFactor()).thenReturn(2d);
        PriceService price = new PriceService(repo);
        System.out.println(price.computePrice(Movie.Type.REGULAR, 2));
        System.out.println(price.computePrice(Movie.Type.NEW_RELEASE, 2));
        System.out.println(price.computePrice(Movie.Type.CHILDREN, 2));
    }
}