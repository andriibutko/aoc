package aoc.day09;

import java.util.stream.Stream;
import java.util.*;

import aoc.Day;
import aoc.common.*;;

public class Day09 implements Day {

    @Override
    public String part1(String input) {
        Queue<Integer> freeSpace = new LinkedList<>();
        Stack<Pair<Integer, Integer>> allocatedSpace = new Stack<>();

        var memory = Stream.of(input.trim().split(""))
                        .mapToInt(Integer::parseInt)
                        .toArray();

        var memorySize = Arrays.stream(memory).sum();
        var inputArray = memory;

        var compactedMemory = new int[memorySize];

        int i = 0;
        int memoryBlockIndex = 0;
        int memoryIndex = 0;
        while(i < inputArray.length) {
            var value = memoryBlockIndex;
            var count = inputArray[i];

            for(int j = 0; j < count; j++) {
                compactedMemory[memoryIndex + j] = value;
                allocatedSpace.push(Pair.pair(memoryIndex+j, value));
            }

            memoryIndex += count;
            i++;
            memoryBlockIndex++;

            if (i >= inputArray.length) {
                break;
            }

            var freeSpaceLocation = i;
            var freeSpaceCount = inputArray[freeSpaceLocation];
            for (int j = 0; j < freeSpaceCount; j++) {
                freeSpace.add(memoryIndex+j);
            }
            memoryIndex += freeSpaceCount;
            i++;
        }

        while (!freeSpace.isEmpty()) {
            var freeSpaceLocation = freeSpace.poll();

            var occupied = allocatedSpace.pop();
            var index = occupied.a();
            var value = occupied.b();

            if(index <= freeSpaceLocation) break;
    
            compactedMemory[index] = 0;
            compactedMemory[freeSpaceLocation] = value;
        }

        long checkSum = 0;
        for (int j = 0; j < compactedMemory.length; j++) {
            checkSum += compactedMemory[j] * j;
        }

        return String.valueOf(checkSum);
    }

    record Block(int id, int start, int length, int free) {
        long checksum() {
            long sum = 0;
            for (int i = 0; i < length; i++) 
                sum += id * (start + i);
            return sum;
        }
    }

    @Override
    public String part2(String input) {
        List<Block> blocks = new ArrayList<>(); 

        int start = 0;
        for (int i = 0; i < input.length(); i += 2) {
            int usedLength = Character.getNumericValue(input.charAt(i));
            if (i == input.length() - 1) blocks.add(new Block(i / 2, start, usedLength, 0));
            else {
                int freeLength = Character.getNumericValue(input.charAt(i + 1));
                blocks.add(new Block(i / 2, start, usedLength, freeLength));
                start += usedLength + freeLength;
            }
        }

        int blockId = blocks.size() - 1;
        int lastMovedId = blockId + 1;
        while (blockId > 0) {
            Block tail = blocks.get(blockId);        
            boolean moved = false;        
            if (tail.id() < lastMovedId) {
                for (int i = 0; !moved && i < blockId; i++) {
                    Block head = blocks.get(i);
                    if (head.free >= tail.length) { 
                        blocks.remove(blockId);
                        lastMovedId = blockId;
                        blocks.set(i, new Block(head.id, head.start, head.length, 0));
                        blocks.add(i + 1, new Block(tail.id, head.start + head.length, tail.length, head.free - tail.length));
                        moved = true;
                    }
                }
            }        
            if (!moved) blockId--;
        }    
        
        return String.valueOf(blocks.stream().mapToLong(Block::checksum).sum());
    }

}
