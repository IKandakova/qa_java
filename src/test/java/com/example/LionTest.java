package com.example;

import com.sun.source.tree.ParameterizedTypeTree;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class LionTest {

    @Mock
    Feline feline;

    @Before
    public void setMock() {
        MockitoAnnotations.openMocks(this);
    }

    @Parameterized.Parameters(name = "{index}: {0}-{1}")
    public static Iterable<Object[]> data(){
        return Arrays.asList(new Object[][]{
            {1, 1},
            {2, 2}
        });
    }

    @Parameterized.Parameter(0)
    public int volume;

    @Parameterized.Parameter(1)
    public int result;

    @Test
    public void getKittensTest(){
        Lion lion = new Lion(feline);
        Mockito.when(feline.getKittens()).thenReturn(volume);
        Assert.assertEquals(result,lion.getKittens());
    }

    @Test
    public void getFoodTest() throws Exception {
        Lion lion = new Lion(feline);
        List<String> food = List.of("Животные", "Птицы", "Печеньки");
        Mockito.when(feline.getFood("Хищник")).thenReturn(food);
        Assert.assertEquals(food, lion.getFood());
    }

    @Test
    public void doesHaveManeTrue() throws Exception {
        Lion lion = new Lion("Самец");
        Assert.assertTrue(lion.doesHaveMane());
    }

    @Test
    public void doesHaveManeFalse() throws Exception {
        Lion lion = new Lion("Самка");
        Assert.assertFalse(lion.doesHaveMane());
    }

    @Test
    public void doesHaveManeNone() throws Exception {
        Exception exception = Assert.assertThrows(Exception.class, () -> {
            new Lion("Самки");
        });
        Assert.assertEquals("Используйте допустимые значения пола животного - самец или самка", exception.getMessage());
    }

}
