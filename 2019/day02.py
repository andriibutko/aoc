from itertools import product


def run(program, noun, verb):
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

# part 1
with open('day02.txt', 'r') as input:
    program = list(map(int, input.read().split(',')))

res1 = run(program[:], 12, 2)
print(f"part1 {res1}")

# part 2
target = 19690720

for noun, verb in product(range(100), range(100)):
	if run(program[:], noun, verb) == target:
		break

res2 = 100 * noun + verb
print(f"part2 {res2}")

