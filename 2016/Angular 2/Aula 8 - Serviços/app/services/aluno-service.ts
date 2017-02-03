import {Injectable} from 'angular2/core';
import {Aluno} from '../models/aluno';

@Injectable()
export class AlunoService {

	private alunos: Aluno[] = [
		new Aluno('Fulano', 'fulano@email.com'),
		new Aluno('Beltrano', 'beltrano@email.com')
	];

	listarTodos() {
		return this.alunos;
	}

	cadastrar(aluno: Aluno) {
		this.alunos.push(aluno);
	}

	atualizar(id: number, aluno: Aluno) {
		this.alunos[id] = aluno;
	}

	excluir(id: number) {
		this.alunos.splice(id, 1);
	}
}