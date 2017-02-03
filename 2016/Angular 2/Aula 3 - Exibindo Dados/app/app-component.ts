import {Component} from 'angular2/core';
import {Cliente} from './models/cliente';
@Component({
    selector: 'meu-app',
    template:   `
                    <h1>Angular 2 - aula 3</h1>
                    <h2>{{listagem | uppercase}}</h2>
                    <p>cliente selecionado: {{cliente.nome}}</p>
                    <ul>
                        <li *ngFor="#cliente of clientes #i = index">{{cliente.id}} - {{cliente.nome}}
                        <a (click)="selecionarCliente(i)" href="#"> Selecionar</a></li>
                    </ul>
                `
})
export class AppComponent{
    private listagem: string;
    private clientes: Cliente[] = [];
    private cliente: Cliente = this.clientes[0];

    constructor(){
        this.listagem = "Listagem de Clientes";
        this.clientes = [
            new Cliente(1, 'Richard'),
            new Cliente(2, 'Edgard')
        ];
        this.cliente = this.clientes[0];
    }
    selecionarCliente(index: number): void{
        if(index <this.clientes.lenght || index >= 0){
            this.cliente = this.clientes[index];
        }
    }

    
}