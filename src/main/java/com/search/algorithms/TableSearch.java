package com.search.algorithms;

import com.search.dto.Graph;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TableSearch {

    public Map<String, Object> getWay(Graph graph) {
        int start = graph.getStart() - 1;
        int end = graph.getEnd() - 1;
        double[] weights = graph.getWeights();
        double[][] points = graph.getLengths();

        if (isSolution(start, end, points)) {
            return findWay(start, end, points, weights);
        } else {
            return new HashMap<>(
                    Map.of(
                            "decision", false,
                            "path", List.of(graph.getStart()),
                            "length", 0
                    )
            );
        }
    }

    private Map<String, Object> findWay(int start, int end, double[][] points, double[] weights) {
        Set<Integer> path = new LinkedHashSet<>();
        path.add(start + 1);

        boolean decision = true;
        int i = start;
        int index;
        double min;
        double length = 0;
        double[] values;

        do {
            values = func(points[i], weights);
            min = 0;
            index = -1;

            for (int one = 0; one < values.length; one++) {
                if (values[one] != 0.0) {
                    if (Double.compare(min, 0) == 0) {
                        min = values[one];
                        index = one;
                    }

                    if (min > values[one]) {
                        min = values[one];
                        index = one;
                    }
                }
            }

            if (Double.compare(min, 0) != 0) {
                length += points[i][index];

                if (!path.add(index + 1)) {
                    points[i][index] = 0;
                    points[index][i] = 0;

                    return findWay(start, end, points, weights);
                }

                i = index;
            } else {
                decision = false;
                break;
            }
        } while (index != end);

        return new HashMap<>(
                Map.of(
                        "decision", decision,
                        "path", path,
                        "length", length
                )
        );
    }

    private double[] func(double[] connections, double[] weights) {
        double[] result = new double[connections.length];

        for (int one = 0; one < result.length; one++) {
            if (connections[one] != 0) {
                result[one] = connections[one] + weights[one];
            }
        }

        return result;
    }

    private boolean isSolution(int start, int end, double[][] points) {
        return isInBounds(start, points.length) && isInBounds(end, points.length);

//        for (int one = 0; one < points.length; one++) {
//            if (one != end && points[one][end] != 0) {
//                return true;
//            }
//        }
    }

    private boolean isInBounds(int a, int border) {
        return a >= 0 && a < border;
    }

}
