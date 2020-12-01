package com.search.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Graph {

    private double[][] lengths;
    private double[] weights;
    private int start;
    private int end;

}
