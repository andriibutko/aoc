import math

res = 0

# part 1
with open('day01.txt', 'r') as file:
     for line in file:
        try:
            number = int(line.strip())
            res += math.floor(number / 3) - 2
        except ValueError:
            print(f"Skipping invalid line: {line}")

print("part1")

print(res)


res = 0

# part 2
with open('day01.txt', 'r') as file:
     for line in file:
        try:
            number = int(line.strip())
            tmp = math.floor(number / 3) - 2
          
            while tmp > 0:
                res += tmp 
                tmp = math.floor(tmp / 3) - 2
           
        except ValueError:
            print(f"Skipping invalid line: {line}")

print("part2")

print(res)

