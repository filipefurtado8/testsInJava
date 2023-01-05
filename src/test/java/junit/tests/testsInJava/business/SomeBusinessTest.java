package junit.tests.testsInJava.business;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SomeBusinessTest {
    @Test
    public void calculateSum() {
        //Given
        SomeBusinessImpl business = new SomeBusinessImpl();

        //When
        int actualResult = business.calculateSum(new int[]{1, 2, 3});
        int expectedResult = 6;

        //Then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void calculateSum_empty() {
        //Given
        SomeBusinessImpl business = new SomeBusinessImpl();

        //When
        int actualResult = business.calculateSum(new int[]{});
        int expectedResult = 0;

        //Then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void calculateSum_oneValue() {
        //
        SomeBusinessImpl business = new SomeBusinessImpl();

        //When
        int actualResult = business.calculateSum(new int[]{5});
        int expectedResult = 5;

        //Then
        assertEquals(actualResult, expectedResult);
    }

}
