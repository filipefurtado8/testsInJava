package junit.tests.testsInJava.business;

import java.util.Arrays;
import java.util.OptionalInt;

public class SomeBusinessImpl {

    SomeDataService dataService;


    public int calculateSum(int[] data) {

        //this way we're using stream methods
        //It's an optional in case the data comes null

        OptionalInt number = Arrays.stream(data).reduce((a, b) -> Integer.sum(a, b));
        
        return number.orElse(0);
        
        
        /*int sum = 0;
        for (int value : data) {
            sum += value;
        }
        return sum;*/
    }

    public int calculateSumUsingDataService() {

        int sum = 0;

        int[] data = dataService.retrieveAllData();
        for (int value : data) {
            sum += value;
        }
        return sum;
    }

    public void addValuesToDatabaseUsingDataService(Integer[] value) {
        dataService.addValuesToDatabase(value);
    }

    public void setDataService(SomeDataService dataService) {
        this.dataService = dataService;
    }
}
