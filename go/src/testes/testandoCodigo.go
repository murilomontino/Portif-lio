package main

import (
	"fmt"
	"math"
	"sort"
)

// Ponto estrutra que representa pontos em plano 2D
type Ponto struct {
	x float64
	y float64
}

func buscaBinaria(ponto Ponto, lista []Ponto) bool {

	limiteInferior := 0
	limiteSuperior := len(lista) - 1
	var meio int
	var valorY float64

	for limiteInferior <= limiteSuperior {
		meio = (limiteSuperior + limiteInferior) / 2

		if ponto.y == lista[meio].y {
			valorY = lista[meio].y
		}

		if ponto.y < lista[meio].y {
			limiteSuperior = meio - 1
		} else {
			limiteInferior = meio + 1
		}

	}

	contador := meio

	for ; (contador < len(lista)) && (valorY == ponto.y); contador++ {
		if ponto.x == lista[contador].x {
			return true
		}
	}

	contador = meio

	for ; (contador > -1) && (valorY == ponto.y); contador-- {
		if ponto.x == lista[contador].x {
			return true
		}
	}

	return false
}

func forcaBruta(ordX []Ponto) (valorDist float64) {

	valorDist = math.Inf(0)

	for i := 0; i < len(ordX); i++ {

		for k := i + 1; k < len(ordX); k++ {

			if valor := distancia(ordX[i], ordX[k]); valor < valorDist {
				valorDist = valor
			}

		}

	}

	return valorDist
}

func distancia(pontoA Ponto, pontoB Ponto) float64 {
	x := pontoB.x - pontoA.x
	y := pontoB.y - pontoA.y
	distancia := (x * x) + (y * y)
	return math.Sqrt(distancia)
}

func main() {
	listaPonto := []Ponto{{2, 1}, {3, 3}, {2, 3}, {2, 2}, {7, 2}, {6.3, 2}, {5, 2}, {4, 2}}
	sort.Slice(listaPonto, func(i, j int) bool { return listaPonto[i].y < listaPonto[j].y })
	fmt.Println(forcaBruta(listaPonto))

}
