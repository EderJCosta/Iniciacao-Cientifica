import {Component} from 'angular2/core';
import {CepPipe} from './filtros/cep-pipe';
@Component({
    selector: 'meu-app',
    template:   `
                    <h1>Angular 2 - aula 4</h1>
                    <p>Caixa Alta: {{texto | uppercase}}</p>
                    <p>Caixa baixa: {{texto | lowercase}}</p>
                    <p>Valor: {{valor | currency:'BRL':true}}</p>
                    <p>cep: {{cep | cep}}</p>
                    <p>Data: {{dataAtual | date:'dd/MM/yyyy HH:mm'}}</p>
                    <p>Data Dinâmica: {{dataAtual | date:formatarData}}</p>
                    <button (click)="mudarFormatoData()">mudar formato</button>
                `,
                pipes: [CepPipe]
})
export class AppComponent{
    private texto: string = 'Angular 2 Filtros';
    private valor: number = 45.6123;
    private dataAtual: Date = new Date();
    private formato: boolean = true;
    private cep: string = '37545000';

    get formatarData(){
        return this.formato ? 'dd/MM/yyyy HH:mm' : 'dd/MM/yy';
    }
    mudarFormatoData(){
        this.formato = !this.formato;
    }
}