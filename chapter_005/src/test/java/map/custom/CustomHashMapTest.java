package map.custom;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class CustomHashMapTest {

    private static final String KEY_1 = "key1";
    private static final String KEY_2 = "key2";
    private static final String KEY_3 = "key3";
    private static final String VALUE_1 = "value1";
    private static final String VALUE_2 = "value2";
    private static final String VALUE_3 = "value3";

    private CustomHashMap<String, String> customHashMap;

    @Before
    public void setUp() {
        customHashMap = new CustomHashMap<>();
    }

    @Test
    public void insertTest1() {
        assertThat(customHashMap.insert(KEY_1, VALUE_1), is(true));
        assertThat(customHashMap.size(), is(1));
    }

    @Test
    public void insertTest2() {
        customHashMap.insert(KEY_1, VALUE_1);
        assertThat(customHashMap.insert(KEY_1, VALUE_2), is(true));
        assertThat(customHashMap.get(KEY_1), is(VALUE_2));
        assertThat(customHashMap.size(), is(1));
    }

    @Test
    public void insertTest3() {
        // чтобы ТОЧНО сработал метод isNeedResize() - уменьшаю размер внутреннего массива
        customHashMap = new CustomHashMap<>(2);
        for (int i = 0; i < 2; i++) {
            customHashMap.insert("" + i, "" + i);
        }
        assertThat(customHashMap.size(), is(2));
        assertThat(customHashMap.insert(KEY_1, VALUE_1), is(true));
        assertThat(customHashMap.size(), is(3));
    }

    @Test
    public void getTest1() {
        customHashMap.insert(KEY_1, VALUE_1);
        customHashMap.insert(KEY_2, VALUE_2);
        customHashMap.insert(KEY_3, VALUE_3);

        assertThat(customHashMap.size(), is(3));
        assertThat(customHashMap.get(KEY_1), is(VALUE_1));
        assertThat(customHashMap.get(KEY_2), is(VALUE_2));
        assertThat(customHashMap.get(KEY_3), is(VALUE_3));
    }

    @Test
    public void deleteTest1() {
        customHashMap.insert(KEY_1, VALUE_1);
        customHashMap.insert(KEY_2, VALUE_2);
        customHashMap.insert(KEY_3, VALUE_3);

        customHashMap.delete(KEY_2);

        assertThat(customHashMap.size(), is(2));
        assertThat(customHashMap.get(KEY_1), is(VALUE_1));
        assertThat(customHashMap.get(KEY_2), nullValue());
        assertThat(customHashMap.get(KEY_3), is(VALUE_3));
    }

    @Test
    public void iteratorTest1() {
        customHashMap.insert(KEY_1, VALUE_1);
        Iterator iterator = customHashMap.iterator();

        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(new Node<>(KEY_1, VALUE_1)));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test (expected = NoSuchElementException.class)
    public void iteratorTest2() {
        customHashMap.insert(KEY_1, VALUE_1);
        Iterator iterator = customHashMap.iterator();
        iterator.next();
        iterator.next();
    }
}