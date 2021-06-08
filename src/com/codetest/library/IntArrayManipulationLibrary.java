package com.codetest.library;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class IntArrayManipulationLibrary {

	void max(int[] arr) {
		Arrays.stream(arr).max().getAsInt();
	}
	
	void groupAndCount(int[] arr) {
		Map<Integer, Integer> grps = Arrays.stream(arr)
				.boxed()
                .collect(Collectors.groupingBy(e->e, Collectors.reducing(0, e->1, Integer::sum)));
	}
	
}
