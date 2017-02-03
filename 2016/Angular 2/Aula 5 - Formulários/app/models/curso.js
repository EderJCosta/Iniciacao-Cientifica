System.register([], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var Curso;
    return {
        setters:[],
        execute: function() {
            Curso = (function () {
                function Curso(codigo, nome) {
                    this.codigo = codigo;
                    this.nome = nome;
                }
                return Curso;
            }());
            exports_1("Curso", Curso);
        }
    }
});
//# sourceMappingURL=curso.js.map