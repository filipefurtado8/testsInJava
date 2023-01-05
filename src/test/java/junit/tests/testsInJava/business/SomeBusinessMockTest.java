package junit.tests.testsInJava.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SomeBusinessMockTest {

    @InjectMocks
    SomeBusinessImpl business;
    @Mock
    SomeDataService dataMock;


    @Test
    public void calculateSum() {
        //SUT -> system under test
        //Given & When
        when(dataMock.retrieveAllData()).thenReturn(new int[]{1, 2, 3}).thenReturn(new int[]{});

        //Then
        assertEquals(6, business.calculateSumUsingDataService());
        assertEquals(0, business.calculateSumUsingDataService());

        //verification
        //verify method allows to check if a method is used a X number of times
        verify(dataMock, times(2)).retrieveAllData();

    }

    @Test
    public void calculateSum_empty() {

        //Given & When
        when(dataMock.retrieveAllData()).thenReturn(new int[]{});

        //Another way of testing by asserting values to variables
        int actualResult = business.calculateSumUsingDataService();
        int expectedResult = 0;

        //Then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void calculateSum_oneValue() {

        //Given & When
        when(dataMock.retrieveAllData()).thenReturn(new int[]{5});
        int actualResult = business.calculateSumUsingDataService();
        int expectedResult = 5;

        //Then
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void addValueToDatabase() {
        //SUT
        Integer[] values = new Integer[]{1, 2, 3};
        dataMock.addValuesToDatabase(values);

        //Verification
        ArgumentCaptor<Integer[]> captor = ArgumentCaptor.forClass(Integer[].class);
        verify(dataMock, times(1)).addValuesToDatabase(captor.capture());

        //Here I'm verifying if the values inserted in the method are the same in the ArgumentCaptor
        assertEquals(values, captor.getValue());

    }

}
