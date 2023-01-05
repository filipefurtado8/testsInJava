package junit.tests.testsInJava.business;

public class SomeBusinessImpl {

    SomeDataService dataService;

    public int calculateSum(int[] data) {
        int sum = 0;
        for (int value : data) {
            sum += value;
        }
        return sum;
    }

    public int calculateSumUsingDataService() {

        int sum = 0;
        
        int[] data = dataService.retrieveAllData();
        for (int value : data) {
            sum += value;
        }
        return sum;
    }

    public void setDataService(SomeDataService dataService) {
        this.dataService = dataService;
    }
}
