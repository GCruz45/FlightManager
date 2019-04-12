package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class SearchAndOrder {

    public ArrayList<Airlines> bubbleSortDate(ArrayList<Airlines> airlines) {//Por burbuja.
        Airlines[] fixedAirlines = new Airlines[airlines.size()];
        int x = 0;
        for (Airlines al : airlines
        ) {
            fixedAirlines[x] = al;
            x++;
        }
        int n, i, j;
        Airlines temp;
        n = fixedAirlines.length;
        for (i = 0; i < n - 1; i++) {
            for (j = 0; j < n - i - 1; j++) {
                if (fixedAirlines[j].compareByDate(fixedAirlines[j + 1]) == 1) {
                    temp = fixedAirlines[j];
                    fixedAirlines[j] = fixedAirlines[j + 1];
                    fixedAirlines[j + 1] = temp;
                }
            }
        }
        airlines = new ArrayList<Airlines>(Arrays.asList(fixedAirlines));
        return airlines;
    }

    public ArrayList<Airlines> bubbleSortDepartureTime(ArrayList<Airlines> airlines) {//Por burbuja.
        Airlines[] fixedAirlines = new Airlines[airlines.size()];
        int x = 0;
        for (Airlines al : airlines
        ) {
            fixedAirlines[x] = al;
            x++;
        }
        int n, i, j;
        Airlines temp;
        n = fixedAirlines.length;
        for (i = 0; i < n - 1; i++) {
            for (j = 0; j < n - i - 1; j++) {
                if (fixedAirlines[j].compareByTime(fixedAirlines[j + 1]) == 1) {
                    temp = fixedAirlines[j];
                    fixedAirlines[j] = fixedAirlines[j + 1];
                    fixedAirlines[j + 1] = temp;
                }
            }
        }
        airlines = new ArrayList<Airlines>(Arrays.asList(fixedAirlines));
        return airlines;
    }

    public ArrayList<Airlines> insertionSortAirline(ArrayList<Airlines> airlines) {//Por insercion.
        Airlines[] fixedAirlines = new Airlines[airlines.size()];
        int i = 0;
        for (Airlines al : airlines
        ) {
            fixedAirlines[i] = al;
            i++;
        }
        int n = airlines.size();
        for (i = 1; i < n; ++i) {
            Airlines key = fixedAirlines[i];
            int j = i - 1;

            while (j >= 0 && fixedAirlines[j].compareByAirline(key) > 0) {
                fixedAirlines[j + 1] = fixedAirlines[j];
                j = j - 1;
            }
            fixedAirlines[j + 1] = key;
        }
        airlines = new ArrayList<Airlines>(Arrays.asList(fixedAirlines));
        return airlines;
    }

    public ArrayList<Airlines> insertionSortFlightNumber(ArrayList<Airlines> airlines) {//Por insercion.
        Airlines[] fixedAirlines = new Airlines[airlines.size()];
        int i = 0;
        for (Airlines al : airlines
        ) {
            fixedAirlines[i] = al;
            i++;
        }
        int n = airlines.size();
        for (i = 1; i < n; ++i) {
            Airlines key = fixedAirlines[i];
            int j = i - 1;

            while (j >= 0 && fixedAirlines[j].compareByFlightNum(key) > 0) {
                fixedAirlines[j + 1] = fixedAirlines[j];
                j = j - 1;
            }
            fixedAirlines[j + 1] = key;

        }
        airlines = new ArrayList<Airlines>(Arrays.asList(fixedAirlines));
        return airlines;
    }

    public ArrayList<Airlines> selectionSortDestination(ArrayList<Airlines> airlines) {//Por seleccion.
        for (int i = 0; i < airlines.size() - 1; i++) {
            Airlines temp = airlines.get(i);
            int minIndex = i;
            for (int j = i + 1; j < airlines.size(); j++
            ) {
                if (airlines.get(j).compareByDestination(temp) < 0) {
                    minIndex = j;
                    temp = airlines.get(j);
                    airlines.set(minIndex, airlines.get(i));
                    airlines.set(i, temp);
                }
            }
        }
        return airlines;
    }

    public ArrayList<Airlines> selectionSortGate(ArrayList<Airlines> airlines) {//Por seleccion.
        for (int i = 0; i < airlines.size() - 1; i++) {
            Airlines temp = airlines.get(i);
            int minIndex = i;
            for (int j = i + 1; j < airlines.size(); j++
            ) {
                if (airlines.get(j).compareByGate(temp) < 0) {
                    minIndex = j;
                    temp = airlines.get(j);
                    airlines.set(minIndex, airlines.get(i));
                    airlines.set(i, temp);
                }
            }
        }
        return airlines;
    }

    public int linearSearchDate(String value, ArrayList<Airlines> values) {
        int index = -1;
        for (Airlines element : values
        ) {
            if (element.getDate().toString().contains(value)) {
                index = values.indexOf(element);
            }
        }
        return index;
    }

    public int binarySearchGate(String valueString, ArrayList<Airlines> values) {
        int value = Integer.valueOf(valueString);
        int found = -1;
        int start = 0;
        int end = values.size() - 1;
        while (start <= end && found == -1) {
            int half = (start + end) / 2;
            if (values.get(half).getGate() == value) {
                found = half;
            } else if (values.get(half).getGate() > value) {
                end = half - 1;
            } else {
                start = half + 1;
            }
        }
        return found;
    }
}
