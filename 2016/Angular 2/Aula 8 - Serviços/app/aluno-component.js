System.register(['angular2/core', './models/aluno', './services/aluno-service'], function(exports_1, context_1) {
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
    var core_1, aluno_1, aluno_service_1;
    var AlunoComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (aluno_1_1) {
                aluno_1 = aluno_1_1;
            },
            function (aluno_service_1_1) {
                aluno_service_1 = aluno_service_1_1;
            }],
        execute: function() {
            AlunoComponent = (function () {
                function AlunoComponent(alunoService) {
                    this.alunoService = alunoService;
                }
                AlunoComponent.prototype.ngOnInit = function () {
                    this.aluno = new aluno_1.Aluno();
                    this.idEditar = -1;
                    this.alunos = this.alunoService.listarTodos();
                };
                AlunoComponent.prototype.cadastrar = function () {
                    this.alunoService.cadastrar(this.aluno);
                    this.aluno = new aluno_1.Aluno('', '');
                };
                AlunoComponent.prototype.editar = function (id) {
                    this.idEditar = id;
                    this.aluno = new aluno_1.Aluno(this.alunos[id].nome, this.alunos[id].email);
                };
                AlunoComponent.prototype.atualizar = function () {
                    this.alunoService.atualizar(this.idEditar, this.aluno);
                    this.aluno = new aluno_1.Aluno('', '');
                    this.idEditar = -1;
                };
                AlunoComponent.prototype.excluir = function (id) {
                    this.alunoService.excluir(id);
                    this.idEditar = -1;
                };
                AlunoComponent = __decorate([
                    core_1.Component({
                        selector: 'aluno',
                        templateUrl: 'app/views/aluno.html',
                        providers: [aluno_service_1.AlunoService]
                    }), 
                    __metadata('design:paramtypes', [aluno_service_1.AlunoService])
                ], AlunoComponent);
                return AlunoComponent;
            }());
            exports_1("AlunoComponent", AlunoComponent);
        }
    }
});
//# sourceMappingURL=aluno-component.js.map