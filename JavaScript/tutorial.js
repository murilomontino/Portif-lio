
let init = 0
let IdTeste = document.getElementById('IdTeste')
IdTeste.addEventListener("click", clicou)




function maior(x, y) {
    let menor = x.value < y.value ? x.value : y.value
    return document.getElementById('IdResultado').innerHTML = `<b>O Menor valor é:</b> ${menor}`
    
}

function Pessoa(nome, sobrenome=undefined, idade=undefined, sexo=undefined){
    pessoa = {}
        pessoa.nome = nome
        pessoa.sobrenome = sobrenome
        pessoa.idade = idade
        pessoa.sexo = sexo
    pessoa.nomeCompleto = () =>{
        return `${pessoa.nome} ${pessoa.sobrenome} ` 
    }
    
    return pessoa
}

function clicou(){
    const frases = ['Te amo', 'Vai dar Tudo Certo', 'Minha Dengosa']
    
    if(init < frases.length){
        IdTeste.innerHTML = ` <h2> ${frases[init]} </h2> `
    }else{
        init = 0
        IdTeste.innerHTML = ` <h2> ${frases[init]} </h2> `
    }

    init += 1
    

    
}


