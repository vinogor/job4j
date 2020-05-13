package generator;

import org.junit.Before;
import org.junit.Test;
import parkingOperating.ParkingOperator;
import parkingOperating.parkings.CarParking;
import parkingOperating.parkings.LorryParking;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class GeneratorTest {

    private Generator generator;
    private Map<String, String> map;

    @Before
    public void setUp() {
        generator = new GeneratorImpl();
        map = new HashMap<>();
    }

    @Test
    public void generate1() {
        String template = "I am a ${name}, Who are ${subject} ?";
        map.put("name", "Petr");
        map.put("subject", "you");
        String result = generator.generate(template, map);
        assertThat(result, is("I am a Petr, Who are you ?"));
    }

    @Test
    public void generate2() {
        String template = " Help, ${sos}, ${sos}, ${sos}";
        map.put("sos", "Aaa");
        String result = generator.generate(template, map);
        assertThat(result, is(" Help, Aaa, Aaa, Aaa"));
    }

    @Test(expected = Exception.class)
    public void generate3() {
        String template = "Help, ${sos}, ${sos}, ${sos}";
        String result = generator.generate(template, map);
    }

    @Test(expected = Exception.class)
    public void generate4() {
        String template = "Help, ${sos}, ${sos}, ${sos}";
        map.put("s", "Aaa");
        String result = generator.generate(template, map);
    }

    @Test(expected = Exception.class)
    public void generate5() {
        String template = "I am a ${name}, Who are ${subject}";
        map.put("name", "Petr");
        map.put("subject", "you");
        map.put("subject2", "you2");
        String result = generator.generate(template, map);
    }
}