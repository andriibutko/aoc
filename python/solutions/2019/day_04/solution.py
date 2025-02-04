# puzzle prompt: https://adventofcode.com/2019/day/4

from ...base import TextSolution
from itertools import pairwise

class Solution(TextSolution):
    _year = 2019
    _day = 4
    
    def increasing(self, s):
        return all(a <= b for a, b in pairwise(s))

    def part_1(self) -> int:
        start, end = self.input.split("-")
        count = 0
        
        for i in range(int(start), int(end)):
            s = str(i)
            
            if not self.increasing(s):
                continue
                
            if not any([s.count(x) >= 2 for x in set(s)]):
                continue
                
            count += 1
        
        return count
        
    def part_2(self) -> int:
        start, end = self.input.split("-")
        count = 0
        
        for i in range(int(start), int(end)):
            s = str(i)
            
            if not self.increasing(s):
                continue
            
            if not any([s.count(x) == 2 for x in set(s)]):
                continue
                
            count += 1
        
        return count