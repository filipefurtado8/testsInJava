package junit.tests.testsInJava.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

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
        verify(dataMock, times(1)).retrieveAllData();
    }

    @Test
    public void calculateSum_oneValue() {

        //Given & When
        when(dataMock.retrieveAllData()).thenReturn(new int[]{5});
        int actualResult = business.calculateSumUsingDataService();
        int expectedResult = 5;

        //Then
        assertEquals(actualResult, expectedResult);
        verify(dataMock, times(1)).retrieveAllData();
    }

    @Test
    public void addOneValueToDatabase() {
        //SUT
        Integer[] values = new Integer[]{1, 2, 3};
        dataMock.addValuesToDatabase(values);

        //Verification
        ArgumentCaptor<Integer[]> captor = ArgumentCaptor.forClass(Integer[].class);

        //the capture method is used to save the value that you want to further evaluate with assertEquals
        //in this case it saves the values variable(line 69) information
        //since captor.capture() represents the values, the verify method also checks for how many times the addValuesToDatabase is used
        verify(dataMock).addValuesToDatabase(captor.capture());


        //Here I'm verifying if the values inserted in the method are the same in the ArgumentCaptor
        //the system.out.println demonstrates that
        System.out.println(Arrays.toString(captor.getValue())); //captor.getValue -> [1,2,3]
        assertEquals(values, captor.getValue());

    }

    @Test
    public void addMultipleValuesToDatabase() {
        //SUT
        Integer[] values = new Integer[]{1, 2, 3};
        Integer[] values1 = new Integer[]{4, 5, 6};
        dataMock.addValuesToDatabase(values);
        dataMock.addValuesToDatabase(values1);

        //Verification
        ArgumentCaptor<Integer[]> captor = ArgumentCaptor.forClass(Integer[].class);

        verify(dataMock, times(2)).addValuesToDatabase(captor.capture());

        //this list is created to grab and simulate the values used in line 96 and 97
        List<Integer[]> allValues = captor.getAllValues();

        assertEquals(values, allValues.get(0));
        assertEquals(values1, allValues.get(1));

    }

}
