package aoc.day05;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import aoc.Day;

public class Day05 implements Day {

    @Override
    public String part1(String input) {
        var parts = input.split("\n\n");
        var rules = parts[0].lines();

        var rulesMap = new HashMap<String, HashSet<String>>();
        rules.forEach(line -> {
            var r = line.split("\\|");
            rulesMap.computeIfAbsent(r[0], k -> new HashSet<>()).add(r[1]);
        });

        var linesToOrder = parts[1].lines()
            .map(line -> Arrays.stream(line.split(","))
            .map(Integer::parseInt)
            .toList())
            .toList();

        var count = new java.util.concurrent.atomic.AtomicInteger(0);

        linesToOrder.forEach(l -> {
            if(IsValid(l, rulesMap)) {
                var middle = l.get(l.size() / 2);
                count.addAndGet(middle);
            }
        });

        return String.valueOf(count);
    }

    @Override
    public String part2(String input) {
        var parts = input.split("\n\n");
        var rules = parts[0].lines();

        var rulesMap = new HashMap<String, HashSet<String>>();
        rules.forEach(line -> {
            var r = line.split("\\|");
            rulesMap.computeIfAbsent(r[0], k -> new HashSet<>()).add(r[1]);
        });

        var linesToOrder = parts[1].lines()
            .map(line -> Arrays.stream(line.split(","))
            .map(Integer::parseInt)
            .toList())
            .toList();

        var count = new java.util.concurrent.atomic.AtomicInteger(0);

        linesToOrder.forEach(l -> {
            if(!IsValid(l, rulesMap)) {
                var sorted = sort(l, rulesMap);
                var middle = sorted.get(sorted.size() / 2);
                count.addAndGet(middle);
            }
        });

        return String.valueOf(count);
    }

    private List<Integer> sort(List<Integer> list, Map<String, HashSet<String>> rulesMap) {
        var mutableList = new ArrayList<>(list);
        Comparator<Integer> comparator = (o1, o2) -> {
            var left = rulesMap.getOrDefault(o1.toString(), new HashSet<>());
            var right = rulesMap.getOrDefault(o2.toString(), new HashSet<>());

            if (left.contains(o2.toString())) {
                return -1;
            } else if (right.contains(o1.toString())) {
                return 1;
            } 
                
            return 0;
        };
        
        mutableList.sort(comparator);
        return mutableList;
    }

    private boolean IsValid(List<Integer> list, Map<String, HashSet<String>> rulesMap) {
        Comparator<Integer> comparator = (o1, o2) -> {
            var left = rulesMap.getOrDefault(o1.toString(), new HashSet<>());
            var right = rulesMap.getOrDefault(o2.toString(), new HashSet<>());

            if (left.contains(o2.toString())) {
                return -1;
            } else if (right.contains(o1.toString())) {
                return 1;
            } 
                
            return 0;
        };

        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (comparator.compare(list.get(i), list.get(j)) > 0) {
                    return false;
                }
            }
        }
        return true;
    }
   }
