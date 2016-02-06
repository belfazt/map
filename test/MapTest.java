
import java.util.NoSuchElementException;
import mx.tec.arq.tdd.DiegoCamargoMap;
import mx.tec.arq.tdd.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jzab
 */
public class MapTest {
    private Map<String, String> map;
    
    @Before
    public void setUp() {
        map = new DiegoCamargoMap();
    }

    @Test
    public void testConstructor() {
        Assert.assertFalse(map.containsKey("coolKey"));
        Assert.assertEquals(0, map.size());
    }
    
    @Test
    public void testAdd() {
        map.put("coolKey", "coolValue");
        Assert.assertTrue(map.containsKey("coolKey"));
        Assert.assertTrue(map.containsValue("coolValue"));
        Assert.assertEquals(1, map.size());
        map.put("coolKey2", "coolValue2");
        Assert.assertTrue(map.containsKey("coolKey2"));
        Assert.assertTrue(map.containsValue("coolValue2"));
        Assert.assertEquals(2, map.size());
        map.put("coolKey3", "coolValue3");
        Assert.assertTrue(map.containsKey("coolKey3"));
        Assert.assertTrue(map.containsValue("coolValue3"));
        Assert.assertEquals(3, map.size());
    }
    
    @Test
    public void testRemove() {
        map.put("coolKey", "coolValue");
        Assert.assertTrue(map.containsKey("coolKey"));
        Assert.assertTrue(map.containsValue("coolValue"));
        Assert.assertEquals(1, map.size());
        Assert.assertNull(map.remove("undefinedKey"));
        Assert.assertEquals("coolValue", map.remove("coolKey"));
        Assert.assertEquals(0, map.size());
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testReplace() {
        map.put("coolKey", "coolValue");
        Assert.assertTrue(map.containsKey("coolKey"));
        Assert.assertTrue(map.containsValue("coolValue"));
        Assert.assertTrue(map.replace("coolKey", "replacedCoolValue"));
        Assert.assertFalse(map.containsValue("coolValue"));
        Assert.assertTrue(map.containsValue("replacedCoolValue"));
        map.replace("keyDoesNotExist", "");
    }
    
    @Test
    public void testEmptyValue() {
        Assert.assertFalse(map.containsValue("EMPTY_VALUE"));
        map.put("EMPTY_VALUE", "EMPTY_VALUE");
        Assert.assertTrue(map.containsValue("EMPTY_VALUE"));
        map.put("nullValue", null);
        Assert.assertNull(map.get("nullValue"));
    }
}