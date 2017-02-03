import {Component} from 'angular2/core';
import {FormAlunoComponent} from './form-aluno-component';
@Component({
    selector: 'meu-app',
    template:   '<form-aluno></form-aluno>',
    directives: [FormAlunoComponent]
    
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