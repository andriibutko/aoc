# puzzle prompt: https://adventofcode.com/2019/day/3

from ...base import TextSolution


class Solution(TextSolution):
    _year = 2019
    _day = 3
    
    def _parse_input(self) -> list[list[str]]:
        return [x.split(",") for x in self.input.split("\n")]
    
    def _get_direction(self, dir: str) -> tuple[int, int]:
        if dir == "U":
            return (-1, 0)
        elif dir == "D":
            return (1, 0)
        elif dir == "L":
            return (0, -1)
        elif dir == "R":
            return (0, 1)
        
    def _build_wire(self, cmds: list[str], loc: tuple[int, int]):
        points = []
        
        for cmd in cmds:
            dir, length = cmd[0], int(cmd[1:])
            vec = self._get_direction(dir)
            
            for i in range(1, length+1):
                v = (loc[0] + vec[0] * i, loc[1] + vec[1] * i)
                points.append(v)
                    
            loc = tuple(map(lambda c, v: c + v * length, loc, vec))
            
        return points
        
    start = (0, 0)
    curr = start

    def part_1(self) -> int:
        wires = self._parse_input()
        
        wireA = wires[0]
        wireB = wires[1]
        
        pointsA = self._build_wire(wireA, self.start)
        pointsB = self._build_wire(wireB, self.start)
        
        intersections = set(pointsA) & set(pointsB)
        
        return min([abs(i[0]) + abs(i[1]) for i in intersections])

    def part_2(self) -> int:
        wires = self._parse_input()
        
        wireA = wires[0]
        wireB = wires[1]
        
        pointsA = self._build_wire(wireA, self.start)
        pointsB = self._build_wire(wireB, self.start)
        
        intersections = set(pointsA) & set(pointsB)
        
        return min([pointsA.index(i) + pointsB.index(i) for i in intersections]) + 2 # +2 to account for 0-based indexes of A and B