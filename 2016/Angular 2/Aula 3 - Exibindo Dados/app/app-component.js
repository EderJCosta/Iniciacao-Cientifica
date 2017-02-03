System.register(['angular2/core', './models/cliente'], function(exports_1, context_1) {
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
    var core_1, cliente_1;
    var AppComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (cliente_1_1) {
                cliente_1 = cliente_1_1;
            }],
        execute: function() {
            AppComponent = (function () {
                function AppComponent() {
                    this.clientes = [];
                    this.cliente = this.clientes[0];
                    this.listagem = "Listagem de Clientes";
                    this.clientes = [
                        new cliente_1.Cliente(1, 'Richard'),
                        new cliente_1.Cliente(2, 'Edgard')
                    ];
                    this.cliente = this.clientes[0];
                }
                AppComponent.prototype.selecionarCliente = function (index) {
                    if (index < this.clientes.lenght || index >= 0) {
                        this.cliente = this.clientes[index];
                    }
                };
                AppComponent = __decorate([
                    core_1.Component({
                        selector: 'meu-app',
                        template: "\n                    <h1>Angular 2 - aula 3</h1>\n                    <h2>{{listagem | uppercase}}</h2>\n                    <p>cliente selecionado: {{cliente.nome}}</p>\n                    <ul>\n                        <li *ngFor=\"#cliente of clientes #i = index\">{{cliente.id}} - {{cliente.nome}}\n                        <a (click)=\"selecionarCliente(i)\" href=\"#\"> Selecionar</a></li>\n                    </ul>\n                "
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