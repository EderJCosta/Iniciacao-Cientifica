import {Component} from 'angular2/core';

@Component({
    selector: 'meu-app',
    template: `
<h1>Angular 2 - Template</h1>
<h2>Listagem de Tarefas</h2>
<ul>
    <li *ngFor="#tarefa of tarefas #i = index">{{tarefa}}<a (click)="removerTarefa(i)" href="#"> Excluir</a></li>
</ul>
<input [(ngModel)]="tarefa"  name="tarefa" type="text" placeholder="Nome da Tarefa"/>
<button (click)="adicionarTarefa()" >Adiconar</button>

`
})
export class AppComponent{
    public tarefas: string[] = [
        'Estudar Angular 2',
        'Estudar Sistemas Distribuidos',
        'Estudar Engenharia de Software'
    ];
    public tarefa: string;

    adicionarTarefa() {
        if( this.tarefa != '') {
             this.tarefas.push(this.tarefa);
             this.tarefa = '';
        }
    }
    removerTarefa(index:number){
        this.tarefas.splice(index, 1);

    }
    
}