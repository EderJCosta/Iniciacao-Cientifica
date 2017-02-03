import {Component} from 'angular2/core';
import {AlunoComponent} from './aluno-component';

@Component({
    selector: 'meu-app',
    template: '<aluno></aluno>',
    directives: [AlunoComponent]
})
export class AppComponent {

}
