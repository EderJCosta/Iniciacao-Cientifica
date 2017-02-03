System.register(['angular2/core'], function(exports_1, context_1) {
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
    var core_1;
    var AppComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            }],
        execute: function() {
            AppComponent = (function () {
                function AppComponent() {
                    // ATRIBUTOS
                    // interpolação
                    this.olaFulano = 'ola aline!';
                    // atribuições
                    this.mostrarMensagem = true;
                    // one way e two way binding
                    this.nome = 'Fulano';
                    this.nome2 = 'Ciclano';
                    // css class
                    this.azul = true;
                    this.cadastrar = true;
                    this.remover = false;
                    // *ngIf
                    this.exibir = true;
                    this.contador = 1;
                    // *ngFor
                    this.clientes = ['Ciclano', 'Fulano', 'Beltrano'];
                }
                // MÉTODOS
                // interpolação
                AppComponent.prototype.obterJuros = function () {
                    return 100;
                };
                // eventos
                AppComponent.prototype.dizerOi = function () {
                    alert(this.olaFulano);
                };
                AppComponent.prototype.mostrarTexto = function ($event) {
                    alert($event.target.value);
                };
                // atribuições
                AppComponent.prototype.mudarMostrarMensagem = function () {
                    this.mostrarMensagem = !this.mostrarMensagem;
                };
                // one way e two way binding
                AppComponent.prototype.atualizarNome = function ($event) {
                    this.nome = $event.target.value;
                };
                // css class
                AppComponent.prototype.mudarEstilo = function () {
                    this.azul = !this.azul;
                };
                AppComponent.prototype.alterarCadastro = function () {
                    this.cadastrar = !this.cadastrar;
                    this.remover = !this.remover;
                };
                // *ngIf
                AppComponent.prototype.alternarValor = function () {
                    this.exibir = !this.exibir;
                };
                AppComponent.prototype.incrementar = function () {
                    this.contador++;
                };
                AppComponent = __decorate([
                    core_1.Component({
                        selector: 'meu-app',
                        templateUrl: 'app/views/app.html'
                    }), 
                    __metadata('design:paramtypes', [])
                ], AppComponent);
                return AppComponent;
            }());
            exports_1("AppComponent", AppComponent);
        }
    }
});
//# sourceMappingURL=app-component.js.map