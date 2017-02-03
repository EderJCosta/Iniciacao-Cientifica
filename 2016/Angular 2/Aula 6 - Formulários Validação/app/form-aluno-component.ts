import {Component} from 'angular2/core';
import {Curso} from './models/curso';
import {Aluno} from './models/aluno';
import {ControlGroup, FormBuilder, Validators, AbstractControl} from 'angular2/common';
import {EmailValidator} from './validators/email-validator';
@Component({
    selector: 'form-aluno',
    templateUrl: 'app/views/formulario.html'
})

export class FormAlunoComponent {
    private sucesso: boolean = false;
    private cursos: Curso[];
    private aluno: Aluno;
    private alunoForm: ControlGroup;

    constructor(fb: FormBuilder) {
        this.aluno = new Aluno();
        this.cursos = [
            new Curso('SD', 'Sistemas Distribuidos'),
            new Curso('TSIA', 'Tópicos em Sistemas de Informação Aplicados'),
            new Curso('ESSII', 'Engenharia de Sistemas de Software II')
        ];
        this.buildForm(fb);
    }

    buildForm(fb: FormBuilder): void {
        this.alunoForm = fb.group({
            nome: ['', Validators.required],
            email: ['', Validators.compose([
                Validators.required,
                EmailValidator.validate
            ])],
            curso: ['', Validators.required]
        });
    }

    hasErrors(): boolean {
        var hasErrors: boolean = false;
        for (var controlName in this.alunoForm.controls) {
            var control: AbstractControl = this.alunoForm.controls[controlName];
            if (!control.valid && !control.pristine) {
                hasErrors = true;
                break;
            }
        }
        return hasErrors;
    }
    enviar(): void {
        this.sucesso = true;
    }

    debug(): string {
        return JSON.stringify(this.aluno);
    }
}