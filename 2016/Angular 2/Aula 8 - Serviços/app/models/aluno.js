System.register([], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var Aluno;
    return {
        setters:[],
        execute: function() {
            Aluno = (function () {
                function Aluno(nome, email) {
                    this.nome = nome;
                    this.email = email;
                }
                return Aluno;
            }());
            exports_1("Aluno", Aluno);
        }
    }
});
//# sourceMappingURL=aluno.js.map