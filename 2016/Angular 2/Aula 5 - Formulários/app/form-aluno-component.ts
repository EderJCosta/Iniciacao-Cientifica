import {Component} from 'angular2/core';
import {Curso} from './models/curso';
import {Aluno} from './models/aluno';

@Component({
    selector: 'form-aluno',
    templateUrl: 'app/views/formulario.html'
})

export class FormAlunoComponent {
    private sucesso: boolean = false;
    private cursos: Curso[];
    private aluno: Aluno;

    constructor() {
        this.aluno = new Aluno();
        this.cursos = [
            new Curso('SD', 'Sistemas Distribuidos'),
            new Curso('TSIA', 'Tópicos em Sistemas de Informação Aplicados'),
            new Curso('ESSII', 'Engenharia de Sistemas de Software II')
        ]
    }
    enviar(): void {
        this.sucesso = true;
    }

    debug(): string{
        return JSON.stringify(this.aluno);
    }
}