package main

import (
	"fmt"
)

func area_do_quadrado(x int, y int) int {
	return x * y
}

func main() {
	var x int
	var y int
	fmt.Scanf("%d %d", &x, &y)
	fmt.Println(area_do_quadrado(x, y))
}
