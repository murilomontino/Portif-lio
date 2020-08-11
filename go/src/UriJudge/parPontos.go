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

func pegandoPontos() [][]Ponto {

	var quantidade int32
	fmt.Scanf("%d \n", &quantidade)

	var listPoint [][]Ponto
	var auxiliar int32 = 0

	for quantidade != 0 {

		arrayPoint := []Ponto{}
		listPoint = append(listPoint, arrayPoint)
		var contador int32 = 0

		for ; contador < quantidade; contador++ {
			var ponto Ponto
			fmt.Scanf("%f %f \n", &ponto.x, &ponto.y)
			listPoint[auxiliar] = append(listPoint[auxiliar], ponto)
		}

		auxiliar++
		fmt.Scanf("%d \n", &quantidade)
	}

	return listPoint
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

func mapeamento(ordX []Ponto, ordY []Ponto) []Ponto {
	var novoOrdY []Ponto
	for indice, valor := range ordY {

		if buscaBinaria(valor, ordX) {
			novoOrdY = append(novoOrdY, ordY[indice])

		}
	}

	return novoOrdY
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

func mergePoint(ordX []Ponto, ordY []Ponto) (valorDist float64) {

	valorDist = math.Inf(0)
	var metade int = len(ordX) / 2

	if len(ordX) <= 3 {
		return forcaBruta(ordX)
	}

	mergePoint(ordX[:metade], mapeamento(ordX[:metade], ordY))
	mergePoint(ordX[metade:], mapeamento(ordX[metade:], ordY))

	for i := 0; i < len(ordY); i++ {

		for k := 1 + i; (k < len(ordY)) && ((ordY[k].y - ordY[i].y) < valorDist); k++ {
			if valor := distancia(ordY[i], ordY[k]); valor < valorDist {
				valorDist = valor
			}
		}
	}

	return valorDist
}

func calculando(listPoint [][]Ponto) []float64 {

	var menorDistancia []float64

	for _, ordX := range listPoint {
		var ordY []Ponto = make([]Ponto, len(ordX))
		copy(ordY, ordX)
		sort.Slice(ordX, func(i, j int) bool { return ordX[i].x < ordX[j].x })
		sort.Slice(ordY, func(i, j int) bool { return ordY[i].y < ordY[j].y })
		dist := mergePoint(ordX, ordY)
		menorDistancia = append(menorDistancia, dist)

	}

	return menorDistancia
}

func imprimindo(valores []float64) {

	for _, valor := range valores {

		if valor = math.Sqrt(valor); valor < 10000.0 {
			fmt.Printf("%.4f\n", valor)
		} else {
			fmt.Printf("INFINITY\n")
		}

	}
}

func main() {
	listPoint := pegandoPontos()
	valores := calculando(listPoint)
	imprimindo(valores)

}

func distancia(pontoA Ponto, pontoB Ponto) float64 {
	x := pontoA.x - pontoB.x
	y := pontoA.y - pontoB.y
	distancia := (x * x) + (y * y)
	return distancia
}
