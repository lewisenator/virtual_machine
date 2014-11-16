def fib(n)
	return 1 if n == 1
	return 0 if n < 1
	fib(n - 1) + fib(n - 2)
end

n = ARGV[0].to_i

puts "fib(#{n}) is #{fib(n)}"