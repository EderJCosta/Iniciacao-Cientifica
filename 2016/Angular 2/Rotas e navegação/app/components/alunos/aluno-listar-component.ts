import { Component } from '@angular/core';
import { Aluno } from '../.././models/aluno';
import { AlunoService } from '../.././services/aluno-service';
import { OnInit } from '@angular/core';
import { ROUTER_DIRECTIVES } from '@angular/router';
import { ModalUtilComponent } from '../utils/modal-util-component';

@Component({
	selector: 'aluno-listar',
	templateUrl: 'app/views/alunos/listar.html',
	providers: [AlunoService],
	directives: [ROUTER_DIRECTIVES, ModalUtilComponent]
})
export class AlunoListarComponent implements OnInit {

	private alunos: Aluno[];
	private idExcluir: number;

	constructor(private alunoService: AlunoService) {
	}

	ngOnInit() {
		this.alunos = this.alunoService.listarTodos();
	}

	excluir(id: number) {
		this.idExcluir = id;
	}

	onExcluir() {
		this.alunoService.excluir(this.idExcluir);
		this.idExcluir = -1;
		this.alunos = this.alunoService.listarTodos();
	}
}