# puzzle prompt: https://adventofcode.com/2019/day/2

from ...base import InputTypes, BaseSolution, answer
from itertools import product


class Solution(BaseSolution):
    _year = 2019
    _day = 2
    separator = ","
    
    @property
    def input_type(self):
        return InputTypes.INTSPLIT
    
    def compute(self, program, noun, verb):
        program[1] = noun
        program[2] = verb

        i = 0
        while program[i] != 99:
            op = program[i]

            l = program[i+1]
            r = program[i+2]
            p = program[i+3]

            if op == 1:
                program[p] = program[l] + program[r]
            elif op == 2:
                program[p] = program[l] * program[r]
            else:
                break

            i += 4

        return program[0]

    def part_1(self) -> int:
        return self.compute(self.input[:], 12, 2)
        

    def part_2(self) -> int:
        target = 19_690_720
        for noun, verb in product(range(100), range(100)):
            if self.compute(self.input[:], noun, verb) == target: 
                break
            
        return 100 * noun + verb