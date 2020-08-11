package main

import "fmt"

func tabuleiro(n int) (matriz [][]int) {

	var vetor []int
	for i := 0; i < n; i++ {
		vetor = append(vetor, 0)
	}

	for i := 0; i < n; i++ {
		matriz = append(matriz, vetor)
	}
	return matriz
}

func verificandoPos(tabuleiro [][]int, pos int) {

}

func backtracking(tabuleiro [][]int, peca int) (solution [][]int, ok bool) {

	if peca == 0 {
		return solution, true
	} else if ok {
		for i := 0; i < len(tabuleiro); i++ {
			solution, ok = backtracking(tabuleiro, peca-1)
			if ok {
				return solution, ok
			} else {
				return nil, ok
			}

		}
	}
}

func main() {
	fmt.Println("muca lindo")
}
