System.register(['angular2/core', './models/curso', './models/aluno', 'angular2/common', './validators/email-validator'], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, curso_1, aluno_1, common_1, email_validator_1;
    var FormAlunoComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (curso_1_1) {
                curso_1 = curso_1_1;
            },
            function (aluno_1_1) {
                aluno_1 = aluno_1_1;
            },
            function (common_1_1) {
                common_1 = common_1_1;
            },
            function (email_validator_1_1) {
                email_validator_1 = email_validator_1_1;
            }],
        execute: function() {
            FormAlunoComponent = (function () {
                function FormAlunoComponent(fb) {
                    this.sucesso = false;
                    this.aluno = new aluno_1.Aluno();
                    this.cursos = [
                        new curso_1.Curso('SD', 'Sistemas Distribuidos'),
                        new curso_1.Curso('TSIA', 'Tópicos em Sistemas de Informação Aplicados'),
                        new curso_1.Curso('ESSII', 'Engenharia de Sistemas de Software II')
                    ];
                    this.buildForm(fb);
                }
                FormAlunoComponent.prototype.buildForm = function (fb) {
                    this.alunoForm = fb.group({
                        nome: ['', common_1.Validators.required],
                        email: ['', common_1.Validators.compose([
                                common_1.Validators.required,
                                email_validator_1.EmailValidator.validate
                            ])],
                        curso: ['', common_1.Validators.required]
                    });
                };
                FormAlunoComponent.prototype.hasErrors = function () {
                    var hasErrors = false;
                    for (var controlName in this.alunoForm.controls) {
                        var control = this.alunoForm.controls[controlName];
                        if (!control.valid && !control.pristine) {
                            hasErrors = true;
                            break;
                        }
                    }
                    return hasErrors;
                };
                FormAlunoComponent.prototype.enviar = function () {
                    this.sucesso = true;
                };
                FormAlunoComponent.prototype.debug = function () {
                    return JSON.stringify(this.aluno);
                };
                FormAlunoComponent = __decorate([
                    core_1.Component({
                        selector: 'form-aluno',
                        templateUrl: 'app/views/formulario.html'
                    }), 
                    __metadata('design:paramtypes', [common_1.FormBuilder])
                ], FormAlunoComponent);
                return FormAlunoComponent;
            }());
            exports_1("FormAlunoComponent", FormAlunoComponent);
        }
    }
});
//# sourceMappingURL=form-aluno-component.js.map